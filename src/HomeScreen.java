import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *Dahnia Belizaire
 *CEN 3024C- Software Developement 1
 * February 23, 2025
 * Name:HomeScreen
 * This class represents a home menu where users can:
 * 1. Access food log screen
 * 2. Access exercise log screen
 * 3. Access progress screen
 * 4. Exit
 */
public class HomeScreen {

    public FoodLogScreen foodLogScreen;
    public ExerciseLogScreen exerciseLogScreen;
    public ProgressScreen progressScreen;
    private Scanner scanner;
    private List<FoodEntry> foodList;
    private List<ExerciseEntry> exerciseList;


    public HomeScreen() {
        this.foodLogScreen = new FoodLogScreen();
        this.exerciseLogScreen = new ExerciseLogScreen();
        this.scanner = new Scanner(System.in);
        this.foodList = new ArrayList<>();
        this.exerciseList = new ArrayList<>();
    }
    /**
     * Method Name: main
     * Purpose: This is the entry point of the program.
     *           It initializes `HomeScreen` object and calls the `displayMenu` method to display the menu options for the user.
     * Arguments: String[] args
     * Return Value: void
     */
    public static void main(String[] args) {
        HomeScreen homeScreen = new HomeScreen();
        homeScreen.displayMenu();
    }
    /**
     * Name: displayMenu
     * Purpose: Display menu with option and proceed accordingly with user choice
     * Arguments:none
     * return value: void
     */
    public void displayMenu() {

        while (true) {
            System.out.println("Welcome to the Home Screen");
            System.out.println("1. Food Entry");
            System.out.println("2. Exercise Log");
            System.out.println("3. View Progress");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice= -1;
            boolean validChoice = false;
            while (!validChoice) {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine();  // Consume newline character after choice

                    // Check if the choice is within the valid range
                    if (choice >= 1 && choice <= 4) {
                        validChoice = true;
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    }
                } else {
                    // If input is not an integer, ask again
                    System.out.println("Invalid input. Please enter a valid number between 1 and 4.");
                    scanner.nextLine();  // Clear the invalid input
                }
            }

            switch (choice) {
                case 1:
                    foodLogScreen.run();
                break;
                case 2:
                        exerciseLogScreen.run2();  // Show progress
                    break;
                case 3:
                    if (progressScreen == null) {
                        progressScreen = new ProgressScreen(); // Initialize if null
                    }
                    progressScreen.displayProgress();  // Show progress even if empty
                    break;
                case 4:
                    System.out.println("Exiting system. Goodbye!");
                    System.exit(0); // Terminates the program with a successful exit code
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
    }


}
