import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Training {
    private static List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        init();

    }

    private static void init(){
        new Curriculum("J2EE Developer",new GregorianCalendar(2018,11,7),
                new Course("Технология Java Servlets",16),
                new Course("Struts Framework", 24),
                new Course("Spring Framework", 48),
                new Course("Hibernate",20));
        new Curriculum("Java Developer ",new GregorianCalendar(2018,11,14),
                new Course("Обзор технологий Java",8),
                new Course("Библиотека JFC/Swing",16),
                new Course("Технология JDBC",16),
                new Course("Технология JAX",52),
                new Course("Библиотеки commons",44));

        studentList.add(new Student("Ivanov Ivan"));
        studentList.add(new Student("Petrov Petr"));

    }
}
