package ua.nure.chub.Elective.db.entity;

/**
 * Author Lera
 * created 08.09.2017.
 */
public class StudentMark {
    private int userId;
    private int courseId;
    private String userFirstName;
    private String userLastName;
    private double mark;

    public StudentMark(int userId, int courseId, String userFirstName, String userLastName, double mark) {
        this.userId = userId;
        this.courseId = courseId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.mark = mark;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
