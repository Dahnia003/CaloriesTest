import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class FoodLogScreen {

    private List<FoodEntry> foodList= new ArrayList<>();  // List to store food entries


    public List<FoodEntry> getFoodEntries() {
        return foodList;
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

            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline character after choice
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                scanner.nextLine();  // Clear the invalid input
                continue;  // Skip to the next iteration to prompt again
            }

            switch (choice) {
                case 1:
                    enterFoodManually(scanner);
                    userChoice();
                    break;
                case 2:
                    addFoodFromFile (scanner);
                    userChoice();
                    break;
                case 3:
                    removeFoodEntry(scanner);
                    userChoice();
                    break;
                case 4:
                    updateFoodEntry(scanner);
                    userChoice();
                    break;
                case 5:
                    displayFoodLog();
                    userChoice();
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
    private void userChoice() {
        Scanner scanner = new Scanner(System.in);

        while (true) {  // Loop until a valid input is provided
            System.out.println("1-Stay on Food Screen or 2-Navigate to Exercise Screen?");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());  // Try to parse input to an integer
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter 1 or 2.");
                continue;  // If input is invalid, ask again
            }

            if (choice == 1) {
                // Stay on the exercise screen (no action needed)
                break;  // Exit loop as the user made a valid choice
            } else if (choice == 2) {
                goToExerciseScreen();  // Navigate to Progress Screen
                break;  // Exit loop after navigating
            } else {
                System.out.println("Invalid option. Please try again.");  // If the input is neither 1 nor 2
            }
        }
    }

    private void goToExerciseScreen() {
        ExerciseLogScreen exerciseLogScreen = new ExerciseLogScreen();
        exerciseLogScreen.run2();
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
        FoodEntry foodEntry = new FoodEntry(foodID, foodName, calories, carbs,
                                            protein, fat, mealType, mealDate);
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
             continue;
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
//---------------------------------------------


   // now will create validation methods for addFoodFromFile

    private boolean isValidFoodID(String foodID) {
        // Check if the exercise ID matches the required pattern (e.g., E0000000)
        if (!foodID.matches("^F\\d{7}$")) {
            System.out.println("Error: Invalid Exercise ID format! It must follow the format E0000000.");
            return false;  // Invalid format
        }
        // Check if the exercise ID already exists in the list
        for (FoodEntry food : foodList) {
            if (food.getFoodID().equalsIgnoreCase(foodID)) {
                System.out.println("Error: Food ID " + foodID + " already exists. Please use a unique ID.");
                return false;  // ID already exists in the list
            }
        }
        // ID is valid and does not already exist
        return true;
    }

    private boolean isValidFloat(float carbs) {
        return carbs> 0;
    }

    private boolean isValidMealType(String mealType) {
        List<String> validMealTypes = List.of("Breakfast", "Lunch", "Dinner", "Snack", "Other");
        return validMealTypes.contains(mealType);
    }

    private boolean isValidDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        try {
            LocalDate date = LocalDate.parse(dateString, formatter);
            return true;  // Valid date
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + dateString);
            return false;
        }
    }


    private void addFoodFromFile(Scanner scanner) {
        System.out.print("Enter the file name to load exercises: ");
        String fileName = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }


                String[] data = line.split("-");
                if (data.length == 8) {
                    String foodID = data[0].trim();
                    String foodName = data[1].trim();
                    float calories = Float.parseFloat(data[2].trim());
                    float carbs= Float.parseFloat(data[3].trim());
                    float protein = Float.parseFloat(data[4].trim());
                    float fat = Float.parseFloat(data[5].trim());
                    String mealType = data[6].trim();

                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    LocalDate date = LocalDate.parse(data [7]. trim(), dateFormatter);

                    // Validate fields using the new methods
                    if (!isValidFoodID(foodID)) {
                        continue;  // Skip this entry if the ID is invalid or already exists
                    }
                    if (!isValidFloat(carbs)){
                        continue;
                    }

                    if (!isValidMealType(mealType)) {
                        System.out.println("Skipping invalid meal type: " + mealType);
                        continue;  // Skip this entry if the intensity is invalid
                    }
                    if (!isValidDate(data[7].trim())) {
                        System.out.println("Skipping invalid date: " + date);
                        continue;  // Skip this entry if the date is invalid
                    }


                        // Create and add ExerciseEntry to the list
                        FoodEntry foodEntry = new FoodEntry(foodID, foodName, calories, carbs, protein, fat, mealType, date);
                        foodList.add(foodEntry);
                        System.out.println("Food log(s) added successfully!");
                    } else {
                        System.out.println("Skipping invalid line (incorrect number of fields): " + line);
                    }
                } displayFoodLog();

        }catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }



    private void removeFoodEntry(Scanner scanner) {
        System.out.print("Enter the Food ID to remove: ");
        String foodID = scanner.nextLine().trim();

        Iterator<FoodEntry> iterator = foodList.iterator();
        boolean removed = false;

        // Iterate through the list and find the matching food entry
        while (iterator.hasNext()) {
            FoodEntry food = iterator.next();
            if (food.getFoodID().equals(foodID)) {
                iterator.remove();  // Remove the food entry using the iterator
                removed = true;
                break;  // Exit the loop once the food entry is removed
            }
        }

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
        String foodID = scanner.nextLine().trim();

        if (foodID.isEmpty()) {
            System.out.println("Food ID is invalid or empty. Update failed.");
            return null;  // Return null if the ID is invalid
        }

        // Search for the food entry in the list
        Iterator<FoodEntry> iterator = foodList.iterator();
        FoodEntry foodToUpdate = null;

        // Iterate through the list to find the matching FoodEntry
        while (iterator.hasNext()) {
            FoodEntry food = iterator.next();
            if (food.getFoodID().trim().equals(foodID)) {
                foodToUpdate = food;
                break;  // Stop the loop once the food entry is found
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
