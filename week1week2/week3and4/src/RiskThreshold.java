import java.util.Arrays;

public class RiskThreshold {

    // Linear Search
    public static void linearSearch(int[] arr, int target) {

        int comparisons = 0;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {

            comparisons++;

            if (arr[i] == target) {
                System.out.println("LINEAR SEARCH");
                System.out.println("Threshold found at index: " + i);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("LINEAR SEARCH");
            System.out.println("Threshold not found");
        }

        System.out.println("Comparisons: " + comparisons);
        System.out.println();
    }

    // Binary Search for floor and ceiling
    public static void binarySearchFloorCeil(int[] arr, int target) {

        int low = 0;
        int high = arr.length - 1;

        int floor = -1;
        int ceil = -1;

        int comparisons = 0;

        while (low <= high) {

            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] == target) {

                floor = arr[mid];
                ceil = arr[mid];
                break;
            }

            if (arr[mid] < target) {

                floor = arr[mid];
                low = mid + 1;
            }

            else {

                ceil = arr[mid];
                high = mid - 1;
            }
        }

        System.out.println("BINARY SEARCH");
        System.out.println("Floor value: " + floor);
        System.out.println("Ceiling value: " + ceil);
        System.out.println("Comparisons: " + comparisons);

        System.out.println("Insertion index for new client: " + low);
    }

    public static void main(String[] args) {

        int[] risks = {10, 25, 50, 100};

        int target = 30;

        System.out.println("Sorted Risk Bands: " + Arrays.toString(risks));
        System.out.println("Target Threshold: " + target);
        System.out.println();

        // Linear Search
        linearSearch(risks, target);

        // Binary Search Floor/Ceiling
        binarySearchFloorCeil(risks, target);
    }
}