package Learn;

import java.io.*;
import java.util.Scanner;
import java.nio.file.*;

public class Main {
    private static final String FILE_NAME = "sample.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nFile Operations:");
            System.out.println("1. Write to File");
            System.out.println("2. Read from File");
            System.out.println("3. Append to File");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {  // Prevents invalid input errors
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> writeFile(scanner);
                case 2 -> readFile();
                case 3 -> appendFile(scanner);
                case 4 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private static void writeFile(Scanner scanner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            System.out.print("Enter content to write to the file: ");
            String content = scanner.nextLine();
            writer.write(content);
            System.out.println("Content written successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    private static void readFile() {
        if (!Files.exists(Paths.get(FILE_NAME))) {
            System.out.println("File does not exist. Please write to the file first.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            System.out.println("\nFile Contents:");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    private static void appendFile(Scanner scanner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            System.out.print("Enter content to append to the file: ");
            String content = scanner.nextLine();
            writer.write("\n" + content);
            System.out.println("Content appended successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while appending to the file: " + e.getMessage());
        }
    }
}
