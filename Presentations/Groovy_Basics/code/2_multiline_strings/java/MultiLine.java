

import java.util.Date;

public class MultiLine {
  public static void main(String[] args) {
    String messege = "This is a very annoying way to do\n";
    messege += "multi line strings. You have to  write\n";
    messege += "A lot of extra code to go across the lines.\n";
    messege += "I wouldn't want to deal with all this ceremony.";

    System.out.println(messege);

    StringBuilder sb = new StringBuilder();
    sb.append("This is a very annoying way to do\n");
    sb.append("multi line strings. You have to  write\n");
    sb.append("A lot of extra code to go across the lines.\n");
    sb.append("I wouldn't want to deal with all this ceremony.");
    System.out.println(sb.toString());
  }
}