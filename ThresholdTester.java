package pas.sorting;

import java.util.List;

/**
 * The driver class to assist in finding the helper method.
 */
public class ThresholdTester {
  /**
   * Main method to execute the trails.
   */
  public static void main(String[] args) {
    // Define the range of thresholds you want to test
    int startThreshold = 10;
    int endThreshold = 50;

    // Loop over the thresholds
    for (int threshold = startThreshold; threshold <= endThreshold; threshold += 10) {
      // Set the threshold in MergeSortImproved
      MergeSortImproved.setThreshold(threshold);

      // Output which threshold is being tested
      System.out.println("Testing threshold: " + threshold);

      // Create a SortProfiler object with desired parameters
      // Adjust the start size, increment, max size, and trials as needed
      SortProfiler sp = new SortProfiler(
          List.of(MergeSortImproved::mergeSortAdaptive),
          List.of("merge_adaptive_" + threshold),
          1000,      // start size
          1000,      // increment
          10000,     // max size
          5,         // trials per size (increase for more accuracy)
          Generators::generateRandom
      );

      // Run the profiler and send the output to stdout
      sp.run(System.out);

      // Optionally, add a separator for clarity
      System.out.println();
    }
  }
}
