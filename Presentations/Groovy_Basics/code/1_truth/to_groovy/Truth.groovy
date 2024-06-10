def testTruth(op){
    if(op){
        println("true $op")
    }else{
        println("false $op")
    }
}
testTruth(false)
testTruth(0)
testTruth("")
testTruth([])
testTruth([:])

testTruth(true)
testTruth(1)
testTruth([1,2,3])
testTruth([1:"one",2:"two"])

String presenter = "Tucker"
testTruth(presenter)
testTruth(presenter.equals("Tucker"))
testTruth(presenter == "Tucker")