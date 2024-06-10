String sendEmail(String emailAddress, String message = "default message"){
    println("Sending email to ${emailAddress}. The message is $message")
}

sendEmail('Your@email.com')
sendEmail('Your@email.com', 'special message')