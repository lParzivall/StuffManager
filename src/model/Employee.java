package model;

public class Employee extends AbstractPerson {

    String department;
    int age;
    String phone;
    String position;
    String gender;

    public Employee(String name, String department, int age, String phone, String position, String gender) {
        super(name);
        this.department = department;
        this.age = age;
        this.phone = phone;
        this.position = position;
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getPosition() {
        return position;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "model.Employee:\n" +
            "name='" + getName() + '\'' +
            "department='" + department + '\'' +
            ", age=" + age +
            ", phone='" + phone + '\'' +
            ", position='" + position + '\'' +
            ", gender='" + gender + '\'';
    }

    @Override
    public String think() {
        return "I'm employee";
    }
}
