# Micronaut Guidelines
These are my guidelines coming from the perspective of using Micronaut as a backend rest service or as a REST API. 

At the time of writing this other that POCs I've only used Micronaut for about 2 months. So I'm copying/pasting the Grails standards
and conforming them to what I understand so far. Micronaut was built by the same developers that Build Grails so there is a lot
of similar patterns.

Resources:
* [Main site](http://micronaut.io/)
* [Docs](https://micronaut.io/documentation.html)
* [Guides](https://guides.micronaut.io/index.html)
* [Security Announcements](https://micronaut.io/category/security-announcements/)
* [Security Docs](https://micronaut-projects.github.io/micronaut-security/latest/guide/)
* [Releases](https://github.com/orgs/micronaut-projects/projects?query=is%3Aopen)
* [Road Map](https://github.com/orgs/micronaut-projects/projects/69/views/1)
* [Micronaut Data](https://micronaut-projects.github.io/micronaut-data/latest/guide/)
* [Launch](https://micronaut.io/launch)
* [Discord](https://discord.com/invite/9xRFsHv98T)
 
## General Structure
Micronaut doesn't really give you a structure/layout to your code like Grails, it doesn't have the convention over configuration, so almost
everything is determined by annotations. So you can decide what structure/layout you want under src/main/groovy or by using 
[Gradle Source Sets](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceSet.html). Regardless of where you put your code you could
use the same structure of Grails or a "component" based structure like the project I'm currently working on.

### Grails like structure under src/main/groovy.
While not spelled out here you should use packaging to organize under the various categorize that the Grails like structure provides.

I prefer this layout because from an information architecture perspective it makes it easy to guess where things will be based on their
type. Also, the guessability of location within categories can be helped by good naming of packages. One more benefit is that you can do
a code search in a category very easily.

* domains/models
  * Widget.groovy
  * Cog.groovy
  * Gear.groovy

* data 
    * widget
      * in
        * PutWidgetDataIn.groovy
        * PostWidgetDataIn.groovy
      * out
        * WidgetDataOut.groovy
    * cog
      * ...
    * gear
      * ...
* controllers
    * WidgetController.groovy
    * CogController.groovy
    * GearController.groovy
* services
  * WidgetService.groovy
  * CogService.groovy
  * GearService.groovy

### Component Based Structure
This structure in my option seems to have roots from JavaScript, just because that's where I've seen it used the most, but I coule be wrong.

This structure can be good when you have reusable self-contained components. While not shown here you will cross-cutting code that doesn't
fall under components. If you have more then one file per category of file, you may want to put them into sub packages for organization.

From an Information Architecture prespective this does give you the ability to guess where all the files are related to a component.

Where I think this layout structure falls down is if you don't have self-contained components, and they have dependencies on each other.
You also can't easily search through just one category of files.

* components
  * widget
    * Widget.groovy
    * WidgetController.groovy
    * WidgetService.groovy
    * data
      * in
        * PutWidgetDataIn.groovy
        * PostWidgetDataIn.groovy
      * out
        * WidgetDataOut.groovy


## Domains
* https://micronaut-projects.github.io/micronaut-data/latest/guide/#entityEvents
* https://micronaut-projects.github.io/micronaut-data/latest/guide/#hibernate
* https://micronaut-projects.github.io/micronaut-data/latest/guide/#dbc 
* Should not have a lot of logic in them and should not have services injected into them.
* Should be named with no postfix, or a postfix or `Domain`, or a postfix of `Model`. Choose one of the three per project/organization.
* If you do need special processing in a service for something like a beforeInsert use gorm events
  * https://async.grails.org/latest/guide/index.html#gormEvents
  * This allows you to inject a service once into the listener
* Shouldn’t have a lot of logic in them other than constraints it would be good to annotate them with @CompileStatic. 
* Shouldn’t be used as command objects because of the security concerns that they can inflict.
* Class level annotations:
  * @CompileStatic
  * @Entity
  * @Serdeable
  * @Introspected

---

## Controllers 
* https://docs.micronaut.io/latest/guide/#httpServer
* Should not have a lot of logic in them other than:
    * Taking in Command objects
    * Taking in URL parameters like ids
    * Validating command/data objects and return error messages
    * Calling services/repositories
    * Rendering output(JSON in most cases)
    * Redirecting(should be avoided within most single page apps and APIs).
* Should be named with the postfix of `Controller`
* Should probably be put off on a separate thread using @ExecuteOn.
* All parameters should be bound using command objects, or in single parameters in the signature of the controller action(like for ids in the url).
* Should not have any private methods, put those in services.
* Should be @CompileStatic except in a rare exception where dynamic code is an absolute need.
* Avoid using try/catch statements in favor of Exception handlers:\
  https://docs.micronaut.io/latest/guide/#exceptionHandler
* Class level annotations:
  * @CompileStatic
  * @Secured(SecurityRule.IS_AUTHENTICATED)
  * @Controller("/api/object")
  * @Slf4j
* Controller method anotations:
  * @Get\
    list
  * @Get("/{id}")\
    get
  * @Post\
    create
  * @Put("/{id}")\
    update
  * *@Patch\
    update
  * @Delete\
    delete


---

## Command/Data Objects
* https://docs.micronaut.io/latest/guide/#datavalidation
* Should be named with  postfix or `Data`, or a postfix of `Command`. Choose one of these per project/organization. 
* Tacking on a postfix to the postfix of `In` or `Out` can be used to add additional information of the intent of the data object.
* Please do not use the postFix of Dto.
* Should be used for binding JSON or form posts from a frontend/rest call.
* Should use constraint annotations for validating the properties like domain objects.
* Should not be domain objects because of security concerns.
* Should not contain domain objects as properties because of security concerns.
* Should not have services injected into them for performance.
* Should not have DB lookups or business logic in them, those should be in services.
* Should be @CompileStatic
* Class level annotations:
  * @CompileStatic
  * @MapConstructor
  * @Serdeable
  * @Introspected

If you want to do a validation that requires a DB lookup, that is business logic and belongs in a service not a command object.



---

## Services
* https://docs.micronaut.io/latest/guide/#beans
* Should be named with the postfix of Service
* Is where most if not all business logic should be stored.
* Is where all db interactions should be using @Transactional
* Should be groups of methods that go together logically.
* Class level annotations:
* @CompileStatic
  * @Singleton
  * @Slf4j


---

## Repositories(Micronaut Data)
* https://micronaut-projects.github.io/micronaut-data/latest/guide/
* https://micronaut-projects.github.io/micronaut-data/latest/guide/#repositories
* Where you put query logic for not dynamic queries in JPA/hibernate format, or native JDBC
* Should be named with the postfix of Repository.
* Should probably extend either CrudRepository or PageableRepository in most cases
* Class level annotations:
  * @CompileStatic
  * @Repository
    ```groovy
    class SomeObjectRepository extends CrudRepository<SomeObject, Long/UUID>
    ```


---

## Mappers
* https://docs.micronaut.io/latest/guide/#beanMappers
* Class level annotations:
  * @CompileStatic
  * @Singleton
    ```groovy
    interface SomeObjectMapper {
        @Mapper
        SomeObjectDataOut toSomeObjectrDataOu(SomeObjectr someObject)
    }
    ```
---

## Exception handlers
* https://docs.micronaut.io/latest/guide/#exceptionHandler
* Class level annotations:
  * @CompileStatic
  * @Singleton
  * @Produces
  * @Requires(classes = [ValidationException, ExceptionHandler])
  * @Slf4j
    ```groovy
    class ValidationException implements ExceptionHandler<ValidationException, HttpResponse> {
    ```

---

## Annotations collectors
https://docs.groovy-lang.org/latest/html/api/groovy/transform/AnnotationCollector.html

Micronaut seems to have an annotation for everything, which can make your classes look a little noisy. Groovy has an annotation collector
which allows you to consolidate a bunch of commonly used annotations into one annotation. If you are using the same set of annotations say
for Domain/Model objects you could make on annotation collector name it Domain or Model, and then put it on all your Domains/Models, except
in the case for you do something different for a specific case.

example:
```groovy
import groovy.transform.*
@AnnotationCollector([ToString, EqualsAndHashCode, Immutable])
@interface Alias {}

@Alias(excludes=["a"])
class Foo {
  Integer a, b
}
```

