import java.time.LocalDate;
import java.util.*;
/**
 *Dahnia Belizaire
 *CEN 3024C- Software Developement 1
 * February 23, 2025
 * Name: ProgressScreen
 * This class represents a progress tracking system where users can:
 * 1. See total calories intake
 * 2. See total calories expenditure
 * 4. Navigate back to other screens
 */

public class ProgressScreen {
    private final FoodLogScreen foodLogScreen;
    private final ExerciseLogScreen exerciseLogScreen;
    private HomeScreen homeScreen;
    private final List<FoodEntry> foodList;  // List to store food entries
    private final List<ExerciseEntry> exerciseList;  // List to store exercise entries

    // Constructor to initialize lists and load data from FoodLogScreen and ExerciseLogScreen
    public ProgressScreen() {
        this.foodLogScreen = new FoodLogScreen();  // Initialize with the FoodLogScreen
        this.exerciseLogScreen = new ExerciseLogScreen();// Initialize with the ExerciseLogScreen
        this.homeScreen = new HomeScreen();

        // Load the food and exercise entries from respective screens
        this.foodList = foodLogScreen.getFoodEntries();
        this.exerciseList = exerciseLogScreen.getExerciseEntries();

    }
    /**
     * Method Name: main
     * Purpose: This is the entry point of the class.
     *           It initializes `ProgressScreen` object and
     *           calls the `displayProgress` method to display the options for the user.
     * Arguments: String[] args
     * Return Value: void
     */
    public static void main(String[] args) {
        ProgressScreen progressScreen = new ProgressScreen();
        progressScreen.displayProgress();
    }
    /**
     * Name: displayProgress
     * Purpose:display features of the progress screen
     * Arguments:none
     * return value: void
     */

    public void displayProgress() {

        while (true) {
            // Create a list to store unique dates from both food and exercise logs
            List<LocalDate> allDates = new ArrayList<>();

            // Add all food entry dates
            for (FoodEntry food : foodList) {
                if (!allDates.contains(food.getMealDate())) {
                    allDates.add(food.getMealDate());
                }
            }

            // Add all exercise entry dates
            for (ExerciseEntry exercise : exerciseList) {
                if (!allDates.contains(exercise.getExerciseDate())) {
                    allDates.add(exercise.getExerciseDate());
                }
            }

            // Sort dates
            allDates.sort(LocalDate::compareTo);

            // Display the progress table header
            System.out.println("Date\t\tFood Entries\t\tTotal Calories\t\tExercise Entries\t\tCalories Burned");
            System.out.println("--------------------------------------------------------------------------------------------------");

            // Display progress for each unique date
            for (LocalDate date : allDates) {
                System.out.print(date + "\t");

                // Display food entries for the date
                StringBuilder foodEntries = new StringBuilder();
                for (FoodEntry food : foodList) {
                    if (food.getMealDate().equals(date)) {
                        foodEntries.append(food.getFoodName()).append(", ");
                    }
                }
                if (foodEntries.length() > 0) {
                    foodEntries.setLength(foodEntries.length() - 2);  // Remove trailing comma
                }
                System.out.print(foodEntries + "\t");

                // Display total calories for the date
                float totalCalories = calculateCaloriesIntakeByDate(date);
                System.out.print(totalCalories + "\t\t");

                // Display exercise entries for the date
                StringBuilder exerciseEntries = new StringBuilder();
                for (ExerciseEntry exercise : exerciseList) {
                    if (exercise.getExerciseDate().equals(date)) {
                        exerciseEntries.append(exercise.getExerciseName()).append(", ");
                    }
                }
                if (exerciseEntries.length() > 0) {
                    exerciseEntries.setLength(exerciseEntries.length() - 2);  // Remove trailing comma
                }
                System.out.print(exerciseEntries + "\t");

                // Display total calories burned for the date
                float totalCaloriesBurned = calculateCaloriesBurnedByDate(date);
                System.out.println(totalCaloriesBurned);
            }

            // Call displayChoice() after showing progress
            displayChoice();
        }
    }
    /**
     * Name: displayChoice
     * Purpose: allow users to got other screens
     * Arguments:none
     * return value: void
     */
    public void displayChoice() {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;

        while (!validInput) {
            // Display options for navigation
            System.out.println("\nOptions:");
            System.out.println("1. Go back to Home Screen");
            System.out.println("2. Go back to Food Log Screen");
            System.out.println("3. Go back to Exercise Log Screen");
            System.out.println("4. Exit the Program");
            System.out.print("Please select an option (1-4): ");

            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline character after choice
                validInput = true;  // Valid input received
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scanner.nextLine();  // Clear the invalid input
            }

            switch (choice) {
                case 1:
                    homeScreen.displayMenu();
                    break;
                case 2:
                    foodLogScreen.run();  // You should make sure that this method is correctly implemented in FoodLogScreen
                    break;
                case 3:
                    exerciseLogScreen.run2();  // You should make sure that this method is correctly implemented in ExerciseLogScreen
                    break;
                case 4:
                    System.out.println("Exiting system. Goodbye!");
                    System.exit(0); // Terminates the program with a successful exit code
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    validInput = false;  // Invalid choice, so keep asking
            }
        }
    }
    /**
     * Name: calculateCaloriesIntakeByDate
     * Purpose:calculate calories intake by date retrieve from foodList
     * Arguments: LocalDate date
     * return value: float
     */
    // Method to calculate total calories for a specific date
    public float calculateCaloriesIntakeByDate(LocalDate date) {
        float totalCalories = 0;
        for (FoodEntry food : foodList) {
            if (food.getMealDate().equals(date)) {
                totalCalories += food.getCalories();
            }
        }
        return totalCalories;
    }

    /**
     * Name: calculateCaloriesBurnedByDate
     * Purpose:calculate calories burned by date retrieve from foodList
     * Arguments: LocalDate date
     * return value: float
     */
    public float calculateCaloriesBurnedByDate(LocalDate date) {
        float totalCaloriesBurned = 0;
        for (ExerciseEntry exercise : exerciseList) {
            if (exercise.getExerciseDate().equals(date)) {
                totalCaloriesBurned += exercise.getCalBurned();
            }
        }
        return totalCaloriesBurned;
    }
}
