package dbe.hw.homework.secondHomework;

import dbe.hw.homework.firstHomework.FirstHomework;
import dbe.hw.homework.firstHomework.Student;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.*;

@AllArgsConstructor
public class SecondHomework  {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String studentiTotl =  "C:\\Users\\Lenovo\\Desktop\\IT\\Java\\DB_Tech_School\\src\\main\\java\\dbe\\hw\\homework\\firstHomework\\chestiiTema\\studentiTotal.txt";
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Future<List<Student>>> futures = new ArrayList<>();
        for (int i=0; i<2; i++) {
             Future<List<Student>> future = executorService.submit(new Callable<List<Student>>() {
                @Override
                public List<Student> call() throws Exception {
                    return FirstHomework.readStudents(studentiTotl);
                }
            });
            futures.add(future);
        }

        for (Future<List<Student>> future : futures){
            System.out.println(future.get());
            System.out.println("bla");
        }
        executorService.shutdown();

    }
}
