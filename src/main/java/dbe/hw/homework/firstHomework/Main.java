package dbe.hw.homework.firstHomework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String studentiTotl =  "C:\\Users\\Lenovo\\Desktop\\IT\\Java\\DB_Tech_School\\src\\main\\java\\dbe\\hw\\homework\\firstHomework\\chestiiTema\\studentiTotal.txt";
        String studentFile3 =  "C:\\Users\\Lenovo\\Desktop\\IT\\Java\\DB_Tech_School\\src\\main\\java\\dbe\\hw\\homework\\firstHomework\\chestiiTema\\facultate2";

        String[] array = FirstHomework.getTxt(studentFile3);
        List<Student> studentList = new ArrayList<>();
        for (String source: array) {
            studentList.addAll(FirstHomework.readStudents(source));
        }
        studentList.sort(Comparator.comparing(Student::getCredit).reversed().thenComparing(Student::getSecondName));

        FirstHomework.write(studentList, studentiTotl);
    }
}
