import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Training {
    private static List<Student> students = new ArrayList<>();


    public static void main(String[] args) {
        init();
//        training();
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAvgMark()>o2.getAvgMark()?1:
                        (o1.getAvgMark()<o2.getAvgMark()?-1:o1.getEndTraining()-o2.getEndTraining());
            }
        });
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void init() {
        List<Course> courses = new ArrayList<>();
        List<Curriculum> curriculums= new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\petr_bunegin\\IdeaProjects\\EducationalCenter\\src\\list.txt")))) {
            String str = "";
            while ((str = reader.readLine())!=null) {
                switch (str) {
                    case "Courses":
                        while (true) {
                            str = reader.readLine();
                            if (str.equals("end"))
                                break;
                            courses.add(new Course(str.split("\\s+-\\s+")[0],Integer.valueOf(str.split("\\s+-\\s*")[1])));
                        }
                        break;
                    case "Curriculum":
                        while (true) {
                            str = reader.readLine();
                            if (str.equals("end"))
                                break;
                            curriculums.add(new Curriculum(str.split("\\s+-\\s+")[0],
                                    LocalDate.parse(str.split("\\s+-\\s+")[1], DateTimeFormatter.ISO_LOCAL_DATE)));
                        }
                        break;
                    case "Students":
                        while (true) {
                            str = reader.readLine();
                            if (str.equals("end"))
                                break;
                            students.add(new Student(str));
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Curriculum curriculum: curriculums){
            Collections.shuffle(courses);
            curriculum.setCourseList(courses.subList(0, (int) (Math.random() * courses.size())));
        }
        for (Student student: students){
            student.setCurriculum(curriculums.get((int) (Math.random() * curriculums.size())));
        }
        training();

        //        Curriculum curriculum1 = new Curriculum("J2EE Developer", new GregorianCalendar(2018, 10, 7),
//                new Course("Технология Java Servlets", 16),
//                new Course("Struts Framework", 24),
//                new Course("Spring Framework", 48),
//                new Course("Hibernate", 20));
//        Curriculum curriculum2 = new Curriculum("Java Developer ", new GregorianCalendar(2018, 9, 30),
//                new Course("Обзор технологий Java", 8),
//                new Course("Библиотека JFC/Swing", 16),
//                new Course("Технология JDBC", 16),
//                new Course("Технология JAX", 52),
//                new Course("Библиотеки commons", 44));
//
//        studentSet.add(new Student("Ivanov Ivan", curriculum1));
//        studentSet.add(new Student("Petrov Petr",curriculum2));
    }

    private static void training() {
        for (Student student : students) {
            LocalDate start_date = student.getCurriculum().getStart_date();
            LocalDate current_date = LocalDate.now();
            int numberDay = current_date.getDayOfYear() - start_date.getDayOfYear();
            for (int i = 0; i < numberDay; i++) {
                student.setMarks((int) (Math.random() * 4 + 2));
            }
        }
    }
}
