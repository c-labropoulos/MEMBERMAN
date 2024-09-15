import java.util.HashMap;
import java.util.Map;

/**
 * Represents a department with a schedule of available times and participants.
 */
public class Department {
    private int id;
    private String name;
    private Map<String, Integer> schedule; // Χάρτης με τις ώρες και τον αριθμό των συμμετεχόντων

    /**
     * Constructs a new Department with the specified name.
     *
     * @param name the name of the department
     */
    public Department(String name) {
        this.name = name;
        this.schedule = new HashMap<>();
        // Αρχικοποίηση προγράμματος με προκαθορισμένες ώρες και μηδενικούς συμμετέχοντες
        initializeDefaultSchedule();
    }

    /**
     * Initializes the default schedule with predefined times and zero participants.
     */
    private void initializeDefaultSchedule() {
        // Προσθήκη ωρών με αρχική τιμή 0 συμμετεχόντων
        schedule.put("Monday 09:00-11:00", 0);
        schedule.put("Wednesday 16:00-18:00", 0);
        schedule.put("Tuesday 10:00-12:00", 0);
        schedule.put("Thursday 17:00-19:00", 0);
    }

    /**
     * Adds a participant to a specific time slot.
     *
     * @param time the time slot to add a participant to
     * @return true if the participant was added successfully, false otherwise
     */
    public boolean addParticipant(String time) {
        if (schedule.containsKey(time) && schedule.get(time) < 6) {
            schedule.put(time, schedule.get(time) + 1);
            return true;
        }
        return false;
    }

    /**
     * Removes a participant from a specific time slot.
     *
     * @param time the time slot to remove a participant from
     * @return true if the participant was removed successfully, false otherwise
     */
    public boolean removeParticipant(String time) {
        if (schedule.containsKey(time) && schedule.get(time) > 0) {
            schedule.put(time, schedule.get(time) - 1);
            return true;
        }
        return false;
    }

    /**
     * Checks if a specific time slot is available.
     *
     * @param time the time slot to check availability for
     * @return true if the time slot is available, false otherwise
     */
    public boolean isAvailable(String time) {
        return schedule.containsKey(time) && schedule.get(time) < 6;
    }

    // Getters και Setters

    /**
     * Gets the ID of the department.
     *
     * @return the ID of the department
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the department.
     *
     * @param id the new ID of the department
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the department.
     *
     * @return the name of the department
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the department.
     *
     * @param name the new name of the department
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the schedule of the department.
     *
     * @return the schedule of the department
     */
    public Map<String, Integer> getSchedule() {
        return schedule;
    }

    /**
     * Returns a string representation of the department.
     *
     * @return a string representation of the department
     */
    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", schedule=" + schedule +
                '}';
    }
}
