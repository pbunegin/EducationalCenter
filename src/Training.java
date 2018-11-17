import entities.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Training {
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        init();
        students.stream().sorted((o1, o2) -> {
            int res = Double.compare(o1.getAvgMark(), o2.getAvgMark());
            if (res != 0)
                return res;
            return o1.getEndTraining() - o2.getEndTraining();
        }).forEachOrdered(System.out::println);
    }

    private static void init() {
        List<Course> courses = new ArrayList<>();
        List<Curriculum> curriculums = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/input/list.txt"), Charset.forName("UTF-8"));
            for (int i = 0; i < lines.size(); i++) {
                switch (Entity.valueOf(lines.get(i))) {
                    case Course:
                        while (++i < lines.size() && !lines.get(i).equals("end")) {
                            courses.add(new Course(lines.get(i).split("\\s+-\\s+")[0],
                                    Integer.parseInt(lines.get(i).split("\\s+-\\s*")[1])));
                        }
                        break;
                    case Curriculum:
                        while (++i < lines.size() && !lines.get(i).equals("end")) {
                            curriculums.add(new Curriculum(lines.get(i).split("\\s+-\\s+")[0],
                                    LocalDate.parse(lines.get(i).split("\\s+-\\s+")[1], DateTimeFormatter.ISO_LOCAL_DATE)));
                        }
                        break;
                    case Student:
                        while (++i < lines.size() && !lines.get(i).equals("end")) {
                            students.add(new Student(lines.get(i)));
                        }
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Curriculum curriculum : curriculums) {
            Collections.shuffle(courses);
            curriculum.setCourseList(courses.subList(0, (int) (Math.random() * courses.size())));
        }
        for (Student student : students) {
            student.setCurriculum(curriculums.get((int) (Math.random() * curriculums.size())));
        }
        training();
    }

    //ставим оценки за прошедшие дни
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
