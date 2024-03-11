# Bad Practices

I have helped to fix a lot of these bad practices over the years, but I didn't get to fix all of them, Some were too ingrained and couldn't be
removed without a costly refactor. None of this however means the people I worked with were bad. I have worked with a lot of smart people, 
who I have learned a great deal from. 

However, bad practices tend to seep in when a company is in a rush and developers are forced to cut corners. Like when a company is new in a
startup phase and time to market can be the difference between staying in Business or not. Another big source of bad practices are inexperience. 
This can be inexperience in general or inexperience with frameworks, libraries, etc.

Every company/organization should make time to fix and clean up bad practices rather than just kick the can down the road, The longer you
leave bad practices in your code the longer people will have to work around them and probably end up developing bad practices around them.
It is common to take the patterns you see in a codebase and perpetrate them for consistency, or with inexperienced developers because they
don't know better yet. This will end up costing you time and money

On the other hand, you shouldn't just go randomly fixing pieces of code, as you can step on other people's toes, end up breaking other things
, or cause a lot of additional time to be added to a QA process. Small refactors can be done while you are working on a piece of code, but
big refactors should be planned out, and be given the resources they need.

Everybody welcome to the Shit Show!
You're gonna see some crazy Shit! at the Shit Show!
And nobody knows what to do about the Shit Show!
So come on everybody lets Go! It's the Shit Show!

You think you've seen bad code? I've seen so much bad code that in a day of frustration I came up with a chorus to a song about bad code:
```
Everybody welcome to the Shit Show!
You're gonna see some crazy Shit! at the Shit Show!
And nobody knows what to do about the Shit Show!
So come on everybody lets Go! It's the Shit Show!
```

Then to go along with it, I compiled a list of bad practices I've seen over the years. At one point I was thinking about feeding the 
chorus and the list into ChatGPT and see if it could come up with a song in the style of Eminem, just because there are just so many
bad practices.

In any case here is the list with some context/commentary injected in:
### Commented out code to confuse
Commented code should never be pushed up to a main version control repository. You look at this code and wonder if it is important, why is
it here. Rather than wasting other people's time just delete it, if it's in version control then you can always get it back, or for the 
short term, you can use local history to get it back if you need it. Otherwise, you can note it down or maybe just save it to another branch.
### TODO comments that will never be done.
If you have a `todo` comment that is over a year old it will never be done, because it wasn't important enough to come back to and finish.
Having todo comments before you commit and push, can be useful to make sure you revisit an area of code. Something you can do instead of
committing a todo comment is written up a bug ticket and note down the class, method, and approximate line of code, to get back to the issue
you were writing that todo about.
### 10,000+ line classes
Having really large classes can make your code hard to read and reason about. A class should have a general theme or purpose and the 
methods/functions in it should follow from that. When your classes get too large they will often have multiple themes and purposes to them
which is what makes it hard to reason about what a single class does. Also, large classes can have a negative impact on compilation time,
depending on the compiler. This is not to say that you need to make every class super short, it is a balancing act. Depending on the language
I would say a class should be no larger than 500 to 1000 lines of code.
### 1000+ line methods
Just like having excessively large classes is bad having excessively large methods/functions makes your code hard to read, maintain, and test
As a rule of thumb try to keep methods/functions to one 1080p screen length, so you can see the whole function of one screen. there are
places that can be an exception to this where you may have a large switch statement and nothing else in the method, Although if you have a really
large switch statement that is a symptom of another bad practice.
### 6+ levels of indentation with if/else and loops
Having a lot of nested code can make your code hard to read. Sometimes you do need nested code for `if`s, loops, methods, etc. Once
you go past a few levels of this your code will be harder to read. Check out this video about nesting code:

https://youtu.be/CFRhGnuXG-4?si=yuhubOa70j6mqEJE
### Not making use of white space to visually divide up code.
If you don't use white space to break up statements in your code, it makes them a little harder to read, because you have to look a little 
more carefully to see the bound of that code. Adding white space around statements like if, for, try and constructs that span multiple lines
gives your code visual contracts, where you can easily see those statements broken out. You can also use white space to group certain parts
of related code together, but set them apart from other code.
### No Unit tests insight
If you don't write tests for your code, either you or QA will be testing that code manually and sending it back to you with more bugs to 
fix. Writing test also forces you to look at your code a second time and make sure it's actually doing what you think it should be doing.
When you refactor writing tests can give you some confidence that you didn't break your code. Also, if you write a test for a bug you are 
fixing you can make sure that bug doesn't come back when someone else changes the code. I'm not saying you have to do TDD but write some
meaningful tests, they will help prevent certain classes of bugs.
### Not one Integration test
In my mind integration tests can be even better than unit tests, because you are actually testing your code in practice with minimal to no
mocking of code. In the past or if you still do have a large monolith, that takes a long time to run, we prioritized unit tests because they
could be run quicker and give us quicker feedback, but with more modern frameworks like Micronaut, integrations tests can be almost as fast
as unit tests.
### Copy and paste code not using DRY
When you have exact duplicates of code in place, when you change something you end up having to change it in multiple places. I've seen
places where bad code practices were copy/pasted in multiple places, making it really hard to clean up. DRY or don't repeat yourself, is
the idea that you shouldn't have duplicate code if you can help it. There are times when you will have to have very similar code but is
slightly different. In those cases, it might make sense to either leave it alone or look to functional programming techniques.
### Transactional code in controllers
Controllers should be dumb and not have transactional db code in them. That type of code should be moved to a service layer with the rest
of your business logic. Controllers should only be taking parameters in, some light validation, delegating to a service level, and rendering
responses back to the caller of the controller.
### We reinvent the wheel all over the place why bother using a library?
I've seen someone reimplementing `.contains` using a loop. Look at your SDK and google to see if there are libraries that solve your 
particular problem before implementing it yourself. Often time someone has already fixed that problem and has created a solution that
will be much better than if you implemented it yourself.
### Tightly coupled code
A sign of tightly coupled code is when you fix one issue, and it breaks several other things because that code was dependent on the 
implementation of that code. While you should reuse code it is better for that reused to go through an API and not be dependent on the
internal implementation of a method/class.
### 100's of injected services
Injecting services can be a good way to reuse code, but like anything else, there is a balance. If you are injecting too many services, then
you are probably trying to do too much in that code.
### Unused variables
Just delete unused variables, they only serve the purpose of asking why is this here, and just make your code nosier than it needs to be.
### We use string replacement here who need a templating language.
### We don't use types, even when it makes sense
### No code reviews
### Bad Naming foo method methodNameThisActuallyWholeCompleteSentence
### impedance mismatching names
In general, it is a good idea to keep the same name for something all the way from the UI back to the database. This will make it easier to
trace through the different levels of your application. In one place I worked  we had UI-facing names for objects, but in the backend DB,
we had genetic algorithm names like gene, genotype, and allele. so you had to remember what mapped to what if you wanted to look something up in
the DB.
### Acronyms
Technology, in general, uses a lot of acronyms, try not to create your own, and don't use them when naming variables, this can make your code
more obscure and harder to read. Funny story one place I worked automatically made an acronym of a title for a unique identifier that was 
stored in an XML file, and one time I looked at the Annual New Users Security Metric and had a really good laugh.
### Magic numbers
It's always fun with you have magic numbers in code, being actual numbers or strings, because if you use that number or string in more than
one place, which is common, you'll have to update that in multiple places. A better solution is to put it in a static variable, or in
configuration.
### Don't want to learn anything new
I've known developers who refuse to learn anything new, they just want to do what they know, and anything new is a waste of time and an inconvenience.
### Never upgrade your frameworks or libraries
This goes hand in hand with not wanting to learn anything new. The longer you go without updating your framework or libraries the harder
it is because the more they have changed. Not to mention the possible vulnerabilities that are often in older and unmaintained frameworks
and libraries. I've worked at several places that had frameworks that were past their EOL dates, and I was usually the one helping to get 
updated.
### Drools
Drools is a rules framework that seems way overly complicated for what it does. I'd rather have well-written code that is easy to debug than
forcing Drools which seems like an unnecessary level of abstraction especially if you are using Groovy and can use closures.
### Regex write-only code
Regexes are very powerful and can be useful, however I've often seen where someone has copied one of Stack Overflow into the code, rather than
writing out the logic. Regexes are mainly write-only code, meaning you write it, forget what it means, and then never be able to read it 
again. If you actually write out the logic of the code it will be easier to maintain and debug later.
### client-specific code as an excuse.
Having client-specific code and an execute to have duplicate code, rather than having a base class that you only extend and override if you
need to for a customer. Although better companies to work for will have some things configurable, but have the same codebase for all clients.
Having the same code base for all clients is a lot easier to maintain, and you can act as a service rather than a product.
### Using closures as methods
Closure and functional programming can be very helpful and make certain classes of problems easier to read and maintain. However, they are
generally less performant than methods, so if you are not taking advantage of the functional programming aspects of closures use
methods instead.
### conditionals that will never be true
These can be funny to see but should be eliminated from the code, if a condition is always true, then it doesn't need to be there. If 
however a condition is always false you can remove that coed completely.
### DB tables with over a hundred columns
Where I saw this there were 99 "custom" columns and the reason they were all in one table was because 0 to x of those columns could be used,
and they all needed to be able to be sorted on. In retrospect, I think it would have been better to either have those in a separate table one per row or
in a NoSql structure and use a search engine on top of it to make them searchable and sortable.
### Invent our own ORM for searching don't use lucene solr or elastic search.
Related to the last one all those columns needed to be searchable and sortable, doing that by bashing HQL together can make for very 
complicated, hard to maintain code, especially since Lucene, Solr, and Elastic search already exist to fix this problem.
### Using Mapped collections from ORMs
Mapped collection especially in Hibernate/Gorm can lead to a lot of performance issues:
https://burtbeckwith.com/blog/?p=1029
It is often better to do the look-up yourself or do a query with a Join.
### Havng DB logic all over your app
This was an issue I saw a lot of in Grails. DB logic everywhere. This led to a lot of nested DB logic where transactions could become a problem.
A better way is to have a separation from your business logic and your DB code. Grails added Data Services which work to that end. Spring 
Boot and Spring Data, and Micronaut has Micronaut data all originally written by Graeme Rocher.
### Having inexpensive calculated columns in your DB
While storing expensive calculations in the DB or in a cache can be good for performance, storing inexpensive calculations and lead to a 
lot of extra maintenance without any real gain.
### Use NoSql as if it was SQL with joins
No SQL databases aren't SQL and are usually meant to be denormalized, so you don't have to do slow joins. However, I've also seen it where
someone did a query and brought everything into memory where they would manually join the data, which was exponentially slower than
having the join, and even slower than denormalizing their data structure to match how you should model NoSQL.
### Forget your frameworks I'll do it manually.
Why use command objects with Grails rich validation system, where you can write a bunch of if-thens and validate it all yourself? That's 
just one example I've seen where if someone had actually RTFM'ed they would and found better ways to do things rather than reinventing the
wheel.
### Use the file systems ACL in a web app.
As silly as this sounds I have seen it and was inefficient as you can imagine. Reinventing ACLS in the DB can also be a bad ideas, so I'll 

[refer to the security section](./Security.MD)
### Put your Git repo on a Windows share
One company did this, the repo ran slower than it should have and was eventually corrupted because someone turned syncing on in Windows.
Luckily with Git, every repo acts as a backup, so we didn't lose much, but after that we got GitHub and it was much better.
### You don't use an IDE
It doesn't take that long to set up an IDE and you gain a tool that allows you to easily debug your code. I've worked with some people
who would not use the IDE and asked for changes that would make the code easier for them to debug. In the JVM/SpringBoot/Grails/Micronaut 
world the best IDE we have is Intellij. It is not cheap for a commercial license, but it is a very helpful tool when maintaining a code base. 
### Use `printlns` to debug
`Printlns` are a "poor man's" debugger, even IntelliJ has a community edition, and while it has fewer features than the ultimate edition it's 
better than nothing. However, there are times when logging can be used in place of `printlns` to help narrow down where an error is happening
in production, so you know where to debug in your code. Or if you are trying to debug something that happens at the end of a long list.
### Don't profile your code
If your code is slow you should profile your code with a profiler to find out what is causing the slowdown so that you can fix it.
Jprofiler is a good tool for this.
### Don't believe in QA you want engineers to take "ownership"
Don't get me wrong engineers should write automated unit/integration tests, however manual QA has a different mindset, of how do I break
this app, and good QA will come up with scenarios that you never dreamed of. If you do not have manual QA someone will inevitably have to
do it, and if that's not their primary job they will do a worse job of it. Engineers don't make good manual QA of their own code, because
they are too engrossed in it and can't see the forest through the trees.

[Section dedicated to QA](./../Management/QA.MD)
### Deploying wars, instead of jars in containers.
This is more of not keeping up to date with current engineering trends and taking advantage of not just new technology but current 
technology that has been around for a while. Docker containers can be very useful for multiple purposes, including deploying your own apps.
### Open 200 bugs a month
This isn't a bad practice per se, but it is a symptom that you have systemic bad practices happening on your team.
### Builds that never end
One place I worked the app was so big that it took 12 to 15 min(3 to 5 min. restart) from a clean build to get compiled and running. 
Contrast that with a Micronaut app  I'm working on that builds and starts in 15 seconds. Which company do you think can be more productive 
long term? Once apps get to be too big, it's time to start thinking about breaking them up into smaller services and addressing issues that 
contribute to a long build time.
### Sharing Database schemas between services
This can lead to several issues, like contention for resources, deadlocks, and you can't evolve the data model of one app, without
possibly breaking another, so updates could affect multiple apps.
### Unit tests that test the filesystem, the DB,  with mocks
You shouldn't try to unit test external systems. If you want to involve external systems in your testing, it's better to use an integration
test and look into using test containers.
### Overuse of inheritance in places it shouldn't be used.

### Don't document anything, because it can become out of date
### Or document and not maintain it.












