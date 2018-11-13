import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Student  implements Comparable<Student>{
    private String name;
    private Curriculum curriculum;
    private List<Integer> marks;
    private float avgMark;
    private int endTraining;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, Curriculum curriculum,List<Integer> marks) {
        this.name = name;
        this.curriculum = curriculum;
        this.marks = marks;
        this.endTraining = countHour();
    }

    private int countHour() {
        Calendar start_date = curriculum.getStart_date();
        Calendar current_date = new GregorianCalendar();
        int numberDay = current_date.get(Calendar.DAY_OF_YEAR) - start_date.get(Calendar.DAY_OF_YEAR);
        return curriculum.getDuration() - numberDay * 8;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
        this.endTraining = countHour();
    }

    public List<Integer> getMarks() {
        return marks;
    }

    public void setMarks(int mark) {
        this.marks.add(mark);
        int sumMark = 0;
        for (Integer m: marks){
            sumMark += m;
        }
        avgMark = Math.round((sumMark/marks.size())*10)/10;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAvgMark() {
        return avgMark;
    }

    public int getEndTraining() {
        return endTraining;
    }

    @Override
    public String toString() {
        return name +
                " - До окончания обучения по программе " + curriculum.getName() +
                " осталось " + endTraining + "ч." +
                " Средний балл " + String.format("%.1f", avgMark) + ". " +
                (avgMark < 4.5 ? "Отчислить" : "Может продолжить обучение");
    }

    @Override
    public int compareTo(Student o) {
        int res = (int) (avgMark - o.getAvgMark());
        if (res == 0)
            res = endTraining - o.getEndTraining();
        return res;
    }
}
