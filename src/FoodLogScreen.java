import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodLogScreen {

    private List<FoodEntry> foodList;  // List to store food entries

    public FoodLogScreen() {
        // Initialize the food list to hold food entries
        foodList = new ArrayList<>();
    }
    public List<FoodEntry> getFoodEntries() {
        return this.foodList;
    }
    public static void main(String[] args) {
        FoodLogScreen foodLogScreen = new FoodLogScreen();
        foodLogScreen.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Food Log Menu:");
            System.out.println("1. Add Food Manually");
            System.out.println("2. Add From a text file");
            System.out.println("3. Remove Food Entry");
            System.out.println("4. Update an entry");
            System.out.println("5. Display Food Log");
            System.out.println("6. Exit");
            System.out.print("Please select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character after choice

            switch (choice) {
                case 1:
                    enterFoodManually(scanner);
                    new ExerciseLogScreen().run2();
                    break;
                case 2:
                    addFoodFromFile (scanner);
                    new ExerciseLogScreen().run2();
                    break;
                case 3:
                    removeFoodEntry(scanner);
                    break;
                case 4:
                    updateFoodEntry(scanner);
                    break;
                case 5:
                    displayFoodLog();
                    break;
                case 6:
                    System.out.println("Exiting system. Goodbye!");
                    System.exit(0); // Terminates the program with a successful exit code
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Prompts the user to enter food information manually.
     * Displays a form for input.
     */
    private FoodEntry enterFoodManually(Scanner scanner) {
        System.out.print("Enter a Food ID F0000000 (alphanumeric, 7 characters long with the prefix F): ");
        String foodID = getValidFoodID(scanner);

        System.out.print("Please enter a name for your food entry: ");
        String foodName = scanner.nextLine();
        if (foodName.isEmpty()) {
            System.out.println("Food name cannot be empty.");
            return null;
        }



        float calories = getValidFloat(scanner, "Enter the number of calories: ");
        float carbs = getValidFloat(scanner, "Enter the number of carbs (in grams): ");
        float fat = getValidFloat(scanner, "Enter the number of fat (in grams): ");
        float protein = getValidFloat(scanner, "Enter the number of protein (in grams): ");

        String mealType = getValidMealType(scanner);

        System.out.print("Enter a date (MM/dd/yyyy): ");
        LocalDate mealDate = validateAndParseDate(scanner);
        if (mealDate == null) {
            System.out.println("Invalid date format.");
            return null;  // Return null if the date format is invalid
        }
        // Create food entry and add it to the list
        FoodEntry foodEntry = new FoodEntry(foodID, foodName, calories, carbs, protein, fat, mealType, mealDate);
        foodList.add(foodEntry);
        System.out.println("Food entry added successfully!");
    return foodEntry;
    }

    private String getValidFoodID(Scanner scanner) {
        String foodID;
        while (true) {
            foodID = scanner.nextLine().trim();
            if (foodID.isEmpty()) {
                System.out.println("Food ID cannot be empty.");
                continue;
            }
            if (foodID.matches("^F\\d{7}")) {
                return foodID;
            } else {
                System.out.println("Invalid ID! Please enter an alphanumeric ID with exactly 7 characters.");
            }
        }
    }

    private float getValidFloat(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();

         if (input.isEmpty()) {
             System.out.println("Field cannot be empty: ");
             return Float.parseFloat(scanner.nextLine());
         }
            try {
                return Float.parseFloat(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid entry! Please enter a valid float.");
            }
        }
    }
    private float getValidFloat2(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                System.out.println("Field cannot be empty: ");
                return Float.parseFloat(scanner.nextLine());
            }
            try {
                return Float.parseFloat(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid entry! Please enter a valid float.");
            }
        }
    }

    private String getValidMealType(Scanner scanner) {
        List<String> validMealTypes = List.of("Breakfast", "Lunch", "Dinner", "Snack", "Other");
        while (true) {
            System.out.print("Enter meal type (Breakfast, Lunch, Dinner, Snack, or Other): ");
            String mealType = scanner.nextLine().trim();

            if (mealType.isEmpty()) {
                System.out.println("Meal type cannot be empty.");
                continue;
            }
            if (validMealTypes.contains(mealType)) {
                return mealType;
            } else {
                System.out.println("Invalid meal type. Please enter a valid meal type.");
            }
        }
    }

    private LocalDate validateAndParseDate(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        while (true) {
            String dateString = scanner.nextLine();
            if (dateString.isEmpty()) {
                System.out.println("Date cannot be empty.");
                continue;
            }
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter a valid date in MM/dd/yyyy format.");
            }
        }
    }

    private void addFoodFromFile(Scanner scanner) {
        System.out.print("Enter the file name to load exercises: ");
        String fileName = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Parse and add each exercise from file (assumed format of file is defined)

                String[] data = line.split("-");
                if (data.length == 6) {
                    String foodID = data[0].trim();
                    String foodName = data[1].trim();
                    float calories = Float.parseFloat(data[2]. trim());
                    float carbs = Float.parseFloat(data [3].trim());
                    float protein = Float.parseFloat(data[4].trim());
                    float fat = Float.parseFloat(data[5].trim());
                    String mealType = data[6].trim();
                    LocalDate date = LocalDate.parse(data [7]. trim());


                    // Validate and process the duration
                    foodID = getValidFoodID(scanner);
                    calories = getValidFloat2(scanner);
                    carbs = getValidFloat2(scanner);
                    protein = getValidFloat2(scanner);
                    fat = getValidFloat2(scanner);


                    // Create and add ExerciseEntry to the list
                    FoodEntry foodEntry = new FoodEntry(foodID, foodName, calories, carbs, protein,fat, mealType, date);
                    foodList.add(foodEntry);
                } else {

                    System.out.println("Skipping invalid line (incorrect number of fields): " + line);

                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    private void removeFoodEntry(Scanner scanner) {
        System.out.print("Enter the Food ID to remove: ");
        String foodID = scanner.nextLine().trim();

        boolean removed = foodList.removeIf(food -> food.getFoodID().equalsIgnoreCase(foodID));

        if (removed) {
            System.out.println("Food entry removed successfully.");
        } else {
            System.out.println("Food ID not found.");
        }
    }

    private void displayFoodLog() {
        if (foodList.isEmpty()) {
            System.out.println("No food entries available.");
        } else {
            System.out.println("Food Log:");
            for (FoodEntry food : foodList) {
                System.out.println(food);
            }
        }
    }


    private FoodEntry updateFoodEntry(Scanner scanner) {
        System.out.print("Enter the Food ID to update (e.g., F000000): ");
        String foodID = getValidFoodID(scanner);  // Validate the Food ID
        if (foodID == null) {
            System.out.println("Food ID is invalid or empty. Update failed.");
            return null;  // Return null if the ID is invalid
        }

        // Search for the food entry in the list
        FoodEntry foodToUpdate = null;
        for (FoodEntry food : foodList) {
            if (food.getFoodID().equals(foodID)) {
                foodToUpdate = food;
                break;
            }
        }

        if (foodToUpdate == null) {
            System.out.println("Food ID not found. Update failed.");
            return null;  // Return null if the food entry is not found
        }

        // Now, prompt the user for the field they want to update
        //User will not be able to update ID
        System.out.println("Food entry found.");
        System.out.println( foodToUpdate.getFoodID()+ "- " + foodToUpdate.getFoodName() +"- "
                          + foodToUpdate.getCalories()+ "- " + foodToUpdate.getCarbs() + "- "
                          + foodToUpdate.getProtein() + "- " + foodToUpdate.getFat() + "- "
                          + foodToUpdate.getMealType()+ "- " + foodToUpdate.getMealDate());



        System.out.println(" Which field would you like to update?");
        System.out.println("1. Name");
        System.out.println("2. Calories");
        System.out.println("3. Carbs");
        System.out.println("4. Fat");
        System.out.println("5. Protein");
        System.out.println("6. Meal Type");
        System.out.println("7. Date");
        System.out.print("Enter your choice (1-7): ");
        int updateChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline after choice

        switch (updateChoice) {
            case 1:
                System.out.print("Enter new food name: ");
                String newName = scanner.nextLine();
                if (newName.isEmpty()) {
                    System.out.println("Food name cannot be empty.");
                    return null;  // Return null if the name is invalid
                }
                foodToUpdate.setFoodName(newName);
                break;
            case 2:
                foodToUpdate.setCalories(getValidFloat(scanner, "Enter new calories: "));
                break;
            case 3:
                foodToUpdate.setCarbs(getValidFloat(scanner, "Enter new carbs (in grams): "));
                break;
            case 4:
                foodToUpdate.setFat(getValidFloat(scanner, "Enter new fat (in grams): "));
                break;
            case 5:
                foodToUpdate.setProtein(getValidFloat(scanner, "Enter new protein (in grams): "));
                break;
            case 6:
                foodToUpdate.setMealType(getValidMealType(scanner));
                break;
            case 7:
                System.out.print("Enter new date (MM/dd/yyyy): ");
                LocalDate newDate = validateAndParseDate(scanner);
                if (newDate != null) {
                    foodToUpdate.setMealDate(newDate);
                }
                break;
            default:
                System.out.println("Invalid option. Update failed.");
                return null;  // Return null if the user enters an invalid option
        }

        System.out.println("Food entry updated successfully!");
        return foodToUpdate;  // Return the updated food entry
    }




}
