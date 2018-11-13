import java.util.*;

public class Training {
    private static Set<Student> studentSet = new TreeSet<>();


    public static void main(String[] args) {
        init();
        training();
        for (Student student: studentSet){
            System.out.println(student);
        }
    }

    private static void init() {
        new Curriculum("J2EE Developer", new GregorianCalendar(2018, 11, 7),
                new Course("Технология Java Servlets", 16),
                new Course("Struts Framework", 24),
                new Course("Spring Framework", 48),
                new Course("Hibernate", 20));
        new Curriculum("Java Developer ", new GregorianCalendar(2018, 11, 14),
                new Course("Обзор технологий Java", 8),
                new Course("Библиотека JFC/Swing", 16),
                new Course("Технология JDBC", 16),
                new Course("Технология JAX", 52),
                new Course("Библиотеки commons", 44));

        studentSet.add(new Student("Ivanov Ivan"));
        studentSet.add(new Student("Petrov Petr"));
    }

    private static void training() {
        for (Student student : studentSet) {
            Calendar start_date = student.getCurriculum().getStart_date();
            Calendar current_date = new GregorianCalendar();
            int numberDay = current_date.get(Calendar.DAY_OF_YEAR) - start_date.get(Calendar.DAY_OF_YEAR);
            for (int i = 0; i < numberDay; i++) {
                student.setMarks((int) (Math.random() * 4 + 1));
            }
        }
    }
}
