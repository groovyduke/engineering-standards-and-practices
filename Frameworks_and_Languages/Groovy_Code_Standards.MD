# Groovy Guidelines

Resources:
* [Groovy Language Documentation](https://docs.groovy-lang.org/latest/html/documentation/) 
* [Groovy Style Guide](https://groovy-lang.org/style-guide.html)
* [Groovy Resources List](./../Original_Groovy_Resources.MD)

---

Don’t use def unless you know you are going to do something dynamic 

Reason
If you know the type of the variable you are going to use then use it This helps with ide integration, readability, and the ability to port 
to @CompileStatic if needed. If you don’t know the type of something and can’t find it in documentation(like Spring), use def temporarily 
set a breakpoint and then check the .class.name, if that has an error it’s probably a map, and you can use `.getClass().name` In method 
signatures, always put the types of the variable names, unless you are specifically using a dynamic type. If you are using a dynamic type
in the java doc for the method list why you are using a dynamic type. This makes method signatures easier to figure out what they need to
be called and helps with IDE auto-completion.

---

As a general rule class files should be no larger than 500 to 600 lines, if you are going over that it may be time to think about refactoring
into separate class files. Think about if all the functions actually go together logically. Also, don't let your code go over 3000 lines
as there is an "issue" where the Groovy Compiler will exponentially slow down once you go over 3000 to 4000 lines of code.

---

Don’t use closures in place of methods. Use closures if you are going to be passing them from one function to another, or are using the 
closures delegation properties for a DSL. The reason for this is that they are a little slower and take up a little more memory. So it's a 
balancing act of using closures where they make sense for their power, but not to abuse that power and use them in place of methods. 

---

When using closures in nontrivial examples instead of using the default `it` value, replace it with a more meaningful variable name and type.
While this is a trivial example instead of this :
```
List<Integer> numbers = [1,2,3,4]
List doubles numbers.collect{ it *2}
```
you could do:
```
List<Integer> numbers = [1,2,3,4]
List doubles numbers.collect{ Integer number ->
    number*2
}
```

---

Use Traits to inject/share common functionality into classes.

---
When doing assignments prefer ternary(?)/elvis(?:) operators, in places where you are doing small calls/checks, 
that are less than one line and are easy to read. If the calls and checks exceed one line, or would be hard to read all 
on one line prefer an if statement instead.
Good examples:
```
String test = someBoolean ? 'yes' : 'no' 
String test2 = someString ?: 'default string if someString is null' 
```

Bad Example don't do this:
```
String test = (x==2 && y %2 == 0) || someVaraible || someOthervariable >= someVariible ? "Some long unreable thing slkdjflsjdflksjflk" : "Some other long unreable thing slakjfalkjfl;ajrrlwajerlsajf"
```
---
Prefer inline lists and Maps where applicable. When properly formatted they can be easier to read and take up less lines of code


If you have a hardcoded key for a map use the maps dot syntax
```
Map test = [one:1]
test.two = 2
```

If you have a dynamic key you can use the bracket syntax:
```
String three = 'three'
Map test = [one:1]
test.two = 2
test[three] = 3
```

Try to use the above techniques to create code that is cleaner and easier to read, so instead o this:
```
private void sendMessage(Map uploadResults, Map message, String dataType) {
    Map results = [:]
    String failureMessage = getFailureMessage(results)
    results['results'] = [:]
    results['results'] = results
    results['someId'] = message.someId
    results['dataType'] = dataType
    results['completed'] = failureMessage ? false : true
    if (failureMessage) {
        results['failureMessage'] = failureMessage
    }
    log.info("Results =  {}", results.toString())
    messageService.send( "results-complete", results)
}
```

You might have this:
```
private void sendMessage(Map results, Map message, String dataType) {
   String failureMessage = getFailureMessage(results)

    Map results = [
            results             : failureMessage ?: results,
            someId              : message.id,
            datatype            : dataType,
            completed           : failureMessage ? false : true,
    ]

    if (failureMessage) {
       results['failureMessage'] = failureMessage
    }

    log.info("Results =  {}", results.toString())

   messageService.send( "results-complete", results)
}
```

---

For Strings use single quotes is you are not using templating
```
String test  = 'something'
```

Instead of using a String builder for simple concatenations, you can use Groovy Strings, which use double quotes with 
`${someVariable}` for templating. you can drop the curly brackets if as long as you are just using property access 
and a `.` doesn't follow the expression. As a style consideration, you could always use the curly brackets to avoid
edge cases.

Good examples:
```
String url = "http://$host:$port/$endpoint"

String url = "http://${subDomain}.$host:$port/$endpoint"

Map m = [host: 'www.google.com', port:80, endporint:'maps']
String url = "http://$m.host:$m.port/$m.endpoint"

String url = "http://${m.host.toUppercase()}:$m.port/$m.endpoint"
```

examples that will not work and cause exceptions:
```
String url = "http://$subDomain.$host:$port/$endpoint"

Map m = [host: 'www.google.com', port:80, endporint:'maps']
String url = "http://$m.host.toUppercase()}$m.port/$m.endpoint"
```
