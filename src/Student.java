import java.util.List;

public class Student {
    private String name;
    private Curriculum curriculum;
    private List<Integer> marks;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, Curriculum curriculum,List<Integer> marks) {
        this.name = name;
        this.curriculum = curriculum;
        this.marks = marks;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public List<Integer> getMarks() {
        return marks;
    }

    public void setMarks(int mark) {
        this.marks.add(mark);
    }
}
