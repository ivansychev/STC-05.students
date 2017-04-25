package services;

import java.sql.*;

/**
 * Created by ivans on 18/04/2017.
 */
public class DatabaseManager {

    public static Connection initConnection()
    {
        Connection connection = null;
        try
        {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection =
                    DriverManager.getConnection(
                            "jdbc:postgresql://localhost/students",
                            "postgres", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void selectStudents()
    {
        Connection connection = initConnection();
        String sql = "SELECT * FROM student";

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


  /*  public List<Student> selectStudents()
    {
        Students students = new Students();
        Connection connection = initConnection();
        String sql = "SELECT * FROM sudent";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                Student student = new Student();
                student.setId(result.getInt(1));
                student.setGroup_id(result.getInt(2));
                student.setName(result.getString(3));
                student.setAge(result.getInt(4));
                students.getStudent().add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students.getStudent();
    }

    public List<StudyGroup> selectStudyGroups()
    {
        StudyGroups studyGroups = new StudyGroups();
        Connection connection = initConnection();
        String sql = "SELECT * FROM study_group";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                StudyGroup studyGroup = new StudyGroup();
                studyGroup.setId(result.getInt(1));
                studyGroup.setName(result.getString(2));
                studyGroups.getStudyGroup().add(studyGroup);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studyGroups.getStudyGroup();
    }

    public List<Lesson> selectLessons()
    {
        Lessons lessons = new Lessons();
        Connection connection = initConnection();
        String sql = "SELECT * FROM lesson";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(result.getInt(1));
                lesson.setStudy_group_id(result.getInt(2));
                lesson.setLesson_date(result.getTimestamp(3));
                lesson.setRoom(result.getInt(4));
                lesson.setDescription(result.getString(5));
                lessons.getLesson().add(lesson);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons.getLesson();
    }

    public List<Journal> selectJournals()
    {
        Journals journals = new Journals();
        Connection connection = initConnection();
        String sql = "SELECT * FROM journal";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                Journal journal = new Journal();
                journal.setId(result.getInt(1));
                journal.setLesson_id(result.getInt(2));
                journal.setStudent_id(result.getInt(3));
                journals.getJournal().add(journal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return journals.getJournal();
    }

    public void createStudent(Student student) {
        Connection connection = initConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO student(" +
                            " id,group_id,name,age)" +
                            " VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setInt(2, student.getGroup_id());
            preparedStatement.setString(3, student.getName());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createStudyGroup(StudyGroup studyGroup) {
        Connection connection = initConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO study_group(" +
                            " id,name)" +
                            " VALUES (?, ?)");
            preparedStatement.setInt(1, studyGroup.getId());
            preparedStatement.setString(2, studyGroup.getName());
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createLesson(Lesson lesson) {
        Connection connection = initConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO lesson(" +
                            " id,study_group_id,lesson_date,room,description)" +
                            " VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, lesson.getId());
            preparedStatement.setInt(2, lesson.getStudy_group_id());
            preparedStatement.setTimestamp(3, lesson.getLesson_date());
            preparedStatement.setInt(4, lesson.getRoom());
            preparedStatement.setString(5, lesson.getDescription());
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createJournal(Journal journal) {
        Connection connection = initConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO journal(" +
                            " id,lesson_id,student_id)" +
                            " VALUES (?, ?, ?)");
            preparedStatement.setInt(1, journal.getId());
            preparedStatement.setInt(2, journal.getLesson_id());
            preparedStatement.setInt(3, journal.getStudent_id());
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
