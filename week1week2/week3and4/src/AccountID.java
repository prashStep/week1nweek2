import java.util.Arrays;

public class AccountID {

    // Linear Search for first and last occurrence
    public static void linearSearch(String[] logs, String target) {

        int first = -1;
        int last = -1;
        int comparisons = 0;

        for (int i = 0; i < logs.length; i++) {

            comparisons++;

            if (logs[i].equals(target)) {

                if (first == -1)
                    first = i;

                last = i;
            }
        }

        System.out.println("LINEAR SEARCH");
        System.out.println("First occurrence: " + first);
        System.out.println("Last occurrence: " + last);
        System.out.println("Comparisons: " + comparisons);
        System.out.println();
    }


    // Binary Search
    public static int binarySearch(String[] logs, String target) {

        int low = 0;
        int high = logs.length - 1;
        int comparisons = 0;

        while (low <= high) {

            int mid = (low + high) / 2;
            comparisons++;

            int result = logs[mid].compareTo(target);

            if (result == 0) {

                System.out.println("BINARY SEARCH");
                System.out.println("Found at index: " + mid);
                System.out.println("Comparisons: " + comparisons);
                return mid;
            }

            else if (result < 0)
                low = mid + 1;

            else
                high = mid - 1;
        }

        System.out.println("Account ID not found");
        return -1;
    }


    // Count duplicate occurrences
    public static int countOccurrences(String[] logs, String target, int index) {

        if (index == -1)
            return 0;

        int count = 1;

        int left = index - 1;
        while (left >= 0 && logs[left].equals(target)) {
            count++;
            left--;
        }

        int right = index + 1;
        while (right < logs.length && logs[right].equals(target)) {
            count++;
            right++;
        }

        return count;
    }


    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        String target = "accB";

        // Linear Search
        linearSearch(logs, target);

        // Sort logs for Binary Search
        Arrays.sort(logs);

        System.out.println("Sorted Logs: " + Arrays.toString(logs));
        System.out.println();

        // Binary Search
        int index = binarySearch(logs, target);

        // Count duplicates
        int count = countOccurrences(logs, target, index);

        System.out.println("Total occurrences: " + count);
    }
}