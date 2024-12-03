/** Array based list.
 *  @author QZ
 */

//           0 1  2 3 4 5 6 7
// items: [6 9 -1 2 0 0 0 0 ...]
//    size : 5

    /* Invariants:
    addLast: The position of the next item to be inserted is always size.
    getLast: The position of the last item in the list is always size - 1
    size: The number of items in the list is always size.
     */

public class AList<Glorp> {
    private Glorp[] items;
    private int size;
    /** Creates an empty list. */
    public AList() {
        items = (Glorp[]) new Object[100];
        size = 0;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity){
        Glorp[] a = (Glorp[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    /** Inserts X into the back of the list. */
    public void addLast(Glorp x) {
        if (size == items.length) {
            resize(size + 1);
        }

        items[size] = x;
        size += 1;
    }

    /** Returns the item from the back of the list. */
    public Glorp getLast() {
        return items[size-1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public Glorp get(int i) {
        return items[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public Glorp removeLast() {
        Glorp last = getLast();
        //items[size] = 0; // yeah, won't hurt, but unnecessary
        items[size-1] = null;
        size -= 1;
        return last;
    }

    /**
     * Study Guide Exercises B1
     *
     * Adds an element x to the start of the list.
     * Shifts existing elements to the right and increases the list size.
     *
     * @param x
     */
    public void addFirst(Glorp x){
        // Check if we need to resize the array
        if (size == items.length){
            resize(size+1);
        }

        // Shift existing elements to the right
        for (int i = size; i>0; i--){
            items[i] = items[i-1];
        }

        // Add the new element at the beginning
        items[0] = x;
        size++;
    }
} 