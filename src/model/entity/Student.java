package model.entity;

/**
 * Created by admin on 18.04.2017.
 */
public class Student {
    private long id;
    private String name;
    private long age;
    private long group_id;
    private Group group;

    public Student(long id, String name, long age, long group_id){

        this.id = id;
        this.name = name;
        this.age = age;
        this.group_id = group_id;
    }

    public Student(long id, String name, long age, Group group){

        this.id = id;
        this.name = name;
        this.age = age;
        this.group = group;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", group_id=" + group_id +
                ", group=" + group +
                '}';
    }
}
