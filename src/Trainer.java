public class Trainer extends Person{
    private String specialty;
    private double salary;
    private int yearOfExp;

    public Trainer(int id, String name, int age, String phone,
                   String specialty, double salary, int yearOfExp){
        super(id, name, age, phone);
        this.specialty = specialty;
        this.salary = salary;
        this.yearOfExp = yearOfExp;

    }

    public String getSpecialty(){return specialty;}
    public double getSalary(){return salary;}
    public int getYearOfExp(){return yearOfExp;}

    public void setSpecialty(String specialty){
        if (specialty == null || specialty.trim().isEmpty()){
            throw new IllegalArgumentException("Speciality cannot be empty.");
        }
        this.specialty = specialty.trim();
    }

    public void setSalary(double salary){
        if (salary < 0) throw new IllegalArgumentException("Salary cannot be negative.");
        this.salary = salary;
    }

    public void setYearOfExp(int years){
        if (years < 0) throw new IllegalArgumentException("Years of experience cannot be negative.");
        this.yearOfExp = years;
    }

    @Override
    public String getRole(){
        return "TRAINER";
    }

    public void displayInfo() {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.printf("│ TRAINER PROFILE                         │%n");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.printf("│ ID         : %-27d│%n", getId());
        System.out.printf("│ Name       : %-27s│%n", getName());
        System.out.printf("│ Age        : %-27d│%n", getAge());
        System.out.printf("│ Phone      : %-27s│%n", getPhone());
        System.out.printf("│ Specialty  : %-27s│%n", specialty);
        System.out.printf("│ Experience : %-27s│%n", yearOfExp + " years");
        System.out.printf("│ Salary     : ETB %-23.2f│%n", salary);
        System.out.println("└─────────────────────────────────────────┘");
    }
    @Override
    public String toString(){
        return super.toString()
                + String.format(" | Specialty: %-12s | Exp: %d yrs | Salary:ETB %.2f",
                specialty, yearOfExp, salary);
    }
}



