 /** An SLList is a list of integers, which hides the terrible truth
   * of the nakedness within. */
  public class SLList {	
	private static class IntNode {
		public int item;
		public IntNode next;

		public IntNode(int i, IntNode n) {
			item = i;
			next = n;
			//System.out.println(size);
		}
	} 

	/* The first item (if it exists) is at sentinel.next. */
	private IntNode sentinel;
	private int size;

	// private static void lectureQuestion() {
	// 	SLList L = new SLList();
	// 	IntNode n = IntNode(5, null);
	// }

	/** Creates an empty SLList. */
	public SLList() {
		sentinel = new IntNode(63, null);
		size = 0;
	}

	public SLList(int x) {
		sentinel = new IntNode(63, null);
		sentinel.next = new IntNode(x, null);
		size = 1;
	}

	/**
	 * Constructs an SLList with an array of integers.
	 * Lists2 study guide exercises B2
	 * @param nums
	 */
	public SLList(int[] nums){
		sentinel = new IntNode(63, null);
		size = nums.length;
		IntNode p = sentinel;
		for(int i : nums){
			p.next = new IntNode(i, null);
			p = p.next;
		}
	}

 	/** Adds x to the front of the list. */
 	public void addFirst(int x) {
 		sentinel.next = new IntNode(x, sentinel.next);
 		size = size + 1;
 	}

	/** Deletes the first element of the list.
	 * Lists2 study guide exercises B1
	 */
	public void deleteFirst(){
		if (sentinel.next != null) {
			sentinel.next = sentinel.next.next;
			size -= 1;;
		}
	}


 	/** Returns the first item in the list. */
 	public int getFirst() {
 		return sentinel.next.item;
 	}

 	/** Adds x to the end of the list. */
 	public void addLast(int x) {
 		size = size + 1; 		

 		IntNode p = sentinel;

 		/* Advance p to the end of the list. */
 		while (p.next != null) {
 			p = p.next;
 		}

 		p.next = new IntNode(x, null);
 	}
 	
 	/** Returns the size of the list. */
 	public int size() {
 		return size;
 	}

	public static void main(String[] args) {
 		/* Creates a list of one integer, namely 10 */
 		// SLList L = new SLList();
 		// L.addLast(20);
		// L.deleteFirst();
 		// System.out.println(L.size());

		int[] nums = {1, 2};
		SLList S = new SLList(nums);
		System.out.println(S.size());
		
 	}
}