package pas.sorting;

/**
 * Improved MergeSort class.
 */

public class MergeSortImproved {

  private static int MERGE_SORT_THRESHOLD = 30;

  /**
   * Merge sort the provided array using an improved merge operation.
   */
  public static <T extends Comparable<T>> void mergeSortHalfSpace(T[] items) {
    // TODO
    mergeSort(items, 0, items.length - 1);
  }

  /**
   * Recursive method for the merge sort.
   *
   * @param items The array
   * @param left Edge of the sort
   * @param right Other end of the sort
   */
  private static <T extends Comparable<T>> void mergeSort(T[] items, int left, int right) {

    if (left >= right) {  // base case
      return;
    }
    int mid = (left + right) / 2;  // midpoint

    // left side
    mergeSort(items, left, mid);

    // right side
    mergeSort(items, mid + 1, right);

    merge(items, left, mid, right); // bring together
  }

  /**
   * Merge and copy the left half into the temp array.
   */
  private static <T extends Comparable<T>> void merge(T[] items, int left, int mid, int right) {

    int n = mid - left + 1; // Size of the left half

    // Allocate temp array of the exact size needed
    @SuppressWarnings("unchecked")
    T[] temp = (T[]) new Comparable[n];

    // Copy left half into temp
    for (int i = 0; i < n; i++) {
      temp[i] = items[left + i];  // left half
    }

    int lindex = 0; // left temp
    int rindex = mid + 1; // right array
    int cindex = left; // original array

    // Merge back into items
    while (lindex < n && rindex <= right) { // Merge back into the original
      if (temp[lindex].compareTo(items[rindex]) <= 0) {
        items[cindex++] = temp[lindex++];
      } else {
        items[cindex++] = items[rindex++];
      }
    }

    // Remaining data
    while (lindex < n) {
      items[cindex++] = temp[lindex++];
    }
  }

  /**
   * Merge sort the provided array by using an improved merge operation and
   * switching to insertion sort for small sub-arrays.
   */
  public static <T extends Comparable<T>> void mergeSortAdaptive(T[] items) {
    // TODO
    mergeSortAdaptive(items, 0, items.length - 1);
  }

  /**
   * Recursive method for adaptive sort.
   * Uses insertion for small arrays.
   *
   * @param items The array
   * @param left Index edge to sort
   * @param right Index end to sort
   */
  private static <T extends Comparable<T>> void mergeSortAdaptive(T[] items, int left, int right) {

    // Insertion if smaller than threshold
    if (right - left + 1 <= MERGE_SORT_THRESHOLD) {
      BasicSorts.insertionSubsort(items, left, right);
      return;
    }

    // Adaptive sort
    int mid = (left + right) / 2;
    mergeSortAdaptive(items, left, mid);  // Left half
    mergeSortAdaptive(items, mid + 1, right);  // Right half

    merge(items, left, mid, right);
  }

  /**
   * Using our improved merge sort. Fallback used by introspective sort.
   */
  public static <T extends Comparable<T>> void mergeSubsortAdaptive(T[] items, int start, int end) {
    // TODO
    // call the insertion sort
    if (start >= end) {
      return;
    }
    mergeSortAdaptive(items, start, end);
  }

  public static void setThreshold(int threshold) {
    MERGE_SORT_THRESHOLD = threshold;
  }
}
