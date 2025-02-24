import java.util.HashSet;
import java.util.Set;
import java.time.LocalDate;

/**
 * Author: Dahnia Belizaire
 * Course: CEN 3024C
 * February 23, 2025
 * Class Name: FoodEntry
 * This class is responsible for representing a food entry in a food log application.
 * Attributes: food ID, food name, calories, carbs, protein, fat, meal type,date.
 * The class provides getter and setter methods to access and modify these attributes.
 * and helps in managing food entries for users.
 */
public class FoodEntry {
    public static final Set<String> usedFoodIDs = new HashSet<>();
    public String foodID;
    public String foodName;

    public float calories;
    public float carbs;
    public float protein;
    public float fat;
    public String mealType;
    public LocalDate mealDate;


    // Constructor to initialize a new FoodEntry
    public FoodEntry(String foodID,String foodName, float calories, float carbs, float protein, float fat, String mealType, LocalDate mealDate) {
        this.foodID = foodID;
        this.foodName = foodName;

        this.calories = calories;
        this.carbs = carbs;
        this.protein = protein;
        this.fat = fat;
        this.mealType = mealType;
        this.mealDate = mealDate;
    }

    // Getter

    public String getFoodID() {
        return foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public float getCalories() {
        return calories;
    }

    public float getCarbs() {
        return carbs;
    }

    public float getProtein() {
        return protein;
    }

    public float getFat() {
        return fat;
    }

    public String getMealType() {
        return mealType;
    }

    public LocalDate getMealDate() {
        return mealDate;
    }



    // Setter methods to update values
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public void setMealDate(LocalDate mealDate) {
        this.mealDate = mealDate;
    }




    @Override
    public String toString() {
        return foodID+ "-" + foodName + "-" + calories + "-" + carbs + "-" + protein + "-" + fat + "-" + mealType + "-" + mealDate;
    }



}
