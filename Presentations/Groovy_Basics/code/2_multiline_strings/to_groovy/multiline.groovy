String message = """This is a very annoying way to do
multi line strings. You have to  write
A lot of extra code to go across the lines.
I wouldn't want to deal with all this ceremony.
"""


println message.replaceAll("\n", " ")


def presenter ="tucker"
println('string'.class)
println("string presenter".class)
println("string $presenter".class)
println('''string $presenter
'''.class)
println("""string $presenter""".class)

def stringTest = "test"
println stringTest[0..2]
println stringTest[-1..(-1*stringTest.length())]

println("""Multi
line
String""")


println('''Multi
line
String''')