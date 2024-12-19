import java.util.Arrays;

/**
 * Study guide Recommended Problems B5
 * Give a counterexample that shows why this intuitive implementation of
 * union() for quick-find is not correct.
 * Also, it shows why we need cache id[p] and id[q]
 */
public class QuickFindBugDemo {
    private int[] id;
    private int count;

    public QuickFindBugDemo(int N) {
        id = new int[N];
        count = N;
        // Initialize each site to its own component
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    // The buggy union implementation
    public void union(int p, int q) {
        if (connected(p, q)) return;
        // Rename p's component to q's name
        for (int i = 0; i < id.length; i++)
            if (id[i] == id[p]) id[i] = id[q];
        count--;
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public static void main(String[] args) {
        QuickFindBugDemo qf = new QuickFindBugDemo(5);

        // Initial state: [0, 1, 2, 3, 4]
        System.out.println("Initial state: " + Arrays.toString(qf.id));

        // Connect 0-1
        qf.union(0, 1);
        // Should be: [1, 1, 2, 3, 4]
        System.out.println("After 0-1: " + Arrays.toString(qf.id));

        // Connect 2-3
        qf.union(2, 3);
        // Should be: [1, 1, 3, 3, 4]
        System.out.println("After 2-3: " + Arrays.toString(qf.id));

        // Try to connect 0-2
        qf.union(0, 2);
        // Should connect all of 0,1 with 2,3 but FAILS
        // Gets: [3, 1, 3, 3, 4]  <- Bug! Site 1 not connected
        System.out.println("After 0-2: " + Arrays.toString(qf.id));
    }
}