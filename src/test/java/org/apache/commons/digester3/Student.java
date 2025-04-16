package org.apache.commons.digester3;

public class Student {
    private String id;
    private String name;
    private int age;
    private String major;

    // Getter & Setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    @Override
    public String toString() {
        return "Student{id='" + id + "', name='" + name + "', age=" + age + ", major='" + major + "'}";
    }
}


