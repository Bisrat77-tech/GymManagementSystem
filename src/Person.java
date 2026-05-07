public  class Person{
    private int id;
    private String name;
    private int age;
    private String phone;

    public Person(int id,String name, int age, String phone){
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public int getId(){return id;}
    public String getName(){return name;}
    public int getAge(){return age;}
    public String getPhone(){return phone;}

    public void setName(String name){
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be Empty.");
        }
        this.name = name.trim();
    }

    public void setAge(int age){
        if (age < 5 || age > 120){
            throw new IllegalArgumentException("Age must be between 5 and 120");
        }
        this.age =age;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()){
            throw new IllegalArgumentException("Phone cannot be empty.");
      }
        this.phone =phone.trim();
    }


    public String getRole(){
        return "Person";
    }

    @Override
    public String toString(){
        return String.format("[%s] ID: %d | Name: %-20s | Age:%3d | Phone: %s",
        getRole(), id, name, age, phone);
    }
}



