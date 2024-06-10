List<String> hardware = [
        "1/4\" nut",
        "bolt",
        "1\" screws",
        "1\" nails",
        "hammer"
]

hardware << "screw driver"

println "Last item in the list: ${hardware[-1]}"

println("List of items:");
hardware.eachWithIndex { e, i ->
  println "$i\t$e"
}

println("Sorted items");
hardware.clone().sort().each { println it }

print("The items are: ");
println hardware.join(', ')
println hardware*.toUpperCase().join(', ')


//collect
def list = [1,2,3]
println("double ${list.collect{it *2}}")

//inject
def value = [1, 2, 3].inject('counting: ') { str, item -> str + item }
println  value == "counting: 123"
 
value = [1, 2, 3].inject(0) { count, item -> count + item }
println value
assert value == 6

//times
def someIntValueInYourProgam = 5
someIntValueInYourProgam.times{
    println it
}