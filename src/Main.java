import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // Create tables if they do not exist
        DatabaseConnection.createTables();

        // Create departments
        Department swimming = new Department("Swimming");
        Department dancing = new Department("Traditional Dancing");

        // Insert departments into the database
        DepartmentDAO departmentDAO = new DepartmentDAO();
        if (!departmentDAO.checkDepartmentExists(swimming.getName())) {
            departmentDAO.addDepartment(swimming);
            System.out.println("Department 'Swimming' inserted.");
        }

        if (!departmentDAO.checkDepartmentExists(dancing.getName())) {
            departmentDAO.addDepartment(dancing);
            System.out.println("Department 'Traditional Dancing' inserted.");
        }

        // Create and insert member
        Member john = new Member("John Doe", "john@example.com", "1234567890", 25);
        MemberDAO memberDAO = new MemberDAO();
        if (!memberDAO.emailExists(john.getEmail())) {
            int memberId = memberDAO.addMember(john);
            if (memberId != -1) {
                System.out.println("Member added with ID: " + memberId);
            }
        }

        // Create subscriptions
        Subscription swimmingSub = new Subscription(8, swimming);
        Subscription dancingSub = new Subscription(15, dancing);

        // Insert subscriptions into the database
        SubscriptionDAO subscriptionDAO = new SubscriptionDAO();
        subscriptionDAO.addSubscription(swimmingSub);
        subscriptionDAO.addSubscription(dancingSub);

        // Create reservations
        Reservation swimmingReservation = new Reservation(john, swimming, "Monday 09:00-11:00", LocalDateTime.now(), swimmingSub);
        Reservation dancingReservation = new Reservation(john, dancing, "Tuesday 10:00-12:00", LocalDateTime.now(), dancingSub);

        // Insert reservations into the database
        ReservationDAO reservationDAO = new ReservationDAO();
        reservationDAO.addReservation(swimmingReservation);
        reservationDAO.addReservation(dancingReservation);

        // Example of changing a reservation
        boolean changed = swimmingReservation.changeReservation("Wednesday 16:00-18:00", LocalDateTime.now().plusDays(1));
        if (changed) {
            System.out.println("Reservation changed successfully.");
        } else {
            System.out.println("Failed to change reservation.");
        }

        // Example of changing a department for reservation
        boolean departmentChanged = swimmingReservation.changeDepartment(dancing, "Thursday 17:00-19:00", LocalDateTime.now().plusDays(2));
        if (departmentChanged) {
            System.out.println("Department changed successfully.");
        } else {
            System.out.println("Failed to change department.");
        }
    }
}
