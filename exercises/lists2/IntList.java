public class IntList {
    public int first;
    public IntList rest;
    public IntList (int f, IntList r){
        first = f;
        rest = r;
    }

    /** Return the size of the list using... recursion! */
    public int size() {
        if (rest == null) {
            return 1;
        }
        return 1 + this.rest.size();
    }

    /** Return the size of the list using no recursion! */
    public int iterativeSize() {
        IntList p = this;
        int totalSize = 0;
        while (p != null) {
            totalSize += 1;
            p = p.rest;
        }
        return totalSize;
    }

    /** Returns the ith item of this IntList. */
    public int get(int i) {
        if (i == 0) {
            return first;
        }
        return rest.get(i - 1);
    }

    /**
     * Lists2 study guide exercises A1
     *
     * If 2 numbers in a row are the same,
     * we add them together and make one large node.
     * For example: 1→1→2→3 becomes 2→2→3 which becomes 4→3
     */
    public void addAdjacent(){
        IntList p = this;
        for (int i=1; i<this.size(); i++) {
            if (p.first == p.rest.first) {
                p.first += p.rest.first;
                p.rest = p.rest.rest;
            } else {
                p = p.rest;
            }

        }
    }

    public static void main(String[] args) {
        IntList L = new IntList(3, null);
        L = new IntList(2, L);
        L = new IntList(1, L);
        L = new IntList(1, L);

        L.addAdjacent();

    }
}
