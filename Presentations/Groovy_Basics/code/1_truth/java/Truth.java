import java.lang.*;
import java.util.*;

class Truth {
    static void testTruth(boolean op) {
        if (op) {
            System.out.println("true " + op);
        } else {
            System.out.println("false " + op);
        }
    }

    public static void main(String[] args) {
        testTruth(false);
        testTruth(0 == 1);
        testTruth(!"".isEmpty());
        testTruth(!new ArrayList().isEmpty());
        testTruth(!new HashMap().isEmpty());

        System.out.println("");

        testTruth(true);
        testTruth(1 == 1);

        System.out.println("");

        List<Integer> l = new ArrayList();
        l.add(1);
        l.add(2);
        l.add(3);
        testTruth(!l.isEmpty());

        System.out.println("");

        Map<Integer, String> m = new HashMap();
        m.put(1, "one");
        m.put(2, "two");
        testTruth(!m.isEmpty());
        Map<Integer, String> n = new HashMap();
        n.put(1, "one");
        n.put(2, "two");
        testTruth(!m.isEmpty());
        testTruth(n == m);
        testTruth(n.equals(m));

        System.out.println("");

        String presenter = "Tucker";
        testTruth(!presenter.isEmpty());
        testTruth(presenter.equals("Tucker"));
        testTruth(presenter == "Tucker");
    }
}