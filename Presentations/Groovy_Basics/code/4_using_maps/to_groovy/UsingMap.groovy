def map = ["key1":"value1","key2":"value2"] //HashMap

map.each{
    println "$it.key : $it.value"
}

map.each{key, value ->
    println "$key : $value"
}

map.key3 = "value3"
pritnln map

String key = "key5"
map["key4"] = "value4"
println map
map[key] = "value5"
println map


def map2 = [ //if you have a lot of values it's easier to read if formatted this way:
    key1 : 1,
    key2 : 2,
    key3 : 3,
    key4 : 4,
    key5 : 5,
    key6 : 6,
    key7 : 7,
    key8 : 8,
    key9 : 9,
    key10: 10,
]