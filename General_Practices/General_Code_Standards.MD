# General Backend Standards and Guidelines
The following are some guiding principles to think about when you are coding. These are not hard and fast rules and do have exceptions.

If it makes sense to question and possibly update these.

## Consistency
All code standards have one goal consistency. If your code is consistent than it will be easier to reason about and maintain. 

It also makes it when someone new joins the team they can pick up the standards and stay consistent with the code base.

## KISS
Keep it Simple Stupid. Or Keep it Stupid Simple  I.E. don’t overengineer what you are doing, try to keep the code as simple as possible, because you or 
someone else will have to maintain that code, or eventually replace it.

## YNGTNI
You're not going to need it. I.E. don’t write code you think you are going to need leave that for when you actually need it. Otherwise, you will clutter up the 
code with extra unnecessary code, that only serves to confuse and misdirect people who have to maintain it after you.

## DRY
Do not Repeat Yourself. Generally it’s good to not have repeated code, because you will end up having to maintain two implementations of the same code. When 
appropriate abstract to a function, method, service/bean, plugin, or library, that can be reused. That being said there are times when more than one 
implementation is warranted like where you would have to add a lot of parameters to the signature in order to have one implementation. In general, you want to 
limit the number of parameters you have for a function/method, so that it is easier to fully test. So this can be a balancing act at times.

One notable exception is mirrored frontend and backend validations, because the backend can never trust the frontend/user input. The frontend validation are
usually in place to give a better user experience and for their convenience.


## You're not Google
Don't do something or adopt something just because a big company does it. You are not that big company and whatever solution they are using maybe overkill for 
what you are doing and cause you more time and headache then if you did it another simpler way. That's not to say that a big company won't release a tool,
or have a process that could be useful to you. Do your research before adopting any new tool, process or library and have good arguments to why you are going
to uses them. It also usually strengthens your argument if you also include where the new process, tool, or library has weaknesses or trade-offs, because all
choices do.

## Follow industry standards and practices
In general, it is good to follow industry standards and practices, so that you have a consistent code base where new people can pick up what you are doing because
what you are doing follows standards. One caveat to this are these standards are written by people, thus fallible and sometime incomplete. There are also 
sometimes competing standards(e.g. config formats), and disagreements(spaces vs tabs). When you deviate from established standards, or if one doesn't exist
make your own standard, document it and state why you are going with that standard.

## Generated Code
Code generators, AI/ChatGPT/Large language models are becoming more prevent as tools, however they are just that tools. They do not excuse
you from actually looking at what they generate and making sure that it's following coding standards, is readable, maintainable and most 
importantly actually works. Generators can save time and effort, but it is incumbent on you to make sure that when they hallucinate or
produce garbage, you don't put that garbage in the code base.

## Tabs vs Spaces
For your company just choose a lane and stick to it. Both is not the answer, especially in the same file. Both can actually get you into
trouble if you have to deal with Python or Yaml, which have significant whitespace.

Opinion: I prefer spaces because they are universal and work everywhere including Yaml, which doesn't support tabs. If you're  in DevOps a lot
of things use Yaml for configuration, so in the case you might have to uses spaces. If you turn on draw
white space in your editor because you are dealing with a programing languages or config format that has significant white space you may
find that one representation is easier to read than another, and while I think that breaks down more to preference I still prefer spaces.

Tabs do have there advantages in that you can configure
the amount of space displayed in an editor, and anecdotally I've heard that Tabs are better for the visually impaired.

## Class size
While this varies a lot between programing languages, because of verbosity, a good rule of thumb is to keep your classes below 500 lines for 
Groovy or 1000 lines for Java. If you are going beyond that you should think about refactoring. Large classes are harder to keep in your head,
and reason about. 

lines of code:
* 0
* 500 Good
* 1000 think about refactoring
* 3000 make time to refactor your code
* over 3000 you have major issues in your code, it will more than likely take far longer to compile, and could cause your editor/ide to freeze, 
especially with syntax checking turned on. 

## Method size
In general a method shouldn't be longer than one 1080p screen length. The reason for this is that you can see the whole method on one screen
without scrolling, and ensure that you are not just creating one big method. A method should also do one thing or set of things, this makes
them easier to reason about and test. 

## Number of Parameters in Method Signature
In general, you should try to keep it under 4. The reason for this is to keep the method from having too many responsibilities and to keep the
method easy to test because you don't have to deal with as many combinations of parameters. If you have to have over 4 then you may want to
create an object that contains that with validating annotations think Grails/Micronaut Command/Data/DTO Object, That way those parameters
become their own document that goes together.


---

## Code standards and Guidelines
Never eat an exceptions without a really good reason, which should be documented in the code, and code reviewed to see if there might be a better way.

Don't do this:
```
try{
    someMethodThatWillThrowAnException()
}catch{}

```

Instead, do this:
```
try{
    someMethodThatWillThrowAnException()
}catch{
    log.debug('eating error because xyz.')
}
```
OR
```
try{
    someMethodThatWillThrowAnException()
}catch{
    //eating error because xyz.
}
```
---
In general, it is better not to wrap and rethrow and exception, but in a case where you have to make sure you include the original exception
otherwise you loose the stacktrace making bugs harder to track down.

Don't do this:
```
}catch(SpecificEception e){
   throw new SoneOtherException('Something else happened and I don't know why...")
}
```

Do this instead:
```
}catch(SpecificEception e){
   throw new SoneOtherException('Something else happened, but now you know why.", e)
}
```

---

Use white space to make the code easier to read. For example there should be a line of whitespace around classes and statements(if, switch, while, etc). Also 
consider adding white space to break up groups of things in code. Here is a bad and good example, although don’t use this as an example for variable naming:

Bad example:
```
class SomeClass {
    static final SOME_CONSTANT="a constant"
    def someInstanceVariable=0
    Bean1 bean1
    Bean2 bean2
    Bean3 bean3
    Bean4 bean4
    Bean5 bean5
    someclass(Bean1 bean1, Bean2 bean2, Bean3 bean3,
            Bean4 bean4,Bean5 bean5{
        this.bean1 = bean1
        this.bean2 = bean2
        this.bean3 = bean3
        this.bean4 = bean4
        this.bean5 = bean5
    }
    //Maps or lists that go over a few entries get harder to read when they are on one line or wrapped on multiple lines.
    def someMap=[key1:'one',key2:'two',key3:'three',keyFour:'four',
                key5:'five',keySix:'six',key7:'seven']
    Service1 service1
    Service2 service2
    Service3 service3
    def someMethod(someparam){
        def someNumber=1
        def someString='something'
        if(someparam.isNumber() && someparam==someNumber){
            return true
        }else{
           return false
        }
        callSomeMethod()
        callSomeotherMethod()
        if(someparam==someString){
            return false
        }
    }
    def someOtherMethod(someParam){
        return "$someParam/some/other/url/part"
    }
}
```

Good example:
```
/**
 * A general description of the class and what it is for.
 */
class SomeClass2 {
    static final String Some_Constant = "a constant"
    Integer someInstanceVariable = 0
    
    Bean1 bean1
    Bean2 bean2
    Bean3 bean3
    Bean4 bean4
    Bean5 bean5

    someclass(
        Bean1 bean1,
        Bean2 bean2,
        Bean3 bean3,
        Bean4 bean4,
        Bean5 bean5{

        this.bean1 = bean1
        this.bean2 = bean2
        this.bean3 = bean3
        this.bean4 = bean4
        this.bean5 = bean5
    }
    
    //This can be accomplished through autoformatting, but it makes it easier to read
     Map someMap=[
         key1   : 'one',
         key2   : 'two',
         key3   : 'three',
         keyfour: 'four',
         key5   : 'five',
         keySix : 'six',
         key7   : 'seven'
     ]
    
    Service1 service1
    Service2 service2
    Service3 service3

    /**
     * A general description of the method.
     *
     * @param someParam a description of what the param is for.
     *
     * @return a description of what is returned
     */
    boolean someMethod( Integer someparam ){
        Integer someNumber = 1
        String someString = 'something'
        
        if(someparam.isNumber() && someparam == someNumber){
            return true
        }else{
           return false
        }

        callSomeMethod()
        callSomeotherMethod()

        if(someparam.toString() == someString){
            return false
        }
        
    }
    
    /**
     * A general description of the method.
     *
     * @param someParam a description of what the param is for.
     *
     * @return a description of what is returned
     */
    String someOtherMethod(someParam){
        return "$someParam/some/other/url/part"
    }
}
```
---

If you can avoid it don't have inner classes or enums. Closures and Lambdas have mostly replaced every having the need for inner classes. Inner classes can
create confusing code that is harder to read and generally leads to class files that are too long. Just break the class or enum out to another file. Breaking 
classes and enums out to separate files make them easier to find and reuse. 

---

Avoid using magic numbers, or inline strings, in your final code. Instead, prefer constants, enums, configs, or i18n messages looked up in a property file.

---

Avoid bit masks or obscure codes. There was a time when storage was expensive, and every bit counted, but for most business software we are beyond that, and
having things like bit masks can code just makes your code harder to read. Instead, use enums, or store a string that you can reconstitute to an enum.

---

Functions/methods should in general be short and testable. This is not to say go crazy and make everything one line long, there is a balance that needs to be 
struck. There are reasons to have a longer method, but if your method is more than one screen long, it probably has too much going on in it. One exception might
be a switch with many conditions, where each condition delegates to another method, i.e. not method onto itself. The reason you want shorter methods is that 
they are easier to read and reason about. However, if you go to the extreme,  where you have all one-liners that chain on to each other you can get into a 
situation where it’s hard to reason about the code because you are bouncing around method to method. So this is a balancing act where you will have to use your 
judgment.

---

All functions/methods must have javadoc documentation, explaining what the method is for, what are its inputs and outputs. This makes it easier to figure out 
what a method is for, and we can generate documentation from it.

---

Naming is hard, but when naming functions/method/classes/packages/Grails artifacts(Controller, service, domains, etc) try to follow the conventions of 
Java/Groovy/Grails/Micronaut for them, and use whole words rather than partial words. 

Bad example but SomeString instead of someStr. Some exceptions that you can use are well known acronyms like URL and URI. Using whole words can make variable 
names somewhat self documenting. Also try to make the name somewhat concise and don’t follow the spring convention of using whole sentences like 
ProjectContractChargingPeriodProjectAccountReferenceVM.

What to do about DTOs? I have two suggestions a postfix of `Data` or `Command` like `PersonData` or `PersonCommand` Both are simple and somewhat short `Data` 
does imply a Data transfer object without spelling it out. Also, you could add postfix to `Data` of `in` or `out` to denote if that data object is 
specifically used for taking in data or pushing it out, i.e. `DataIn`, `DataOut`. 

`Command`come from Grails/Micronaut where that is what they decided to call DTOs used to bind parameters in controllers.

Avoid naming impedance mismatch. This means try to be consistent with naming things through the layers of code. For example if in the front
end you have a field called postalCode, have a data object that binds it as postalCode, and a model/domain object that calls is postalCode
and a database column that calls it postal_code. This gives you consistency where I can guess the sql that I'm going to run to look in up
in the db, but if you named is zip in the db that consistency falls apart.

Naming conventions
* Packages all lowercase, period separated, usually including the company name for uniqueness. example: com.yourcompany.security
* Classes capitalized and camelcase example: class Project 
* Method camelcase example: String processFile(File file){}
* Variable/property/field camelcase example: String conceptName
* Static variable constant snake case with capitals example: static final String Azure_Service_URL

Static variables are the one place where I would rather not follow the Java convention to the letter as having all the letters be capitalized 
makes the works harder to read at a glance. I prefer just capitalizing the first letter of every word in the name.
https://www.suzannearnold.com/blog/are-capital-letters-harder-to-read

---

For code style go into Intellij under Editor/CodeStyle configure it how you like then click the button to export that as a .editorconfig file 
and include that in all your repositories. This way you should have consistent style and file formatting.

---

Include working run configurations in every project in a folder that can be copied and used by people new to the company/project. This is so you don't have
to pass these around to new people like it's tribal knowledge. Alternatively you can have them spelled out in a `README.MD` file.

---

Keep the `README.MD`/guides that are in each project upto date, and make sure they work periodically.

---

Write unit tests for code for things that are easy to unit tests, and integration tests for things that are not easy to unit test. If you 
find yourself trying to mock too many things, that’s a sign that you should probably be using an integration test instead of a unit test. 
Some things like file interactions and direct SQL calls don’t work in unit tests so those should have integration tests. 

There was a time that we preferred unit, over integration tests, because apps took too long to load, but with Grails 3 and 4 that has changed 
quite a bit and now you should prefer integration tests that are closer to testing how the code will actually be used.

Try to not make your tests brittle. Interaction based unit testing should be used sparingly, in dynamic cases that warrant it, as it can create brittle unit tests.

---

Move away from building war files and deploying to Tomcat. Today there are a lot of benefits to building as a jar/container with a jar in it rather than a war.
The Jar often contains an embedded version of Tomcat/Netty/etc that has been vetted and works with the version of the framework you are using. With the embedded
server you also get the following benefits: it works the same in dev as it does in production, the configuration is the same, you don't have to worry about
conflicting libraries dropped in a lib folder.
