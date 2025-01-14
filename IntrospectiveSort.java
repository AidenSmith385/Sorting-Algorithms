package pas.sorting;

/**
 * IntrospectiveSort class.
 */

public class IntrospectiveSort {

  /**
   * Sort the provided items using introspective sort.
   */
  public static <T extends Comparable<T>> void introspectiveSort(T[] items) {
    // TODO
    // Depth limit is 2 times log2(n)
    int depth = (int) (2 * Math.log(items.length) / Math.log(2));
    introspectiveSort(items, 0, items.length - 1, depth); // Recursive helper
  }

  /**
   * Recursive helper method.
   * Start with quicksort then switch to merge sort if depth is reached.
   *
   * @param items The array
   * @param left The start index
   * @param right The end index
   * @param depth The max depth before switching to merge sort
   */
  private static <T extends Comparable<T>> void introspectiveSort(T[] items, int left, int right,
      int depth) {

    if (left >= right) {
      return;
    }

    // Depth is reached, switch to merge sort
    if (depth == 0) {
      MergeSortImproved.mergeSubsortAdaptive(items, left, right);
      return;
    }

    // Quicksort
    int piv = QuickSort.findPivot(items, left, right);
    int part = QuickSort.partition(items, left, right, piv);

    // Sort the left and right partitions
    // Ensure valid recursive ranges
    if (part > left) {
      introspectiveSort(items, left, part - 1, depth - 1);
    }
    if (part < right) {
      introspectiveSort(items, part + 1, right, depth - 1);
    }
  }
}
