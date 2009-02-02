package haus.util.sort;

public class QuickSort {
	    private static long comparisons = 0;
	    private static long exchanges   = 0;

	   /***********************************************************************
	    *  Quicksort code from Sedgewick 7.1, 7.2.
	    ***********************************************************************/
	    
	    @SuppressWarnings("unchecked")
	    public static void quicksort(Comparable[] a) {
	        shuffle(a);                        // to guard against worst-case
	        quicksort(a, 0, a.length - 1);
	    }

	    // quicksort a[left] to a[right]
	    @SuppressWarnings("unchecked")
	    public static void quicksort(Comparable[] a, int left, int right) {
	        if (right <= left) return;
	        int i = partition(a, left, right);
	        quicksort(a, left, i-1);
	        quicksort(a, i+1, right);
	    }

	    // partition a[left] to a[right], assumes left < right
	    @SuppressWarnings("unchecked")
	    private static int partition(Comparable[] a, int left, int right) {
	        int i = left - 1;
	        int j = right;
	        while (true) {
	            while (less(a[++i], a[right]))      // find item on left to swap
	                ;                               // a[right] acts as sentinel
	            while (less(a[right], a[--j]))      // find item on right to swap
	                if (j == left) break;           // don't go out-of-bounds
	            if (i >= j) break;                  // check if pointers cross
	            exch(a, i, j);                      // swap two elements into place
	        }
	        exch(a, i, right);                      // swap with partition element
	        return i;
	    }

	    // is x < y ?
	    @SuppressWarnings("unchecked")
	    private static boolean less(Comparable x, Comparable y) {
	        comparisons++;
	        //return (x < y);
	        return x.compareTo(y) < 0;
	    }

	    // exchange a[i] and a[j]
	    @SuppressWarnings("unchecked")
	    private static void exch(Comparable[] a, int i, int j) {
	        exchanges++;
	        Comparable swap = a[i];
	        a[i] = a[j];
	        a[j] = swap;
	    }

	    // shuffle the array a[]
	    @SuppressWarnings("unchecked")
		private static void shuffle(Comparable[] a) {
	        int N = a.length;
	        for (int i = 0; i < N; i++) {
	            int r = i + (int) (Math.random() * (N-i));   // between i and N-1
	            exch(a, i, r);
	        }
	    }

	    // test client
	    public static void main(String[] args) {
	        //int N = Integer.parseInt(args[0]);
	    	int N = 50;

	        // generate N random real numbers between 0 and 1
	        long start = System.currentTimeMillis();
	        //int[] a = new int[N];
	        Double[] a = new Double[N];
	        for (int i = 0; i < N; i++)
	            a[i] = Math.random();
	        long stop = System.currentTimeMillis();
	        double elapsed = (stop - start) / 1000.0;
	        System.out.println("Generating input:  " + elapsed + " seconds");

	        for (Double i : a)
	        	System.out.print(i + ", ");
	        System.out.println("");
	        
	        // sort them
	        start = System.currentTimeMillis();
	        quicksort(a);
	        stop = System.currentTimeMillis();
	        elapsed = (stop - start) / 1000.0;
	        System.out.println("Quicksort:   " + elapsed + " seconds");

	        // print statistics
	        System.out.println("Comparisons: " + comparisons);
	        System.out.println("Exchanges:   " + exchanges);
	        
	        for (Double i : a)
	        	System.out.print(i + ", ");
	        System.out.println("");
	    }
}

