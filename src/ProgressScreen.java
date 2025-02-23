import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProgressScreen {
    public FoodLogScreen foodLogScreen;
    public ExerciseLogScreen exerciseLogScreen;
    public List<FoodEntry> foodList;  // List to store food entries
    public List<ExerciseEntry> exerciseList;  // List to store exercise entries

    // Constructor to initialize lists
    public ProgressScreen() {
        this.foodList = foodLogScreen.getFoodEntries();
        this.exerciseList = exerciseLogScreen.getExerciseEntries();
        this.foodLogScreen = new FoodLogScreen();
        this.exerciseLogScreen = new ExerciseLogScreen();
    }
    public static void main(String[] args) {
        ProgressScreen progressScreen = new ProgressScreen();
        progressScreen.displayProgress();
    }
    public void displayProgress() {
        Scanner scanner = new Scanner(System.in);

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
                float totalCalories = getTotalCaloriesForDate(date);
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
                float totalCaloriesBurned = getTotalCaloriesBurnedForDate(date);
                System.out.println(totalCaloriesBurned);
            }

            // Display options for navigation
            System.out.println("\nOptions:");
            System.out.println("1. Go back to Home Screen");
            System.out.println("2. Go back to Food Log Screen");
            System.out.println("3. Go back to Exercise Log Screen");
            System.out.println("4. Exit the Program");
            System.out.print("Please select an option (1-4): ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character after the choice

            switch (choice) {
                case 1:
                    HomeScreen homeScreen = new HomeScreen();
                    homeScreen.displayMenu();
                    break;
                case 2:
                    foodLogScreen.run();
                    break;
                case 3:
                    exerciseLogScreen.run2();
                    break;
                case 4:
                    System.out.println("Exiting system. Goodbye!");
                    System.exit(0); // Terminates the program with a successful exit code
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    // Method to calculate total calories for a specific date
    public float getTotalCaloriesForDate(LocalDate date) {
        float totalCalories = 0;
        for (FoodEntry food : foodList) {
            if (food.getMealDate().equals(date)) {
                totalCalories += food.getCalories();
            }
        }
        return totalCalories;
    }

    // Method to calculate total calories burned for a specific date
    public float getTotalCaloriesBurnedForDate(LocalDate date) {
        float totalCaloriesBurned = 0;
        for (ExerciseEntry exercise : exerciseList) {
            if (exercise.getExerciseDate().equals(date)) {
                totalCaloriesBurned += exercise.getCalBurned();
            }
        }
        return totalCaloriesBurned;
    }

    // Method to retrieve all food entries and exercises by date


}
