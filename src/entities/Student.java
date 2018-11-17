package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private Curriculum curriculum;
    private List<Integer> marks = new ArrayList<>();
    private double avgMark;
    private int endTraining;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, Curriculum curriculum) {
        this.name = name;
        this.curriculum = curriculum;
        this.endTraining = countHour();
    }

    public Student(String name, Curriculum curriculum, List<Integer> marks) {
        this.name = name;
        this.curriculum = curriculum;
        this.marks = marks;
        this.endTraining = countHour();
        this.avgMark = calcAvgMark();
    }

    private double calcAvgMark() {
        return marks.stream().mapToDouble((s)->s).average().getAsDouble();
    }

    private int countHour() {
        LocalDate start_date = curriculum.getStart_date();
        LocalDate current_date = LocalDate.now();
        int numberDay = current_date.getDayOfYear() - start_date.getDayOfYear();
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
        this.avgMark = calcAvgMark();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAvgMark() {
        return avgMark;
    }

    public int getEndTraining() {
        return endTraining;
    }

    @Override
    public String toString() {
        int compareAvgMark = Double.compare((avgMark + endTraining / 8.0 * 5) / 2, 4.5);
        return name +
                " - До окончания обучения по программе " + (curriculum == null ? "Не указан учебный план!" : curriculum.getName()) +
                " осталось " + endTraining + "ч." +
                " Средний балл " + String.format("%.1f", avgMark) + ". " +
                (compareAvgMark == -1 ? "Отчислить" :
                        (compareAvgMark == 0 ? "Вероятно может быть отчислен" : "Может продолжить обучение"));
    }
}
