import  java.util.regex.Pattern
class TestRegex{
    public static void main(String[] args){
        String regex = "\\.";
        String withsDot = "this is a dot. and it's in the middle"
        String withoutsDot = "ther is no dotin the middle"
        Pattern hasDot = Pattern.compile(regex)
        println hasDot.matcher(withsDot).find()
        println hasDot.matcher(withoutsDot).find()
        println "";


        String testHasPrePost = "Prefix*****Postfix";
        String testHasPre = "Prefix*****";
        String testHasPost = "*****Postfix";
        String testNotHasPrePost = "*****";

        Pattern prepost = Pattern.compile('^Prefix.*Postfix$');
        Pattern post = Pattern.compile('^.*Postfix$');
        Pattern pre =  Pattern.compile('^Prefix.*$');

        println "";
        println prepost.matcher(testHasPrePost).find();
        println pre.matcher(testHasPrePost).find();
        println post.matcher(testHasPrePost).find();

        println "";
        println prepost.matcher(testHasPre).find();
        println pre.matcher(testHasPre).find();
        println post.matcher(testHasPre).find();

        println "";
        println prepost.matcher(testHasPost).find();
        println pre.matcher(testHasPost).find();
        println post.matcher(testHasPost).find();

        println "";
        println prepost.matcher(testNotHasPrePost).find();
        println pre.matcher(testNotHasPrePost).find();
        println post.matcher(testNotHasPrePost).find();
    }

}