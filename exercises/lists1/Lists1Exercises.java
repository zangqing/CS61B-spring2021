public class Lists1Exercises {
    /** Returns an IntList identical to L, but with
      * each element incremented by x. L is not allowed
      * to change. */
    public static IntList incrList(IntList L, int x) {
        /* Your code here. */
        if (L == null){
            return null;
        }
        return new IntList(L.first+x, incrList(L.rest, x));
    }

    /** Returns an IntList identical to L, but with
      * each element incremented by x. Not allowed to use
      * the 'new' keyword. */
    public static IntList dincrList(IntList L, int x) {
        /* Your code here. */
//        Method 1
        //Here, p copies the reference of L
        //So, changes in p will also apply for changes in L
        IntList p = L;
        while (p != null) {
            p.first += x;
            p = p.rest;
        }
        return L;

//        Method 2
//        while (L != null) {
//            L.first += x;
//            L = L.rest;
//        }
//        return L;
    }

    public static void main(String[] args) {
        IntList L = new IntList(5, null);
        L.rest = new IntList(7, null);
        L.rest.rest = new IntList(9, null);

        System.out.println(L.size());
        System.out.println(L.iterativeSize());

        // Test your answers by uncommenting. Or copy and paste the
        // code for incrList and dincrList into IntList.java and
        // run it in the visualizer.
        // System.out.println(L.get(1));
        //System.out.println(incrList(L, 3));
        // System.out.println(dincrList(L, 3));        
    }
}