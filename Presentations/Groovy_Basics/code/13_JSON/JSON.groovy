import groovy.json.*
JsonBuilder builder = new JsonBuilder()
builder.records {
  car {
        name 'HSV Maloo'
        make 'Holden'
        year 2006
        country 'Australia'
        record {
            type 'speed'
            description 'production pickup truck with speed of 271kph'
        }
  }
}
String json = JsonOutput.prettyPrint(builder.toString())
println json

println ''
def jsonSlurper = new JsonSlurper()
def object = jsonSlurper.parseText '''
    { 
      "simple": 123,
      "fraction": 123.66,
      "exponential": 123e12
    }'''

println object
println object instanceof Map
println object.simple.class == Integer
println object.fraction.class == BigDecimal
println object.exponential.class == BigDecimal