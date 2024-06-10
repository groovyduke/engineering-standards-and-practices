class Email{
    static String sendEmail(String emailAddress, String message){
        println("Sending email to " + emailAddress + ". The message is " +message);
    }

    static String sendEmail(String emailAddress){
        String message = "default message";
        sendEmail(emailAddress, message);
    }

    public static void main(String[] args){
        sendEmail("Your@email.com");
        sendEmail("Your@email.com", "special message");
    }
}