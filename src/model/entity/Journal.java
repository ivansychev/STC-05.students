package model.entity;

/**
 * Created by admin on 18.04.2017.
 */
public class Journal {
    private long id;
    private long lessonId;
    private long studentId;
    private Lesson lesson;
    private Student student;

    public Journal(long id, long lessonId, long studentId){

        this.id = id;
        this.lessonId = lessonId;
        this.studentId = studentId;
    }

    public Journal(long id, Lesson lesson, Student student){

        this.id = id;
        this.lesson = lesson;
        this.student = student;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLessonId() {
        return lessonId;
    }

    public void setLessonId(long lessonId) {
        this.lessonId = lessonId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", lessonId=" + lessonId +
                ", studentId=" + studentId +
                ", lesson=" + lesson.toString() +
                ", student=" + student +
                '}';
    }
}
