
def writeStuff(writer) {
  writer.write("This is stupid")
}

trait UppercaseFilter {
  def write(String msg) {
    println msg.toUpperCase()
  }
}

trait ProfanityFilter {
  def write(String msg) {
    println msg.replace("stupid", "*****").toUpperCase()
  }
}

class StringWriter implements ProfanityFilter, UppercaseFilter {
  String toString() { target.toString() }
}


writeStuff(new StringWriter())
