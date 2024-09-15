import java.time.LocalDateTime;

/**
 * Represents a reservation made by a member for a specific department at a scheduled time.
 */
public class Reservation {
    private int id;
    private Member member;
    private Department department;
    private String scheduleTime;
    private LocalDateTime dateTime;
    private Subscription subscription;

    /**
     * Constructs a new Reservation.
     *
     * @param member the member making the reservation
     * @param department the department for which the reservation is made
     * @param scheduleTime the scheduled time for the reservation
     * @param dateTime the date and time of the reservation
     * @param subscription the subscription associated with the reservation
     * @throws IllegalArgumentException if the department is not available at the scheduled time
     */
    public Reservation(Member member, Department department, String scheduleTime, LocalDateTime dateTime, Subscription subscription) {
        if (!department.isAvailable(scheduleTime)) {
            throw new IllegalArgumentException("No available slot for the selected schedule.");
        }
        this.member = member;
        this.department = department;
        this.scheduleTime = scheduleTime;
        this.dateTime = dateTime;
        this.subscription = subscription;
        department.addParticipant(scheduleTime); // Προσθήκη συμμετέχοντα στο πρόγραμμα του τμήματος
    }

    /**
     * Changes the schedule time and date of the reservation.
     *
     * @param newScheduleTime the new scheduled time
     * @param newDateTime the new date and time
     * @return true if the reservation was successfully changed, false otherwise
     */
    public boolean changeReservation(String newScheduleTime, LocalDateTime newDateTime) {
        if (department.isAvailable(newScheduleTime)) {
            department.removeParticipant(this.scheduleTime); // Αφαίρεση από την προηγούμενη ώρα
            this.scheduleTime = newScheduleTime;
            this.dateTime = newDateTime;
            department.addParticipant(newScheduleTime); // Προσθήκη στη νέα ώρα
            return true;
        }
        return false;
    }

    /**
     * Changes the department, schedule time, and date of the reservation.
     *
     * @param newDepartment the new department
     * @param newScheduleTime the new scheduled time
     * @param newDateTime the new date and time
     * @return true if the reservation was successfully changed, false otherwise
     */
    public boolean changeDepartment(Department newDepartment, String newScheduleTime, LocalDateTime newDateTime) {
        if (newDepartment.isAvailable(newScheduleTime)) {
            department.removeParticipant(this.scheduleTime); // Αφαίρεση από το προηγούμενο τμήμα
            newDepartment.addParticipant(newScheduleTime); // Προσθήκη στο νέο τμήμα
            this.department = newDepartment;
            this.scheduleTime = newScheduleTime;
            this.dateTime = newDateTime;
            return true;
        }
        return false;
    }

    // Getters και Setters

    /**
     * Gets the ID of the reservation.
     *
     * @return the ID of the reservation
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the reservation.
     *
     * @param id the new ID of the reservation
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the member who made the reservation.
     *
     * @return the member who made the reservation
     */
    public Member getMember() {
        return member;
    }

    /**
     * Sets the member who made the reservation.
     *
     * @param member the new member who made the reservation
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * Gets the department for which the reservation is made.
     *
     * @return the department for which the reservation is made
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Sets the department for which the reservation is made.
     *
     * @param department the new department for which the reservation is made
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Gets the scheduled time of the reservation.
     *
     * @return the scheduled time of the reservation
     */
    public String getScheduleTime() {
        return scheduleTime;
    }

    /**
     * Sets the scheduled time of the reservation.
     *
     * @param scheduleTime the new scheduled time of the reservation
     */
    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    /**
     * Gets the date and time of the reservation.
     *
     * @return the date and time of the reservation
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Sets the date and time of the reservation.
     *
     * @param dateTime the new date and time of the reservation
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Gets the subscription associated with the reservation.
     *
     * @return the subscription associated with the reservation
     */
    public Subscription getSubscription() {
        return subscription;
    }

    /**
     * Sets the subscription associated with the reservation.
     *
     * @param subscription the new subscription associated with the reservation
     */
    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    /**
     * Returns a string representation of the reservation.
     *
     * @return a string representation of the reservation
     */
    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", member=" + member.getName() +
                ", department=" + department.getName() +
                ", scheduleTime='" + scheduleTime + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}