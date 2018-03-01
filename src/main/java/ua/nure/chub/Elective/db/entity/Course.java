package ua.nure.chub.Elective.db.entity;

import java.sql.Date;
import java.util.Objects;

/**
 * Author Lera
 * created 01.09.2017.
 */
public class Course {
    private static final long serialVersionUID = 2854980309005527526L;
    private int course_id;
    private String name;
    private String topic;
    private String teacherName;
    private double duration;
    private Date startDate;
    private Date endDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date start_date) {

        this.startDate = start_date;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date end_date) {
        this.endDate = end_date;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getFirstName() {
        return teacherName.split(" ")[0];
    }

    public String getLastName() {
        return teacherName.split(" ")[1];
    }

    @Override
    public String toString() {
        return "Course [name=" + name
                + ", topic=" + topic
                + ", teacher=" + teacherName
                + ", duration=" + duration
                + ", start_date=" + startDate
                + ", end_date=" + endDate + "]";
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = course_id;
        result = 31 * result + name.hashCode();
        result = 31 * result + topic.hashCode();
        result = 31 * result + (teacherName != null ? teacherName.hashCode() : 0);
        temp = Double.doubleToLongBits(duration);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return course_id == course.course_id &&
                Double.compare(course.duration, duration) == 0 &&
                Objects.equals(name, course.name) &&
                Objects.equals(topic, course.topic) &&
                Objects.equals(teacherName, course.teacherName) &&
                Objects.equals(startDate, course.startDate) &&
                Objects.equals(endDate, course.endDate);
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
