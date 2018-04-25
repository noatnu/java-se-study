package zch.pojo;

public class Employee {

    private String id;
    private String name;
    private float salary;
    private String email;

    private Department dept;

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public String getId() {
        return id;
    }

    public Employee setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public float getSalary() {
        return salary;
    }

    public Employee setSalary(float salary) {
        this.salary = salary;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Employee setEmail(String email) {
        this.email = email;
        return this;
    }


    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", email=" + email + "]";
    }


    public Employee() {
    }

}
