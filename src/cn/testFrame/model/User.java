package cn.testFrame.model;

public class User {

    private int uuid;

    private  int name;
    private int age;


    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(int uuid, int name, int age) {
        this.uuid = uuid;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", name=" + name +
                ", age=" + age +
                '}';
    }


}
