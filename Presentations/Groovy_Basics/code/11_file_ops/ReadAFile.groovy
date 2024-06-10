String readFromFile(String filePath) {
  new File(filePath).text
}

println readFromFile('/etc/hosts')

new File('/etc/hosts').eachLine{ String line ->
  println line
}