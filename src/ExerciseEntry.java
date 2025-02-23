
import java.time.LocalDate;
import java.time.LocalTime;


public class ExerciseEntry {
    private String exerciseName;
    private String exerciseID;
    private String exerciseType;
    private int duration;
    private String intensity;
    private LocalDate exerciseDate;
    private LocalTime exerciseTime;
    private float calBurned;

    // Constructor
    public ExerciseEntry(String exerciseName, String exerciseID, String exerciseType, int duration,
                         String intensity, LocalDate exerciseDate, LocalTime exerciseTime, float calBurned) {
        this.exerciseName = exerciseName;
        this.exerciseID = exerciseID;
        this.exerciseType = exerciseType;
        this.duration = duration;
        this.intensity = intensity;
        this.exerciseDate = exerciseDate;
        this.exerciseTime = exerciseTime;
        this.calBurned = calBurned;
    }

    // Getter and Setter methods
    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(String exerciseID) {
        this.exerciseID = exerciseID;
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
        return "ExerciseEntry{" +
                "exerciseName='" + exerciseName + '\'' +
                ", exerciseID='" + exerciseID + '\'' +
                ", exerciseType='" + exerciseType + '\'' +
                ", duration=" + duration +
                ", intensity='" + intensity + '\'' +
                ", exerciseDate=" + exerciseDate +
                ", exerciseTime=" + exerciseTime +
                ", calBurned=" + calBurned +
                '}';
    }
}