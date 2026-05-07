public class Member extends Person{
    private String memebershipType;
    private double balance;
    private boolean isActive;

    public Member(int id, String name, int age, String phone,
                  String memebershipType, double balance, boolean isActive){
        super(id, name, age, phone);
        this.memebershipType = memebershipType;
        this.balance = balance;
        this.isActive = true;
    }
    public String getMemebershipType(){return memebershipType;}
    public double getBalance(){return balance;}
    public boolean isActive(){return isActive;}

    public void setMemebershipType(String type){
        if (type == null) throw new IllegalArgumentException
                ("Membership type cannot be null.");
        switch (type.trim().toLowerCase()){
            case "basic": memebershipType = "Basic"; break;
            case "standard": memebershipType = "Standard"; break;
            case "premium": memebershipType = "Premium"; break;

            default:
                throw new IllegalArgumentException
                        ("Invalid membership type. Choose: Basic, Standard, Premium.");
        }
    }
    public void setActive(boolean active){this.isActive = active;}

    public void pay(double amount){
        if (amount <= 0)throw new IllegalArgumentException
                ("Payment must be positive");
        this.balance -= amount;
    }
    public void charge(double amount){
        if (amount <= 0)throw new IllegalArgumentException
                ("Charge must be positive.");
        this.balance += amount;
    }
    @Override
    public String getRole(){
        return "MEMBER";
    }


    public void dispayInfo(){
        System.out.println("___________________________________________");

        System.out.printf("| MEMBER PROFILE |%n");

        System.out.println("___________________________________________");

        System.out.printf("| ID      : %-27d|%n", getId());
        System.out.printf("| Name    : %-27s|%n", getName());
        System.out.printf("| Age     : %-27d|%n", getAge());
        System.out.printf("| Phone   : %-27s|%n", getPhone());
        System.out.printf("| Membership : %-27s|%n", memebershipType);
        System.out.printf("| Balance : ETB %-23.2f|%n", balance);
        System.out.printf("| Status  : %-27s|%n", isActive? "Active" : "Inactive");

        System.out.println("____________________________________________");


    }
    @Override
    public String toString(){
        return super.toString()
                + String.format("| Membership: %-8s | Balance: ETB %.2f | %s",
                memebershipType, balance, isActive ? "Active" : "Inactive");
    }
}
