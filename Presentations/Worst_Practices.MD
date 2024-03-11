
# Worst Practices Presentation
This is the text of a presentation I did on worst programming practices, based off of:
* [Grails Worst Practices](https://www.youtube.com/watch?v=PLh3jOYe_oI)
* [I've Seen Grails Code You Wouldn't Believe](https://youtu.be/BHBy_qp9mlI?si=7H8hQ7ayDJvKA1PM)

I also didn't include any pictures/memes as I want to make this repository public domain.

Larger Classes are Better
* I've seen classes with 4k,5k, 13k loc
* Intellij RIP

How to fix/prevent
* Break the class up into multiple classes
* logically group classes by function
* Code reviews/think about refactoring when
* Over 500 lines
* Code that does all follow the same theme
* When you are injecting a lot of services

Larger Methods are Better
* 250+ lines of code
* 6 levels on nested IFs
* Commented out code
* Unused variables
* Uses String replacement instead of a template engine
* Not taking advantage of Groovy
* Untestable

How to Fix/Prevent
* Breakup into smaller methods that do one thing
* You don’t need to go to the extreme though and have one liner methods for everything, i.e. don’t over engineer it
* Used guarded if statements
* Start thinking about refactoring if the code is larger than one screen
* Code reviews

Put all your code in controllers preferably one, and don’t bother with Services.

How to Fix/Prevent
* You can use more than one controller per domain
* Try to organize Controllers around resources and a theme
* Don’t put business logic in a controller
* A controller should only receive params, delegate to services and return responses
* Code reviews

Copy-Paste because it’s the fastest way to get things done…
* Am I seeing double pic

How to Fix/Prevent
* Try to be DRY- Don’t repeat yourself
* Pull duplicate code out to a method to reuse
* Don’t just copy and paste from Stack Overflow
* Code reviews

Use def everywhere, types are overrated.
* Toy Story pick Types Overrated Use Def Everywhere

How to Fix/Prevent
* Use types for everything you know the type of(String Integer, Boolean etc)
* Use types for method signature parameters
* Use types for beans, but be careful to use the right type
* If you don’t know the type of something, put a breakpoint debug the code and find out
* There are places to use def like when doing something that is dynamic
* For local variables it maybe ok to use def, but you might miss out on IDE help
* Code reviews

Naming is hard
* corn marked as watermelon pic, bad names to avoid, spring example

How to Fix/Prevent
* Use meaningful names one to three words
* Avoid using full sentences as names
* Avoid partial names
* Avoid single letter names
* Avoid Acronyms
* Avoid adding type pre/postfixes, when your variable is already typed.
* Code reviews

Use magic numbers/Strings Everywhere

How to Fix/Prevent
* Use static constants
* Use enums
* Use configs
* Use i18n message bundles
* Code reviews

Just pass params to Service and validate there
* Bag of crap pic

How to Fix/Prevent
* Use Command objects
* Run validation on Command objects
* Have your services have actual signatures with typed variables
* Watch the Grails Basics video
* Code reviews

Don’t bother profiling code and using @Compile Static
* turtle on keyboard

How to Fix/Prevent
* Profile code with tools like JProfiler/New Relic
* Add @CompileStatic/@
* Add @CompileStatic/@
* Profile before and after
* Avoid using HasManys/HasOne on large tables
* Code review slow code

Don’t bother learning more
* I have no idea what I'm doing pic

How to Fix
* Code reviews
* Read documentation:
  * http://groovy-lang.org/single-
  * https://groovy-lang.org/style-
  * http://docs.grails.org/latest/
  * http://guides.grails.org/#/
  * http://gorm.grails.org/
* Use resources
  * https://github.com/
* Watch videos(dated ones may still be relevant)
  * https://www.infoq.com/
  * https://www.youtube.com/watch?
  * https://www.youtube.com/watch?
* Use Grails/Groovy Slack to ask questions
* Groovy
* Mentoring
* Buy books
* Send developers to conferences…
