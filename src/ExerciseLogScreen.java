import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class ExerciseLogScreen {

    private final List<ExerciseEntry> exerciseList= new ArrayList<>();  // List to store exercise entries

    //Method to get exercise entries from ProgressScreen
    public List<ExerciseEntry> getExerciseEntries() {
        return exerciseList;
    }

    public static void main(String[] args) {
        ExerciseLogScreen exerciseLogScreen = new ExerciseLogScreen();
        exerciseLogScreen.run2();
    }

    public void run2() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Exercise Log Menu:");
            System.out.println("1. Add Exercise Manually");
            System.out.println("2. Add Exercise from File");
            System.out.println("3. Remove Exercise Entry");
            System.out.println("4. Update Exercise Entry");
            System.out.println("5. Display all exercise entries");
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
                    addExerciseManually(scanner);
                    userChoice();

                    break;
                case 2:
                    addExerciseFromFile(scanner);
                  userChoice();

                    break;
                case 3:
                    removeExerciseEntry(scanner);
                    userChoice();
                    break;
                case 4:
                    updateExerciseEntry(scanner);
                    userChoice();
                    break;
                case 5:
                    displayExerciseLog();
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

private void goToProgressScreen() {
    ProgressScreen progressScreen = new ProgressScreen();
    progressScreen.displayProgress();
}

    private void userChoice() {
        Scanner scanner = new Scanner(System.in);

        while (true) {  // Loop until a valid input is provided

            System.out.println("1-Stay on Exercise Screen or 2-Navigate to Progress Screen?");

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
                goToProgressScreen();  // Navigate to Progress Screen
                break;  // Exit loop after navigating
            } else {
                System.out.println("Invalid option. Please try again.");  // If the input is neither 1 nor 2
            }
        }
    }

    public ExerciseEntry addExerciseManually(Scanner scanner) {

        System.out.print("Enter Exercise ID (e.g., E0000000): ");
        String exerciseID = getValidExerciseID(scanner);

        System.out.print("Enter Exercise Name: ");
        String exerciseName = scanner.nextLine().trim();

        if (exerciseName.isEmpty()) {
            System.out.println("Exercise name cannot be empty.");
            return null;
        }

        System.out.print("Enter Exercise Type: ");
        String exerciseType = scanner.nextLine().trim();

        int duration = getValidInt(scanner, "Enter exercise duration (minutes): ");
        String intensity = getValidIntensity(scanner);

        System.out.print("Enter date of exercise (MM/DD/YYYY): ");
        LocalDate exerciseDate = validateAndParseDate(scanner);

        System.out.print("Enter time of exercise (hh:mm AM/PM): ");
        LocalTime exerciseTime = validateAndParseTime(scanner);

        float calBurned = calculateCaloriesBurned(duration, intensity);

        ExerciseEntry exerciseEntry = new ExerciseEntry(exerciseName, exerciseID, exerciseType,
                                     duration, intensity, exerciseDate, exerciseTime, calBurned);
        exerciseList.add(exerciseEntry);
        System.out.println("Exercise Added Successfully.");
        return exerciseEntry;

    }

    private String getValidExerciseID(Scanner scanner) {
        String exerciseID;
        while (true) {
            exerciseID = scanner.nextLine().trim();
            if (exerciseID.isEmpty()) {
                System.out.println("Exercise ID cannot be empty.");
                continue;
            }
            if (exerciseID.matches("^E\\d{7}$")) {
                return exerciseID;
            } else {
                System.out.println("Invalid Exercise ID! Please enter an ID with the format E0000000.");
            }
        }
    }

    private int getValidInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                System.out.println("Field cannot be empty: ");
                continue;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    private String getValidIntensity(Scanner scanner) {
        String intensity;
        List<String> validIntensities = List.of("Low", "Medium", "High", "Mixed");
        while (true) {
            System.out.print("Enter exercise intensity (Low, Medium, High, Mixed): ");
            intensity = scanner.nextLine().trim();
            if (intensity.isEmpty()) {
                System.out.println("Meal type cannot be empty.");
                continue;
            }
            if (validIntensities.contains(intensity)) {
                return intensity;
            } else {
                System.out.println("Invalid intensity. Please enter Low, Medium, High, or Mixed.");
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

    private LocalTime validateAndParseTime(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        while (true) {
            String timeString = scanner.nextLine();
            if (timeString.isEmpty()) {
                System.out.println("Time cannot be empty.");
                continue;
            }
            try {
                return LocalTime.parse(timeString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please enter a valid time in hh:mm a format.");
            }
        }
    }

    public float calculateCaloriesBurned(int duration, String intensity) {
        float caloriesPerMinute = 0;
        switch (intensity.toLowerCase()) {
            case "low":
                caloriesPerMinute = 5.0f;
                break;
            case "medium":
                caloriesPerMinute = 8.0f;
                break;
            case "high":
                caloriesPerMinute = 12.0f;
                break;
            case "mixed":
                caloriesPerMinute = 10.0f;
                break;
        }
        return caloriesPerMinute * duration;
    }
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
 //now writing boolean methods to validate addFromTextFile

  private boolean isValidExerciseID(String exerciseID) {
    // Check if the exercise ID matches the required pattern (e.g., E0000000)
    if (!exerciseID.matches("^E\\d{7}$")) {
        System.out.println("Error: Invalid Exercise ID format! It must follow the format E0000000.");
        return false;  // Invalid format
    }
    // Check if the exercise ID already exists in the list
    for (ExerciseEntry exercise : exerciseList) {
        if (exercise.getExerciseID().equalsIgnoreCase(exerciseID)) {
            System.out.println("Error: Exercise ID " + exerciseID + " already exists. Please use a unique ID.");
            return false;  // ID already exists in the list
        }
    }
    // ID is valid and does not already exist
    return true;
}

    private boolean isValidInt(int duration) {
        return duration > 0;  // Duration must be positive
    }

private boolean isValidIntensity(String intensity) {
    List<String> validIntensities = List.of("Low", "Medium", "High", "Mixed");
    return validIntensities.contains(intensity);  // Check if intensity is valid
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

private boolean isValidTime(String timeString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
    try {
        LocalTime time = LocalTime.parse(timeString, formatter);
        return true;  // Valid time
    } catch (DateTimeParseException e) {
        System.out.println("Invalid time format: " + timeString);
        return false;
    }
}

   public void addExerciseFromFile(Scanner scanner) {
        System.out.print("Enter the file name to load exercises: ");
        String fileName = scanner.nextLine();



       try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }
                String[] data = line.split("-");
                if (data.length == 7) {
                    String exerciseID = data[0].trim();
                    String type = data[1].trim();
                    String name = data[2].trim();
                    int duration = Integer.parseInt(data[3]. trim());
                    String intensity = data [4].trim();

                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    LocalDate date = LocalDate.parse(data [5]. trim(), dateFormatter);

                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
                    LocalTime time = LocalTime.parse(data[6].trim(), timeFormatter);

                    // Validate and process the duration
                    if (!isValidExerciseID(exerciseID)) {
                        continue;  // Skip this entry if the ID is invalid or already exists
                    }
                    if (!isValidIntensity(intensity)) {
                        System.out.println("Skipping invalid intensity: " + intensity);
                        continue;  // Skip this entry if the intensity is invalid
                    }
                    if (!isValidTime(data[6].trim())) {
                        System.out.println("Skipping invalid time: " + time);
                        continue;  // Skip this entry if the time is invalid
                    }
                    if (!isValidDate(data[5].trim())) {
                        System.out.println("Skipping invalid date: " + date);
                        continue;  // Skip this entry if the date is invalid
                    }

                    if (!isValidInt(duration)) {
                        continue;  // Skip this entry if the duration is invalid
                    }

                    // Calculate calories burned (we don't get it from the file)
                    float calBurned = calculateCaloriesBurned(duration, intensity);

                    // Create and add ExerciseEntry to the list
                    ExerciseEntry exerciseEntry = new ExerciseEntry(name, exerciseID, type, duration, intensity, date, time, calBurned);
                    exerciseList.add(exerciseEntry);


                } else {

                    System.out.println("Skipping invalid line (incorrect number of fields): " + line);

                }
            } displayExerciseLog();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
//--------------------------------------------------------------
    //-----------------------------------------------------------
public boolean removeExerciseEntry(Scanner scanner) {
    System.out.print("Enter the Exercise ID to remove: ");
    String exerciseID = scanner.nextLine().trim();  // Ensure there's no leading/trailing whitespace

    // Create an iterator for the exercise list
    Iterator<ExerciseEntry> iterator = exerciseList.iterator();
    boolean removed = false;

    // Iterate over the list
    while (iterator.hasNext()) {
        ExerciseEntry exercise = iterator.next();

        // Compare the exercise ID
        if (exercise.getExerciseID().equals(exerciseID)) {
            iterator.remove();  // Remove the exercise entry from the list
            removed = true;
            System.out.println("Exercise entry removed successfully.");
            break;  // Exit the loop once the exercise is removed
        }
    }

    // If no match is found, print an error message
    if (!removed) {
        System.out.println("Exercise ID not found.");
    }

    return removed;
}


    private boolean updateExerciseEntry(Scanner scanner) {
        System.out.print("Enter the Exercise ID to update: ");
        String exerciseID = scanner.nextLine().trim();

        Iterator<ExerciseEntry> iterator = exerciseList.iterator();
        ExerciseEntry exerciseToUpdate = null;

        // Iterate over the list to find the exercise entry with the matching ID
        while (iterator.hasNext()) {
            ExerciseEntry exercise = iterator.next();
            if (exercise.getExerciseID().equals(exerciseID)) {
                exerciseToUpdate = exercise;
                break;  // Exit loop once the exercise is found
            }
        }

        if (exerciseToUpdate == null) {
            System.out.println("Exercise ID not found.");
            return false;
        }

        System.out.println("Exercise found. Which field would you like to update?");
        System.out.println("1. Name");
        System.out.println("2. Type");
        System.out.println("3. Duration");
        System.out.println("4. Intensity");
        System.out.println("5. Date");
        System.out.println("6. Time"); // user will not be able to update calories burned because it will be recalculated automatically
        System.out.print("Enter your choice (1-6): ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline character after choice

        boolean isUpdated = false;

        switch (choice) {
            case 1:
                System.out.print("Enter new name: ");
                exerciseToUpdate.setExerciseName(scanner.nextLine());
                isUpdated = true;
                break;
            case 2:
                System.out.print("Enter new type: ");
                exerciseToUpdate.setExerciseType(scanner.nextLine());
                isUpdated = true;
                break;
            case 3:
                int newDuration = getValidInt(scanner, "Enter new duration (in minutes): ");
                exerciseToUpdate.setDuration(newDuration);
                // Recalculate calories based on updated duration and current intensity
                exerciseToUpdate.setCalBurned(calculateCaloriesBurned(newDuration, exerciseToUpdate.getIntensity()));
                break;
            case 4:
                String newIntensity = getValidIntensity(scanner);
                exerciseToUpdate.setIntensity(newIntensity);
                // Recalculate calories when intensity changes
                exerciseToUpdate.setCalBurned(calculateCaloriesBurned(exerciseToUpdate.getDuration(), newIntensity));
                break;
            case 5:
                System.out.print("Enter new date (MM/DD/YYYY): ");
                exerciseToUpdate.setExerciseDate(validateAndParseDate(scanner));
                isUpdated = true;
                break;
            case 6:
                System.out.print("Enter new time (hh:mm AM/PM): ");
                exerciseToUpdate.setExerciseTime(validateAndParseTime(scanner));
                isUpdated = true;
                break;
            default:
                System.out.println("Invalid option. Update failed.");
                return false;
        }
        if (isUpdated) {
            System.out.println("Exercise entry updated successfully!");
        }
        exerciseList.add(exerciseToUpdate);
        displayExerciseLog();
        return isUpdated;
    }

    private void displayExerciseLog() {
        if (exerciseList.isEmpty()) {
            System.out.println("No exercise entries available.");
        } else {
            System.out.println("Exercise Log:");
            for (ExerciseEntry exercise : exerciseList) {
                System.out.println(exercise);  // Assuming `toString()` method is properly implemented in ExerciseEntry
            }
        }
    }



}




