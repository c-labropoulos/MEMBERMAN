 /**
 * Represents a member with personal details such as name, email, phone, and age.
 */
public class Member {
    private int id;
    private String name;
    private String email;
    private String phone;
    private int age;

    /**
     * Constructs a new Member with the specified details.
     *
     * @param name  the name of the member
     * @param email the email of the member
     * @param phone the phone number of the member
     * @param age   the age of the member
     */
    public Member(String name, String email, String phone, int age) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
    }

    // Getters and Setters

    /**
     * Gets the ID of the member.
     *
     * @return the ID of the member
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the member.
     *
     * @param id the new ID of the member
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the member.
     *
     * @return the name of the member
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the member.
     *
     * @param name the new name of the member
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email of the member.
     *
     * @return the email of the member
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the member.
     *
     * @param email the new email of the member
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the phone number of the member.
     *
     * @return the phone number of the member
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the member.
     *
     * @param phone the new phone number of the member
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the age of the member.
     *
     * @return the age of the member
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the member.
     *
     * @param age the new age of the member
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns a string representation of the member.
     *
     * @return a string representation of the member
     */
    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                '}';
    }
}
