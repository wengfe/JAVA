public class Course {
    private int id;
    private String courseName;
    private int  teacher_id;

    public Course(int id, String courseName, int teacher_id) {
        this.id = id;
        this.courseName = courseName;
        this.teacher_id = teacher_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }
}
