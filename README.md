# Member Management System

## Περιγραφή
Το Member Management System είναι μια εφαρμογή που διαχειρίζεται μέλη, τμήματα, συνδρομές και κρατήσεις ενός οργανισμού. Η εφαρμογή είναι υλοποιημένη σε Java και χρησιμοποιεί MySQL ως βάση δεδομένων. Παρέχει δυνατότητες όπως η δημιουργία μελών, η διαχείριση συνδρομών και η οργάνωση κρατήσεων σε διάφορα τμήματα.

## Δομή του Συστήματος
Η εφαρμογή αποτελείται από τις εξής βασικές κλάσεις:
- **DatabaseConnection:** Διαχειρίζεται τη σύνδεση με τη βάση δεδομένων.
- **Department:** Αναπαριστά ένα τμήμα του οργανισμού.
- **Member:** Αναπαριστά ένα μέλος του οργανισμού.
- **Subscription:** Αναπαριστά μια συνδρομή που συνδέεται με ένα συγκεκριμένο τμήμα.
- **Reservation:** Αναπαριστά μια κράτηση για συμμετοχή ενός μέλους σε ένα πρόγραμμα τμήματος.
- **DAO Κλάσεις:** Χρησιμοποιούνται για την αλληλεπίδραση με τη βάση δεδομένων (π.χ., DepartmentDAO, MemberDAO, SubscriptionDAO, ReservationDAO).

## Σχεδιασμός Βάσης Δεδομένων
Η βάση δεδομένων αποτελείται από τους παρακάτω πίνακες:
- **Department:** Περιέχει τα τμήματα με μοναδικά ονόματα.
- **Member:** Περιέχει τα μέλη με μοναδικά emails.
- **Subscription:** Περιέχει τις συνδρομές με περιορισμό στον αριθμό των επισκέψεων.
- **Reservation:** Περιέχει τις κρατήσεις, συνδεδεμένες με τα μέλη και τα τμήματα.

### SQL Scripts
Για τη δημιουργία των πινάκων, χρησιμοποιήστε τα παρακάτω SQL scripts:

```sql
USE memberman;

-- Δημιουργία του πίνακα Department
CREATE TABLE Department (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- Δημιουργία του πίνακα Member
CREATE TABLE Member (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20),
    age INT CHECK (age >= 0)
);

-- Δημιουργία του πίνακα Subscription
CREATE TABLE Subscription (
    id INT AUTO_INCREMENT PRIMARY KEY,
    visitsPerMonth INT CHECK (visitsPerMonth IN (8, 15)),
    department_id INT,
    FOREIGN KEY (department_id) REFERENCES Department(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- Δημιουργία του πίνακα Reservation
CREATE TABLE Reservation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    member_id INT,
    department_id INT,
    scheduleTime VARCHAR(255) NOT NULL,
    dateTime DATETIME NOT NULL,
    FOREIGN KEY (member_id) REFERENCES Member(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (department_id) REFERENCES Department(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);
--

```
## Οδηγίες Εγκατάστασης και Εκτέλεσης

### Προαπαιτούμενα
- **Java (JDK 15 ή νεότερο)**
- **MySQL**
- **MySQL Connector/J (JDBC Driver)**

### Ρυθμίσεις Σύνδεσης
1. Δημιουργήστε ένα αρχείο `config.properties` με το παρακάτω περιεχόμενο:
    ```properties
    db.url=jdbc:mysql://localhost:3306/memberman
    db.user=root
    db.password=your_password_here
    ```

### Εκτέλεση της Εφαρμογής
1. Κλωνοποιήστε το αποθετήριο και εισάγετε το project στο IDE σας (π.χ., IntelliJ).
2. Βεβαιωθείτε ότι η MySQL εκτελείται και ότι η βάση δεδομένων είναι ρυθμισμένη όπως πρέπει.
3. Εκτελέστε την κλάση `Main` για να δημιουργήσετε τους πίνακες και να προσθέσετε παραδείγματα δεδομένων.

## Λειτουργία του Κώδικα

### Κλάση `DatabaseConnection`
- Δημιουργεί σύνδεση με τη βάση δεδομένων χρησιμοποιώντας τις ρυθμίσεις που έχουν οριστεί στο `config.properties`.
- Περιλαμβάνει λειτουργίες για τη δημιουργία των απαιτούμενων πινάκων αν δεν υπάρχουν ήδη.

### Κλάση `Department` και `DepartmentDAO`
- **Department:** Αντιπροσωπεύει ένα τμήμα με μοναδικό όνομα.
- **DepartmentDAO:** Παρέχει μεθόδους για τη διαχείριση των τμημάτων στη βάση δεδομένων, όπως η προσθήκη, η ενημέρωση και η διαγραφή τμημάτων.

### Κλάση `Member` και `MemberDAO`
- **Member:** Αντιπροσωπεύει ένα μέλος με μοναδικό email και πληροφορίες όπως όνομα, τηλέφωνο, και ηλικία.
- **MemberDAO:** Παρέχει μεθόδους για τη διαχείριση των μελών στη βάση δεδομένων, όπως η προσθήκη νέων μελών.

### Κλάση `Subscription` και `SubscriptionDAO`
- **Subscription:** Αντιπροσωπεύει μια συνδρομή που σχετίζεται με ένα τμήμα και περιλαμβάνει περιορισμό στον αριθμό επισκέψεων (8 ή 15).
- **SubscriptionDAO:** Παρέχει μεθόδους για τη διαχείριση των συνδρομών στη βάση δεδομένων, όπως η προσθήκη και η ενημέρωση συνδρομών.

### Κλάση `Reservation` και `ReservationDAO`
- **Reservation:** Αντιπροσωπεύει μια κράτηση που σχετίζεται με ένα μέλος και ένα τμήμα, περιλαμβάνοντας πληροφορίες για την ημέρα και την ώρα της κράτησης.
- **ReservationDAO:** Παρέχει μεθόδους για τη διαχείριση των κρατήσεων στη βάση δεδομένων, όπως η προσθήκη νέων κρατήσεων και η επαλήθευση διαθεσιμότητας.

### Κλάση `Main`
- Εκτελεί τις βασικές λειτουργίες της εφαρμογής:
    - Δημιουργεί τα τμήματα και τα μέλη.
    - Προσθέτει συνδρομές για τα μέλη.
    - Δημιουργεί κρατήσεις και ελέγχει τη διαθεσιμότητα των ωρών και των ημερών των τμημάτων.
    - Χειρίζεται αλλαγές στις κρατήσεις και ελέγχει τη διαθεσιμότητα για νέες ώρες ή τμήματα.

## Παραδείγματα Χρήσης
- **Δημιουργία Τμημάτων και Μελών:** Μπορείτε να προσθέσετε νέα τμήματα και μέλη στην εφαρμογή και να διαχειριστείτε τις συνδρομές τους.
- **Προσθήκη Συνδρομών:** Εισάγετε συνδρομές για μέλη, συνδέοντάς τα με συγκεκριμένα τμήματα και καθορίζοντας τον αριθμό επισκέψεων.
- **Διαχείριση Κρατήσεων:** Δημιουργήστε κρατήσεις για μέλη, επιλέγοντας τμήμα, ημέρα και ώρα, ενώ ελέγχετε για διαθεσιμότητα και κάνετε αλλαγές εάν είναι απαραίτητο.
  