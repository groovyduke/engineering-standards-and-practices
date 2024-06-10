import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UseList {
  public static void main(String[] args) {
    List<String> harware = new ArrayList<String>();

    harware.add("1/4\" nut");
    harware.add("bolt");
    harware.add("1\" screws");
    harware.add("1\" nails");
    harware.add("hammer");
    harware.add("screw driver");

    System.out.println("Last item in the list: " +
      harware.get(harware.size() - 1));

    System.out.println("List of items:");
    for(int i = 0; i < harware.size(); i++) {
      System.out.println(i + 1 + "\t" + harware.get(i));
    }

    System.out.println("Sorted items");

    String[] items = new String[harware.size()];
    harware.toArray(items);
    Arrays.sort(items);

    for(String item : items) {
      System.out.println(item);
    }

    System.out.print("The items are: ");
    System.out.print(harware.get(0));
    for(int i = 1; i < harware.size(); i++) {
      System.out.print(", " + harware.get(i));
    }
  }
}
