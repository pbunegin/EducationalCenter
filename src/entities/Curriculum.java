package entities;

import java.time.LocalDate;
import java.util.*;

public class Curriculum {
    private String name;
    private LocalDate start_date;
    private List<Course> courseList;
    private int duration;

    public Curriculum(String name, LocalDate start_date) {
        this.name = name;
        this.start_date = start_date;
    }

    public Curriculum(String name, LocalDate start_date, Course... list) {
        this.name = name;
        this.start_date = start_date;
        this.courseList = Arrays.asList(list);
        this.duration = sumDurationCourse();
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
        this.duration = sumDurationCourse();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public int getDuration() {
        return duration;
    }

    private int sumDurationCourse() {
        int sumHour = 0;
        for (Course course: courseList){
            sumHour += course.getDuration();
        }
        return sumHour;
    }
}
