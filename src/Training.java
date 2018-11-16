import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Training {
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        init();
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int res = Double.compare(o1.getAvgMark(),o2.getAvgMark());
                if (res != 0)
                    return res;
                return o1.getEndTraining()-o2.getEndTraining();
            }
        });
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void init() {
        List<Course> courses = new ArrayList<>();
        List<Curriculum> curriculums= new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(
                Training.class.getResource("").getPath().substring(1)+"list.txt"),Charset.forName("UTF-8")))) {
            String str = "";
            while ((str = reader.readLine())!=null) {
                switch (str) {
                    case "Courses":
                        while ((str = reader.readLine())!=null) {
                            if (str.equals("end"))
                                break;
                            courses.add(new Course(str.split("\\s+-\\s+")[0],Integer.parseInt(str.split("\\s+-\\s*")[1])));
                        }
                        break;
                    case "Curriculum":
                        while ((str = reader.readLine())!=null) {
                            if (str.equals("end"))
                                break;
                            curriculums.add(new Curriculum(str.split("\\s+-\\s+")[0],
                                    LocalDate.parse(str.split("\\s+-\\s+")[1], DateTimeFormatter.ISO_LOCAL_DATE)));
                        }
                        break;
                    case "Students":
                        while ((str = reader.readLine())!=null) {
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
