/**
 * Represents a subscription with details such as visits per month and associated department.
 */
public class Subscription {
    private int id;
    private int visitsPerMonth;
    private Department department;

    /**
     * Constructs a new Subscription with the specified visits per month and department.
     *
     * @param visitsPerMonth the number of visits allowed per month
     * @param department the department associated with the subscription
     */
    public Subscription(int visitsPerMonth, Department department) {
        this.visitsPerMonth = visitsPerMonth;
        this.department = department;
    }

    // Getters and Setters

    /**
     * Gets the ID of the subscription.
     *
     * @return the ID of the subscription
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the subscription.
     *
     * @param id the new ID of the subscription
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the number of visits allowed per month.
     *
     * @return the number of visits allowed per month
     */
    public int getVisitsPerMonth() {
        return visitsPerMonth;
    }

    /**
     * Sets the number of visits allowed per month.
     *
     * @param visitsPerMonth the new number of visits allowed per month
     */
    public void setVisitsPerMonth(int visitsPerMonth) {
        this.visitsPerMonth = visitsPerMonth;
    }

    /**
     * Gets the department associated with the subscription.
     *
     * @return the department associated with the subscription
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Sets the department associated with the subscription.
     *
     * @param department the new department associated with the subscription
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Returns a string representation of the subscription.
     *
     * @return a string representation of the subscription
     */
    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", visitsPerMonth=" + visitsPerMonth +
                ", department=" + department.getName() +
                '}';
    }
}