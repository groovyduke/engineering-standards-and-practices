# Gradle
* [Documenation](https://docs.gradle.org/current/userguide/userguide.html)
* [Plugins](https://plugins.gradle.org/)

I use Gradle as my go-to build system. While it isn't perfect I generally find the Groovy DSL easier to read and use than XML-based systems.

Gradle has a tendency to move fast in terms of upgrades and deprecations, so it is good to try to keep up to date with it. Although when 
dealing with frameworks it is usually good to stick to the major version that the current version of your framework supports, because if
you don't you might get strange errors, where the framework isn't using the latest version, and that the latest version changes something
that causes the framework code to break.

## Plugins
The Gradle plugins are good starts to make some build tasks easier and integrate with various technologies. However, since a lot of  plugins
are community created they vary in terms of quality and how well maintained they are. If you used one of the community-created plugins and
whoever that is currently maintaining it stops you may have to take over maintaining it, or find an alternative.

## Dependencies
https://mvnrepository.com/
One of the primary uses of Gradle as a build system is to bring in dependencies, and cache them locally rather than having a lib directory
that you dump libraries into. Gradle can pull in dependencies from Maven repos which have become the standard for storing Java dependencies,
mostly as jar files.

In a Gradle Project you will have a build.gradle file with a `dependencies` section which will have entries that look like this:
```groovy
dependencies {
    dependencies {
        implementation("io.micronaut.groovy:micronaut-runtime-groovy")
        api("io.micronaut.serde:micronaut-serde-jackson")
        compileOnly("io.micronaut:micronaut-http-client")
        compileOnly("io.micronaut:micronaut-http-validation")
        compileOnly("io.micronaut.serde:micronaut-serde-processor")
        runtimeOnly("ch.qos.logback:logback-classic")
        testImplementation("io.micronaut:micronaut-http-client")
    }
}
```
Note the different types of dependencies that denote when they are used, `compileOnly`, `runtimeOnly`, `testImplementation`.
* `implementation` is used to denote a dependency that is not transient, so if you have a library this dependency will only be accessible
from with in that library.
* `api` is used to denote a dependency that will be shared if it was in a library and that library was used in another project. This is  
sometimes referred to as a transitive dependency. This can be good if you want your library to share a dependent library with the app,
* `commpileOnly` a dependency that is only applicable at compile time.
* `runtimeOnly` a dependency that is only applicable at runtime.
* `testImplementation` a dependency that will only be loaded durring unit and integration tests.
however if you have conflicting transitive dependencies that can lead to problems.

## Tasks
Tasks are used to execute build related code, like compiling, packaging, or running an app. Tasks have a dual life of configuration time
and runtime. When in your IDE you run refresh dependencies, so your IDE will update and cache the new dependencies to provide better 
assistance(code highlighting and completion) or run the task:
```bash
gradle --refresh-dependencies
```
anything not in a `doLast` will be executed. When you run a task or a task is run because it is dependent on the task you ran, anything
in the `doLast` will be executed

TODO add examples of creating task, with groups, and with and without dependencies.

## Parameters
Gradle accepts parameters from the command line using the `-P` notation. Parameters are set up in the gradle.properties file. Keep in mind
any parameters that you bring in will be treated as text, and you will have converted them to whatever format your code needs.

## Build Scripts
Build Scripts allows you to add classes to your build process and use them in your tasks.

TODO add examples of build scripts.
