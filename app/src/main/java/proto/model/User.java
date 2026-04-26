package proto.model;

public class User {
    
    private String name;
    private int age;
    private float insulinFactor;

    public User(String name, int age) {
        if(age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        this.name = name;
        this.age = age;
        this.insulinFactor = 1.0f;
    }

    public User(String name, int age, float insulinFactor) {
        this.name = name;
        this.age = age;
        this.insulinFactor = insulinFactor;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        this.age = age;
    }

    public float getFactor() {
        return insulinFactor;
    }

    public void setFactor(float factor) {
        this.insulinFactor = factor;
    }

}