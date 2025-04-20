import java.io.*;
import java.util.*;

public class BubbleSort {

    public static int[] createRandomArray(int arrayLength) {
        Random rand = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = rand.nextInt(101); 
        }
        return array;
    }

    public static void writeArrayToFile(int[] array, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int num : array) {
                writer.println(num);
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
            e.printStackTrace();
        }
    }

    public static int[] readFileToArray(String filename) {
        List<Integer> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    list.add(Integer.parseInt(line));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            e.printStackTrace();
        }
        // Convert list to array
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static void bubbleSort(int[] array) {
        boolean swapped;
        for (int i = 0; i < array.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an option:");
        System.out.println("1 - Generate random array and save to file");
        System.out.println("2 - Read array from file, sort it, and save to new file");
        System.out.print("Enter choice (1 or 2): ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("Enter array length: ");
            int length = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter filename to save the array: ");
            String filename = scanner.nextLine();

            int[] array = createRandomArray(length);
            writeArrayToFile(array, filename);
            System.out.println("Random array written to " + filename);

        } else if (choice == 2) {
            System.out.print("Enter filename to read: ");
            String inputFile = scanner.nextLine();
            System.out.print("Enter filename to save sorted array: ");
            String outputFile = scanner.nextLine();

            int[] array = readFileToArray(inputFile);
            bubbleSort(array);
            writeArrayToFile(array, outputFile);
            System.out.println("Sorted array saved to " + outputFile);

        } else {
            System.out.println("Invalid choice.");
        }
    }
}
