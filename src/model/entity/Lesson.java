package model.entity;

import java.sql.Timestamp;

/**
 * Created by admin on 18.04.2017.
 */
public class Lesson {
    private long id;
    private Timestamp lessonDate;
    private long room;
    private String description;
    private long groupId;
    private Group group;

    public Lesson(long id, Timestamp lessonDate, long room, String description, long groupId){

        this.id = id;
        this.lessonDate = lessonDate;
        this.room = room;
        this.description = description;
        this.groupId = groupId;
    }

    public Lesson(long id, Timestamp lessonDate, long room, String description, Group group){

        this.id = id;
        this.lessonDate = lessonDate;
        this.room = room;
        this.description = description;
        this.group = group;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(Timestamp lessonDate) {
        this.lessonDate = lessonDate;
    }

    public long getRoom() {
        return room;
    }

    public void setRoom(long room) {
        this.room = room;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", lessonDate=" + lessonDate +
                ", room=" + room +
                ", description='" + description + '\'' +
                ", groupId=" + groupId +
                ", group=" + group +
                '}';
    }
}
