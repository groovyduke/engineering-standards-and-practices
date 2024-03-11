# Security
In general, I would say find out what your framework/language uses and use that for things like login/authentication and Role-based permissions.
Security is tightly coupled so do your research before implementing it, because changing it later could be a lot of work. You can outsource
parts of your security like the login(OAuth, saml, etc), and multi-factor auth, however deadening on the implementation this could be very
complicated to implement and not well documented. I've found that OAuth is much simpler, better documented, and easier to implement than saml.
Another thing to consider if you outsource security while they may be able to keep it more up to date than you, you don't control it and
if there is an outage, or they get hacked you don't have a lot of recourse.

## Permissioning

If you can get away with just having permission checks based on Roles you should because permissioning systems have a tendency to become unwieldy over time
and other than the plugin I made for Grails called Enforcer most libraries are little to no help and can end up causing issues in the long run.

The reason permissioning is hard is because when we first start to learn about it the first thing we are taught is ACL, which is a model that works well for file
systems. The reason it works well for file systems is that the data is denormalized, stored as metadata with the file, and is limited to read-write execute for 
the owner, groups, and others.

When you take ACL in its base form and start to apply it to a web application either you start to run in limitations or you expand it in ways it wasn't
meant for. Take the Spring Security ACL plugin for Grails for example, it stores data in the database in a very normalized structure so querying it can be 
inefficient. Also in web apps you usually have more permissions that read write and execute, Typically you have create, read, update, delete, and sometimes 
other actions as well.

### Case study 1: 

One place I worked I implemented Spring Security ACL replacing an unsupported library. We were securing one top-level object at the time and had up
to 7 different permissions on it, 7 for admins/owners, 4 for editors 2 for readers. Out of the box it was slow, because of the Gorm/Hibernate quires I was 
using to create/update the permissions. To work around this I had to do a direct update query and invalidate the cache that the plugin was enforcing on me.

For a while the system worked, although we did run into a few issues with its weird EL language, trying to get it to execute the permissioning logic we needed.
Then we were going to expand our permissioning and add a new object above our top-level object, which was a project object, this meant that the number of 
permissions we had was going to go up dramatically. Looking at the database for just 1200 of our top-level objects we had about 400k ACL entries. This was going
to be a problem. Spring Security ACL with the EL language at the time didn't provide any way to call out to other services to provide a more flexible way of 
doing permissions. So that is where the precursor to my Enforce plugin came in.

The precursor to my Enforce would inject a closure at compile time using an AST transform and call it at runtime. This allowed me to be able to call whatever
logic I needed and I wrote a migration from the normalized Spring Security ACL permissions to a denormalized hierarchical permission structure which took the
400k permissions all the way down to 20k, still a lot but more manageable, and wouldn't grow as fast. I later rewrote the plugin from scratch on my own time
creating Enforcer, which has an AST and injects a call to an enforcer service, taking up to 3 closures as parameters, a predicate closure, a success closure, 
and a failure closure. The success and failure closures were defaulted to a no-op and throwing a permission exception.

While I know not every project will be able to use Enforcer I think it's worth looking at and ripping off whatever you can for your own project. Key takeaways
are denormalizing your permission structure, and putting them in a hierarchy. You can have outliers that fall out of the hierarchy, but if you stick mostly to
a hierarchy, you will have fewer permissions which will be more maintainable over time. While you may or may not be able to port and use the
AST-based annotation, the underlying service could be very useful.

[Enforcer code base](https://github.com/virtualdogbert/Enforcer)

### Case study 2:

Another place where I worked had Permissions dynamically assigned to Roles. The upside to this is that having a role meant you had the  
permissions that went with it. I think it was common in this system to have one role per user. So you didn't have a lot of permission, and
you could cache them in theory, The only time the permissions changed was when new ones were added, or when you went to an admin page,
to change which permissions were connected to a Role. The downside to this setup is that there were permissions for everything, so you have
a very long list to manage for the entire system, so it became a running gag a new feature means new permissions. We also only had a simple
annotation to check the permission, so you couldn't do any checks beyond just the permission.

While I came to many of the conclusions about permissioning on my own, there is a video that I think is worth watching by Zed Shaw about
Acls. Keep in mind Zed does use sarcastic humor and does go a little off-topic by the end. If you don't have a lot of time watch it at 2X.

[The ACL is dead](https://www.youtube.com/watch?v=9BmcB_gp8kw&list=PL2LtD8ALnyTjDy-O6sBHsxYckYmiJ4VSH&index=3&t=26s)
