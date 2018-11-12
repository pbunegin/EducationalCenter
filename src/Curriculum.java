import java.util.*;

public class Curriculum {
    private String name;
    private Calendar start_date;
    private List<Course> courseList;

    public Curriculum(String name,Calendar start_date,List<Course> courseList) {
        this.name = name;
        this.start_date = start_date;
        this.courseList = courseList;
    }

    public Curriculum(String name,Calendar start_date, Course... list) {
        this.name = name;
        this.start_date = start_date;
        this.courseList = Arrays.asList(list);
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
