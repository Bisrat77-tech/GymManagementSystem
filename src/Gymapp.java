import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Gymapp {
    private List<Member> members = new ArrayList<>();
    private List<Trainer>  trainers  = new ArrayList<>();
    private List<Gymclass> classes   = new ArrayList<>();

    private int nextMemberId  = 1;
    private int nextTrainerId = 100;
    private int nextClassId   = 200;

    private Scanner scanner = new Scanner(System.in);

    public Gymapp(){
        trainers.add(new Trainer(nextTrainerId++,"Abebe Girma", 30,"0911111111",
                "Strength", 15000, 5));
        trainers.add(new Trainer(nextTrainerId++,"Tigist Abebe", 27,"092222222",
                "Yoga", 13000,3));
        trainers.add(new Trainer(nextTrainerId++,"Dawit Tesfaye",35,"093333333",
                "Cardio",17000,8));

        members.add(new Member(nextMemberId++,"Sara Bekele", 29, "0944444444" ,
                "Premium", 10000d,true ));
        members.add(new Member(nextMemberId++, "Yonas Tadesse", 28, "0955-555-555",
                "Standard",5000, true));
        members.add(new Member(nextMemberId++, "Liya Mengistu", 19, "0966-666-666",
                "Basic", 2000,true));

        classes.add(new Gymclass(nextClassId++, "Morning Yoga",    "Tigist Haile",
                "Mon/Wed 07:00", 10));
        classes.add(new Gymclass(nextClassId++, "Power Lifting",   "Abebe Girma",
                "Tue/Thu 09:00", 8));
        classes.add(new Gymclass(nextClassId++, "Cardio Blast",    "Dawit Tesfaye",
                "Fri 06:00",     15));
        classes.add(new Gymclass(nextClassId++, "Beginner Cardio", "Dawit Tesfaye",
                "Sat 08:00",     12));
    }
    public void run() {
        printBanner();
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1: memberMenu();   break;
                case 2: trainerMenu();  break;
                case 3: classMenu();    break;
                case 4: polymorphismDemo(); break;
                case 0: running = false; break;
                default: System.out.println("  ✗ Invalid choice. Try again.");
            }
        }
        System.out.println("\n  Goodbye! Stay fit. \n");
        scanner.close();
    }
    private void printMainMenu() {
        System.out.println("  ╔══════════════════════════════════╗");
        System.out.println("  ║         MAIN MENU                ║");
        System.out.println("  ╠══════════════════════════════════╣");
        System.out.println("  ║  1. Member Management            ║");
        System.out.println("  ║  2. Trainer Management           ║");
        System.out.println("  ║  3. Class Management             ║");
        System.out.println("  ║  4. Polymorphism Demo            ║");
        System.out.println("  ║  0. Exit                         ║");
        System.out.println("  ╚══════════════════════════════════╝");
    }

    private void memberMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n── Member Management ─────────────────");
            System.out.println("  1. Add Member");
            System.out.println("  2. View All Members");
            System.out.println("  3. View Member Details");
            System.out.println("  4. Record Payment");
            System.out.println("  5. Deactivate Member");
            System.out.println("  0. Back");
            int choice = readInt("Choice: ");
            switch (choice) {
                case 1: addMember();          break;
                case 2: listMembers();        break;
                case 3: viewMemberDetails();  break;
                case 4: recordPayment();      break;
                case 5: deactivateMember();   break;
                case 0: back = true;          break;
                default: System.out.println("  ✗ Invalid choice.");
            }
        }
    }
    private void addMember() {
        System.out.println("\n── Add New Member ────────────────────");
        try {
            System.out.print("  Name          : "); String name  = scanner.nextLine().trim();
            int    age    = readInt("  Age           : ");
            System.out.print("  Phone         : "); String phone = scanner.nextLine().trim();
            System.out.print("  Membership (Basic/Standard/Premium): ");
            String type   = scanner.nextLine().trim();
            double bal    = readDouble("  Initial balance (ETB): ");

            Member m = new Member(nextMemberId++, name, age, phone, type, bal, true);
            members.add(m);
            System.out.println("  ✓ Member added. ID = " + m.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("  ✗ Error: " + e.getMessage());
            nextMemberId--; // roll back if creation failed
        }
    }

    private void listMembers() {
        System.out.println("\n── All Members ───────────────────────");
        if (members.isEmpty()) { System.out.println("  No members registered."); return; }
        for (Member m : members) System.out.println("  " + m);
    }

    private void viewMemberDetails() {
        int id = readInt("\n  Enter Member ID: ");
        Member m = findMember(id);
        if (m == null) { System.out.println("  ✗ Member not found."); return; }

    }

    private void recordPayment() {
        int id = readInt("\n  Enter Member ID: ");
        Member m = findMember(id);
        if (m == null) { System.out.println("  ✗ Member not found."); return; }
        try {
            double amount = readDouble("  Payment amount (ETB): ");
            m.pay(amount);
            System.out.printf("  ✓ Payment recorded. New balance: ETB %.2f%n", m.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("  ✗ " + e.getMessage());
        }
    }

    private void deactivateMember() {
        int id = readInt("\n  Enter Member ID to deactivate: ");
        Member m = findMember(id);
        if (m == null) { System.out.println("  ✗ Member not found."); return; }
        m.setActive(false);
        System.out.println("  ✓ Member " + m.getName() + " has been deactivated.");
    }

    // ═════════════════════════════════════════════════════════════════════
    //  TRAINER MANAGEMENT
    // ═════════════════════════════════════════════════════════════════════
    private void trainerMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n── Trainer Management ────────────────");
            System.out.println("  1. Add Trainer");
            System.out.println("  2. View All Trainers");
            System.out.println("  3. View Trainer Details");
            System.out.println("  0. Back");
            int choice = readInt("Choice: ");
            switch (choice) {
                case 1: addTrainer();         break;
                case 2: listTrainers();       break;
                case 3: viewTrainerDetails(); break;
                case 0: back = true;          break;
                default: System.out.println("  ✗ Invalid choice.");
            }
        }
    }

    private void addTrainer() {
        System.out.println("\n── Add New Trainer ───────────────────");
        try {
            System.out.print("  Name          : "); String name  = scanner.nextLine().trim();
            int    age    = readInt("  Age           : ");
            System.out.print("  Phone         : "); String phone = scanner.nextLine().trim();
            System.out.print("  Specialty     : "); String spec  = scanner.nextLine().trim();
            double salary = readDouble("  Salary (ETB)  : ");
            int    exp    = readInt("  Years of experience: ");

            Trainer t = new Trainer(nextTrainerId++, name, age, phone, spec, salary, exp);
            trainers.add(t);
            System.out.println("  ✓ Trainer added. ID = " + t.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("  ✗ Error: " + e.getMessage());
            nextTrainerId--;
        }
    }

    private void listTrainers() {
        System.out.println("\n── All Trainers ──────────────────────");
        if (trainers.isEmpty()) { System.out.println("  No trainers registered."); return; }
        for (Trainer t : trainers) System.out.println("  " + t);
    }

    private void viewTrainerDetails() {
        int id = readInt("\n  Enter Trainer ID: ");
        Trainer t = findTrainer(id);
        if (t == null) { System.out.println("  ✗ Trainer not found."); return; }
        t.displayInfo(); // polymorphic call (V4.0)
    }

    // ═════════════════════════════════════════════════════════════════════
    //  CLASS MANAGEMENT
    // ═════════════════════════════════════════════════════════════════════
    private void classMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n── Class Management ──────────────────");
            System.out.println("  1. Add Gym Class");
            System.out.println("  2. View All Classes (brief)");
            System.out.println("  3. View Class Details");
            System.out.println("  4. Enroll Member in Class");
            System.out.println("  5. Unenroll Member from Class");
            System.out.println("  0. Back");
            int choice = readInt("Choice: ");
            switch (choice) {
                case 1: addClass();          break;
                case 2: listClasses();       break;
                case 3: viewClassDetails();  break;
                case 4: enrollInClass();     break;
                case 5: unenrollFromClass(); break;
                case 0: back = true;         break;
                default: System.out.println("  ✗ Invalid choice.");
            }
        }
    }

    private void addClass() {
        System.out.println("\n── Add New Gym Class ─────────────────");
        try {
            System.out.print("  Class Name    : "); String name    = scanner.nextLine().trim();
            System.out.print("  Trainer Name  : "); String trainer = scanner.nextLine().trim();
            System.out.print("  Schedule      : "); String sched   = scanner.nextLine().trim();
            int cap = readInt("  Max Capacity  : ");

            Gymclass gc = new Gymclass(nextClassId++, name, trainer, sched, cap);
            classes.add(gc);
            System.out.println("  ✓ Class added. ID = " + gc.getClassId());
        } catch (IllegalArgumentException e) {
            System.out.println("  ✗ Error: " + e.getMessage());
            nextClassId--;
        }
    }

    private void listClasses() {
        System.out.println("\n── All Gym Classes ───────────────────");
        if (classes.isEmpty()) { System.out.println("  No classes available."); return; }
        for (Gymclass gc : classes) {
            gc.describe();   // overloaded: no-arg version (V4.0 – compile-time polymorphism)
        }
    }

    private void viewClassDetails() {
        int id = readInt("\n  Enter Class ID: ");
        Gymclass gc = findClass(id);
        if (gc == null) { System.out.println("  ✗ Class not found."); return; }
        gc.describe(true);  // overloaded: verbose version (V4.0 – compile-time polymorphism)
    }

    private void enrollInClass() {
        int cid = readInt("\n  Enter Class ID: ");
        Gymclass gc = findClass(cid);
        if (gc == null) { System.out.println("  ✗ Class not found."); return; }
        int mid = readInt("  Enter Member ID: ");
        Member m = findMember(mid);
        if (m == null) { System.out.println("  ✗ Member not found."); return; }
        if (!m.isActive()) { System.out.println("  ✗ Member is inactive."); return; }

        if (gc.enroll()) {
            // Show label version of describe (V4.0 – compile-time polymorphism)
            gc.describe("ENROLLED");
        } else {
            System.out.println("  ✗ Class is full.");
        }
    }

    private void unenrollFromClass() {
        int cid = readInt("\n  Enter Class ID: ");
        Gymclass gc = findClass(cid);
        if (gc == null) { System.out.println("  ✗ Class not found."); return; }
        if (gc.enroll()) {
            System.out.println("  ✓ Member unenrolled.");
            gc.describe("UPDATED");
        } else {
            System.out.println("  ✗ No enrolled members to remove.");
        }
    }


    private void polymorphismDemo() {
        System.out.println("\n╔═══════════════════════════════════════════╗");
        System.out.println("  ║  POLYMORPHISM DEMO (V4.0)                 ║");
        System.out.println("  ║  Superclass reference → subclass objects  ║");
        System.out.println("  ╚═══════════════════════════════════════════╝");

        // Build a mixed Person array (superclass references)
        List<Person> people = new ArrayList<>();
        if (!members.isEmpty())  people.add(members.get(0));   // Member IS-A Person
        if (!trainers.isEmpty()) people.add(trainers.get(0));  // Trainer IS-A Person
        if (members.size() > 1)  people.add(members.get(1));

        System.out.println("\n  Iterating through Person[] – calling displayInfo() on each:");
        System.out.println("  (Java calls the correct overridden method at runtime)\n");

        for (Person p : people) {
            System.out.println("  >> getRole() returns: " + p.getRole());

            System.out.println();
        }

        System.out.println("  ─── Compile-time polymorphism demo (Method Overloading) ───");
        System.out.println("  Calling three versions of GymClass.describe():\n");
        if (!classes.isEmpty()) {
            Gymclass sample = classes.get(0);
            sample.describe();               // version 1 – no args
            sample.describe(true);           // version 2 – boolean
            sample.describe("DEMO");         // version 3 – String label
        }
    }

    // ═════════════════════════════════════════════════════════════════════
    //  HELPER METHODS
    // ═════════════════════════════════════════════════════════════════════
    private Member findMember(int id) {
        for (Member m : members) if (m.getId() == id) return m;
        return null;
    }

    private Trainer findTrainer(int id) {
        for (Trainer t : trainers) if (t.getId() == id) return t;
        return null;
    }

    private Gymclass findClass(int id) {
        for (Gymclass gc : classes) if (gc.getClassId() == id) return gc;
        return null;
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("  ✗ Please enter a whole number.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("  ✗ Please enter a valid number.");
            }
        }
    }

    private void printBanner() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   🏋  GYM MANAGEMENT SYSTEM  🏋           ║");
        System.out.println("║        Java OOP MVP – v1.0               ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }
}





