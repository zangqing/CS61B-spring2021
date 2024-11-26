public class DLList<LochNess> {
    private static class StuffNode {
        public int item;
        public StuffNode next;
        public StuffNode prev;

        public StuffNode(StuffNode p,int i, StuffNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    /* The first item (if it exists) is at sentinel.next. */
    private StuffNode sentinel;
    private int size;
    private StuffNode last;

    /** Creates an empty DLList. */
    public DLList() {
        sentinel = new StuffNode(null, 63, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
        last = sentinel;
    }

    public DLList(int x) {
        sentinel = new StuffNode(null, 63, null);
        sentinel.next = new StuffNode(null, x, null);
        sentinel.next.prev = sentinel;
        sentinel.next.next = sentinel;
        last = sentinel.next;
        sentinel.prev = last;
        size = 1;
    }

    /** Adds x to the end of the list. */
    public void addLast(int x) {
        size = size + 1;

        StuffNode p = sentinel;

        /* Advance p to the end of the list. */
        while (p.next != sentinel) {
            p = p.next;
        }

        p.next = new StuffNode(null, x, null);
        p.next.prev = p;
        p.next.next = sentinel;
        last = p.next;
        sentinel.prev = last;
    }

    public void removeLast(){
        size -= 1;

        last = last.prev;
        last.next = sentinel;
        sentinel.prev = last;

    }

    public StuffNode getLast(){
        return last;
    }

    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    public static void main(String[] args) {
         DLList L = new DLList(10);
         L.addLast(20);
         L.removeLast();
         L.getLast();
         System.out.println(L.size());
    }
}
