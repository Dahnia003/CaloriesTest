
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Author: Dahnia Belizaire
 * Course: CEN 3024C
 * February 23, 2025
 * Class Name: ExerciseEntry
 * This class is responsible for representing an exercise entry in a exercise log application.
 * Attributes: exercise ID, exercise name, exercise type, duration, intensity, time,date, calories burned.
 * The class provides getter and setter methods to access and modify these attributes.
 * and helps in managing exercise entries for users.
 */


public class ExerciseEntry {


    private String exerciseID;
    private String exerciseName;
    private String exerciseType;
    private int duration;
    private String intensity;
    private LocalDate exerciseDate;
    private LocalTime exerciseTime;
    private float calBurned;

    // Constructor
    public ExerciseEntry(String exerciseID,String exerciseName, String exerciseType, int duration,
                         String intensity, LocalDate exerciseDate, LocalTime exerciseTime, float calBurned) {
        this.exerciseID = exerciseID;
        this.exerciseName = exerciseName;
        this.exerciseType = exerciseType;
        this.duration = duration;
        this.intensity = intensity;
        this.exerciseDate = exerciseDate;
        this.exerciseTime = exerciseTime;
        this.calBurned = calBurned;
    }

    // Getter and Setter methods
    public String getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(String exerciseID) {
        this.exerciseID = exerciseID;
    }
    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }


    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public LocalDate getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(LocalDate exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public LocalTime getExerciseTime() {
        return exerciseTime;
    }

    public void setExerciseTime(LocalTime exerciseTime) {
        this.exerciseTime = exerciseTime;
    }

    public float getCalBurned() {
        return calBurned;
    }

    public void setCalBurned(float calBurned) {
        this.calBurned = calBurned;
    }


    @Override
    public String toString() {
        return exerciseID + "-" + exerciseName + "-" + exerciseType + "-"
                + duration + "-" + intensity + "-" + exerciseDate + "-" +
                exerciseTime + "-" + calBurned;
    }
}