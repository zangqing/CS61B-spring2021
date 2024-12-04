package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> NoResizeList = new AListNoResizing<>();
        BuggyAList<Integer> BuggyList = new BuggyAList<>();

        NoResizeList.addLast(4);
        BuggyList.addLast(4);
        NoResizeList.addLast(5);
        BuggyList.addLast(5);
        NoResizeList.addLast(6);
        BuggyList.addLast(6);

        assertEquals(NoResizeList.size(), BuggyList.size());

        assertEquals(NoResizeList.removeLast(), BuggyList.removeLast());
        assertEquals(NoResizeList.removeLast(), BuggyList.removeLast());
        assertEquals(NoResizeList.removeLast(), BuggyList.removeLast());

    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
//                System.out.println("addLast(" + randVal + ")");
                B.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
//                int size = L.size();
//                System.out.println("size: " + size);
                assertEquals(L.size(), B.size());
            } else if (operationNumber == 2) {
                // getLast
                if (L.size() > 0){
//                    int last = L.getLast();
//                    System.out.println("getLast: " + last);
                    assertEquals(L.getLast(), B.getLast());
                }
            } else if (operationNumber == 3) {
                //removeLast
                if (L.size() > 0){
//                    int last = L.removeLast();
//                    System.out.println("removeLast(" + last + ")");
                    assertEquals(L.removeLast(), B.removeLast());
                }
            }
        }
    }


}
