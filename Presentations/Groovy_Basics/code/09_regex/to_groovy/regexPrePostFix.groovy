//https://e.printstacktrace.blog/groovy-regular-expressions-the-definitive-guide/
import  java.util.regex.Pattern

Pattern regex = ~/\./
String withsDot = "this is a dot. and it's in the middle"
String withoutsDot = "ther is no dotin the middle"
println ((withsDot =~ regex).find())
println ((withoutsDot =~ regex).find())

String testHasPrePost = "Prefix*****Postfix"
String testHasPre = "Prefix*****"
String testHasPost = "*****Postfix"
String testNotHasPrePost = "*****"


Pattern prepost = ~/^Prefix.*Postfix$/
String post = /^.*Postfix$/
String pre =  /^Prefix.*$/

println ''
println testHasPrePost ==~ prepost
println testHasPrePost ==~ pre
println testHasPrePost ==~ post

println ''
println testHasPre ==~ prepost
println testHasPre ==~ pre
println testHasPre ==~ post

println ''
println testHasPost ==~ prepost
println testHasPost ==~ pre
println testHasPost ==~ post

println ''
println testNotHasPrePost ==~ prepost
println testNotHasPrePost ==~ pre
println testNotHasPrePost ==~ post


println ''
def input = testHasPost

switch (input) {
    case prepost:
        println "has pre and post"
        break

    case ~pre:
        println "has pre"
        break
        
    case ~post:
        println "has post"
        break

    default:
        println "has none..."
}