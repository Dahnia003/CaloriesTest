import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public void displayMenu() {

        while (true) {
            System.out.println("Welcome to the Home Screen");
            System.out.println("1. Food Entry");
            System.out.println("2. Exercise Log");
            System.out.println("3. View Progress");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    foodLogScreen.run();
                break;
                case "2":
                    exerciseLogScreen.run2();
                    break;
                case "3":
                    ProgressScreen progressScreen = new ProgressScreen();
                    progressScreen.displayProgress();
                    break;
                case "4":
                    System.out.println("Exiting system. Goodbye!");
                    System.exit(0); // Terminates the program with a successful exit code
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
    }
    public static void main(String[] args) {
        HomeScreen homeScreen = new HomeScreen();
        homeScreen.displayMenu();
    }

}
