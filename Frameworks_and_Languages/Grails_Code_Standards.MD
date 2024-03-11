# Grails Guidelines
These are my guidelines coming from the perspective of using Grails as a backend rest service or as a REST API.

Resources:
* [Grails User Guide](https://docs.grails.org/6.1.2/guide/single.html)
* [Grails guides](https://guides.grails.org/index.html)
* [GORM Docs](https://gorm.grails.org/latest/hibernate/manual/index.html)
* [Grails forge](https://start.grails.org/)
 
 Upgrading:
* [Latest Grails Upgrade Guide](https://docs.grails.org/latest/guide/upgrading.html)
* [Grails 3.3.10 to 4.0.2 Postmortem](https://dev.to/virtualdogbert/grails-3-3-10-to-4-0-2-postmortem-5kj)
* [Grails 2.5.5 to Grails 3.3.10 Postmortem](https://dev.to/virtualdogbert/grails-2-5-5-to-grails-3-3-10-postmortem-520a)
* [Upgrade 2 through 4](https://grails.github.io/grails-upgrade/latest/guide/index.html)

## Domains
* Should not have a lot of logic in them and should not have services injected into them. Grails 4 doesn’t support injection out of the box. The reason for this is that when you inject into a domain you are injecting into ever domain instance, which can add up in terms of memory consumption.
* If you do need special processing in a service for something like a beforeInsert use gorm events
  * https://async.grails.org/latest/guide/index.html#gormEvents
  * This allows you to inject a service once into the listener
* Shouldn’t have a lot of logic in them other than constraints it would be good to annotate them with @GrailsCompileStatic. 
* Shouldn’t be used as command objects because of the security concerns that they can inflict.

---

## Controllers 
* Should also not have a lot of logic in them other than:
    * Taking in Command objects
    * Taking in URL parameters like ids
    * Validating command object and return error messages
    * Calling services
    * Rendering output(JSON views in most cases)
    * Redirecting(should be avoided within most single page apps).
* All parameters should be bound using command objects, or in single parameters in the signature of the controller action(like for ids in the url).
* Avoid using the params and request.JSON properties in favor of command objects.
* Should not have any private methods, put those in services.
* Should be @CompileStatic or @GrailsCompileStatic except in a rare exception where dynamic code is an absolute need.
* Avoid using try/catch statements in favor of Exception handlers:
  https://docs.grails.org/latest/guide/theWebLayer.html#controllerExceptionHandling
  
  In an app these can be bundled into a trait and share among controllers

---

## Command Objects

Example with trait:
* https://github.com/virtualdogbert/rest-docs/blob/master/src/main/groovy/rest/docs/command/user/SaveCommand.groovy 
* https://github.com/virtualdogbert/rest-docs/blob/master/src/main/groovy/rest/docs/command/CommandTrait.groovy 

Example usage in a controller:
* https://github.com/virtualdogbert/rest-docs/blob/master/grails-app/controllers/rest/docs/UserController.groovy#L53-L67 
* https://github.com/virtualdogbert/rest-docs/blob/master/grails-app/controllers/rest/docs/ControllerTrait.groovy#L32-L40 

* Should reside in src/main/groovy/command, or alternatively grails-app/command with the proper gradle setup.
* Should be named `"${controller name}/{action name}Command"`, for example: `user/saveCommand.groovy`, for the `user` controller, using the `save` action.
* Should be used for binding JSON or form posts from a frontend/rest call
* Should be used in place of param, and request.JSON. If you have an exception to this documented it and have someone code review it, so they can tell you why you are wrong.
* Should use constraints for validating the properties like domain objects
* Should not be domain objects because of security concerns
* Should not contain domain objects as properties because of security concerns
* Should not have services injected into them for performance
* Should not have DB lookups or business logic in them, those should be in services

If you want to do a validation that requires a DB lookup, that is business logic and belongs in a service not a command object.



---

## Services
[Grails Services Guide](https://docs.grails.org/latest/guide/services.html)
[Data Services](https://gorm.grails.org/latest/hibernate/manual/index.html#dataServices)
* Is where most if not all business logic should be stored.
* Is where all db interactions should be using @Transactional
* Should be groups of methods that go together logically.
* Data services offer a better way of handling GORM queries decoupling them from your business logic services and can be injected into those services.

---

## UrlMappings
You should take advantage of the default mapping and either use the webapp or rest conventions
```
"/$controller/$action?/$id?(.$format)?"{
    constraints {
        // apply constraints here
    }
}
```

OR

For Session based
```
"/$controller"(action: “index”, method: "GET")
"/$controller/$id"(action: "fetch", method: "GET")

"/$controller/$id/$action"(method: "GET")
"/$controller/$id/$action"(method: "POST")
"/$controller/$id/$action"(method: "PUT")
"/$controller/$id/$action"(method: "DELETE")

"/$controller/$id"(action: "delete", method: "DELETE")
"/$controller/$id"(action: "update", method: "PUT")
"/$controller"(action: "save", method: "POST")

//These give you a work around to get more methods once you have to go beyond CRUD
"/$controller/$action"(method: "POST")
"/$controller/$action"(method: "PUT")
"/$controller/$action"(method: "GET")
"/$controller/$action"(method: "PATCH")
"/$controller/$action"(method: "DELETE")
```

For REST token bases(The version/namespace is important for maintained if the api is external facing, otherwise can be omitted.)
```
"/api/${controller}"(version: "1.0", action: “index”, namespace: "v1", method: "GET")
"/api/${controller}/$id"(version: "1.0", action: "fetch", namespace:"v1", method: "GET")

"/api/$controller/$id/$action"(version: "1.0", namespace: "v1", method: "GET")
"/api/$controller/$id/$action"(version: "1.0", namespace: "v1", method: "POST")
"/api/$controller/$id/$action"(version: "1.0", namespace: "v1", method: "PUT")
"/api/$controller/$id/$action"(version: "1.0", namespace: "v1", method: "DELETE")

"/api/${controller}/$id"(action: "delete", version: "1.0", namespace:"v1", method: "DELETE")
"/api/${controller}/$id"(action: "update", version: "1.0", namespace:"v1", method: "PUT")
"/api/${controller}"(action: "save", version: "1.0", namespace:"v1", method: "POST")

"/api/$controller/$action"(version: "1.0", namespace: "v1", method: "POST")
"/api/$controller/$action"(version: "1.0", namespace: "v1", method: "PUT")
"/api/$controller/$action"(version: "1.0", namespace: "v1", method: "GET")
"/api/$controller/$action"(version: "1.0", namespace: "v1", method: "PATCH")
"/api/$controller/$action"(version: "1.0", namespace: "v1", method: "DELETE")
```

Anything that you add beyond mappings like these should be an exception, and should be rare, so that your urlmappings are short and concise.

Normally for REST based controllers are based off a "resource", and you only get the 5 method GET with and with out the id, POST, PUT, and DELETE.
Going beyond normal REST you can add `/action` which gets you more methods to play with. It's a good idea not to put too many actions in a controller
to keep them maintainable.

---

## Querying

https://gorm.grails.org/latest/hibernate/manual/index.html 

---

* Avoid using failOnError: true except in setting up unit/integration tests. The reason for this is that your code flow shouldn’t be dictated by exceptions, and exceptions in groovy can be expensive, with huge stack traces. While the stack traces will go down in the future when Groovy completes its switch to the indy version, you still should avoid using exceptions for flow control.
* Don’t use Holders unless there is a really good reason why and document that reason. In general if you are using holders then you should make the class a service/bean, and not use the Holders. Holder to get service/bean references are less efficient than having the references wired in.
* Avoid using .withTransaction, in favor of the @Transactional Grails(not Spring) annotations. The .withTransaction is a hack, and in general if you need it you should pull that code out to another function using @Transactional.
* Also avoid unnecessary using flush: true, there are cases where this is warranted, but in most cases you should have @Transactional. In a service, that should handle the flushing for you when you return from the method.
* Prefer “where” queries over findBy/findByAll queries as they are more performant and will be checked at compile time:
  http://gorm.grails.org/latest/hibernate/manual/#whereQueries
* “Where”queries do have some limitations. If you ever find yourself needing to use a resultTransformer you may want to use a criteria query instead:
  http://gorm.grails.org/latest/hibernate/manual/#criteria
* If you want to get just one or a handful of columns instead of a whole object that use projections:
  http://gorm.grails.org/latest/hibernate/manual/#_querying_with_projections
* Because you can use the resultTransformer with criteria queries, they may be more useful with projections that have more than one column.
* For more advanced queries use either HQL or SQL instead, both are more verbose, but are good especially if you need to do a lot of joins, or you need query performance.

---

## Configuration
There are several ways to access configuration the cleanest way in Grails 3+ is to use the @Value annotation:
```
import org.springframework.beans.factory.annotation.*

class MyController {
    @Value('${foo.bar.hello:Jay}')
    String recipient

    def hello() {
        render "Hello ${recipient}"
    }
}
```
This gives you a clear separation of the config from the code, and allows you to set defaults.

The old way that you may see in code which should be *AVOIDED* is to use the GrailsApplication and do something like:
```
class MyController {
    GrailsApplication grailsApplication

    def hello() {
        render "Hello ${grailsApplication.config.recipient}"
    }
}
```

This unlike the @value annotation will not pull in environment variables, mixes into your code making it harder to read,
and doesn't provide a mechanism for defaults.

The other option that should only be used in places where you can't use the @Value annotations is:
```
  config.getProperty('max.line.numbers', Integer, 42)
```

This does everything the @Value annotation does but puts more code inline. The only place I could see using this is in
`resources.groovy` For files in src/main/groovy, you should pass the config in from a service. or from `resources.groovy`
if it is a bean that's being overridden. If a bean can be made into a service consider doing that instead. there is more documentation here:

[Retrieving Config Values In Grails® 3](https://grails.org/blog/2016-08-31.html)

---

## Testing

[Testing Guide](https://testing.grails.org/latest/guide/index.html)

Writing test is a part of writing code, and should not be overlooked. If you don't have enough time to write tests, then there is a problem 
with scheduling, and you need to talk to your team lead/manager. If for some unforeseen reason you can't get to write test at a given 
moment, then you should create and issue/ticket to come back and write tests.

Tests server multiple purposes:
* They give you a sense that your code is doing the right thing.
* They force you to take a second look at your code, and by doing, so you might find and fix bugs, before they get to QA or a User.
* They give you confidence that when you did a refactor, you didn't break code.
* They demonstrate how to use the code.

When writing tests you should:
* Try to cover as much of your code as possible.
* Test all edge cases that you can think of.
* Try to make reliable tests that are not brittle and prone to fail.
* Keep the tests simple, so that they are easier to maintain.
* Test should be independent of each other, except for any common setup within a test class.

When testing in Grails there are three type of tests you should be aware of:
* Unit
  * Should test code on a functional level and
  * Should be use for code that is in services and utility classes in src/main/groovy
  * Should do minimal amounts of mocking
  * Should not be used to test things that require interaction or testing file systems.
* Integration
  * Should be used to test Controller to a greater extent, and services to a lesser extent.
  * Should be used when an equivalent unit test would require too much mocking.
  * Should test code that is not easily unit tested
    * requires a lot of serves that would otherwise need to be mocked.
    * requires to test something that you wouldn't have access to mock, like a listener or an event.
    * requires access or testing of interactions with file systems.
    * requires direct SQL calls.
* Functional tests(usually Geb tests handled by QA)


There was a time that you might have shied away from using integration tests, because they took to long to load, and unit tests at the time gave us
more for our time. With modern version of Grails this is no longer the case, and integration tests should be written to fill in the gaps
of unit tests, and make it so that you aren't making ridiculous unit test that have to mock out everything.

For integration test you should definitely look into test containers, to make those tests easier to write and more reliable because they 
have a base database that gets reset everytime they are run.

When mocking going forward instead of using Expandos you should use Maps converted as objects, as Expandos can't be assigned to typed objects,
which you should be using more of that. to use maps to mock out a service you could do something like this:
```
SomeService someService
SomeSubService someSubSerive
...

SomeSubService mockSomeSubSerivce = [
    someMethod: {String param 1, int param2-> //These params must match the signature or the orginal method exactly.
        //add the code you would like to stand in for the method code.
        //return with the type of the original method, or omit return for void methods.
    }
] as SomeSubService
someService.someSubService = mockSomeSubSerivce

```
This method will only work for non-static methods. for properties, you can add `getProperty`/`setProperty` methods. For static methods or
methods you don't have direct access to you can override using metaclass, but be careful and use this sparingly, and make sure you
confine your changes to the test:
```
//Can be unit or integration
@ConfineMetaClassChanges(UserPrincipleService)
@ConfineMetaClassChanges(SpringSecurityUtil)
Class SomeTest{
    voidSetup(){
    
       UserPrincipleService.metaClass.getPrinciple = { ->
              CustomUser customUser = new CustomUser(12, "test@nielseniq.com", 'fullname', [])
              customUser.isCerberusUser = true
              return customUser
       }
       
       SpringSecurityUtil.metaClass.static.getLogginUser = { ->
               CustomUser customUser = new CustomUser(12, "test@nielseniq.com", 'fullname', [])
               customUser.isCerberusUser = true
               return customUser
       }
    }
    
}
}

```

In testing there is something known as interaction base testing, where you test that a method was called, possible a certain amount of times
during a test. This kind of testing should be the exception to the rule, and should be reserved for testing only dynamic behavior that wouldn't
be easily tested any other way. The reason for this is that interaction tests can make a test particularly brittle and prone to failure, 
when the code is changed/updated/extended.

When fixing bugs, this is another time when writing tests is a good idea, because you can write a test that tests for the conditions of a
bug, and when you fix that bug, it will that the conditions are present. This has bonus effect of possibly preventing a regression, if the
conditions are reintroduced because the test should fail.

### Integration testing 
For integration test we should use test containers for the db and Restassured for a rest client. Restassured gives us hook for if we want to 
use Spring RestDocs to document the API. See this example for more details: 
[rest-docs: A sample app demonstrating Spring Rest Docs and Test Containers with a Grails app.](https://github.com/groovyduke/rest-docs)
---

## Actuators
Actuators add the ability to access info from your app like health page and version info(like git). Since Grails 4+ all actuators will have 
the URL of /actuator/* by default.

build.gradle:
```groovy
plugins {
    id 'com.gorylenko.gradle-git-properties' version '2.4.0-rc2'
    //other plugins here
}

// Put this after dependencies section.
// Customize Git properties plugin.
gitProperties {
    // Change date format in git.properties file.
    dateFormat = "yyyy-MM-dd HH:mm:ssZ"
    dateFormatTimeZone = 'EST'
}
```

application.groovy:
```groovy
management.endpoints.health.enabled = true
management.endpoints.health.sensitive = false
management.endpoint.health.'show-details' = 'always'
management.endpoints.info.enabled = true
management.health.defaults.enabled = true
management.info.git.mode = 'full'

grails.plugin.springsecurity.filterChain.chainMap = [
        // a bunch of other patterns,

        [pattern: '/actuator/**', filters: 'none']
]

// Depending on the version of the git info plugin you may need this for the dev environment to see the info.
environments {
  development {
    spring.info.git.location = 'file:build/resources/main/git.properties'
  }
}
```

---

## JSON converters
JSON converters(as JSON) were deprecated and removed in versions 3.x, and 4.x. While you can bring them back using a plugin, it is time to move on and
use the JSON views:
* https://views.grails.org/latest/
* https://programmersought.com/article/48093134917/

JSON views have better performance than the converters, and you can achieve all the same things as the converters with a few tricks.

To get rending as JSON for maps/lists you can have view like:
```
model {
    Map data
}

json g.render(data)
```
and
```

model {
    List data
}

json g.render(data)
```
Then render them like:
```
render(view: '/common/mapAsJson', model: [data: [version: Version]])
render(view: '/common/mapAsJson', model: [data: [1,2,3]])
```

To render a JSON view to a string or other writer, you can do something like this:
```
class JsonService{
  @Autowired
  JsonViewTemplateEngine templateEngine

    void write(String template, Object data, Writer writer) {
        Template t = templateEngine.resolveTemplate(template)
        Writable writable = t.make(data: data)
        writable.writeTo(writer)
        writer.flush()
        writer.close()
    }

    String toJsonString(String template, Object data) {
        Template t = templateEngine.resolveTemplate(template)
        Writable writable = t.make(data: data)
        StringWriter stringWriter = new StringWriter()
        writable.writeTo(stringWriter)
        stringWriter.toString()
    }

}
```

Beyond those basic examples, gson views can take any domain/object and render it, including/excluding properties.

Generally in the Grails documentation for hieratical data where you would have to loop over items you can use templates, however this
can sometimes be a bit cumbersome. Another way to do it is to have:
```
jsonKey theCollectionFromTheModel, { TypeOfTheCollection item ->
    property1 item.property1
    property2 item.property2
}
```

Here is an example:
```
import com.security.Sprocket

model {
    List<Sprocket> results
}

json{
    sprockets(results) { Sprocket sprocket->
        id  sprocket.id
        material sprocket.material
    }
}

```