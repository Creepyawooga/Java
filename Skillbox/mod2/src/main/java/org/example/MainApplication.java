package org.example;

import org.example.Student;
import org.example.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    @Autowired
    private StudentRepository studentRepository;

    @Value("${app.startup.createStudents}")
    private boolean createStudents;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            if (createStudents) {
                for (int i = 1; i <= 5; i++) {
                    Student student = new Student();
                    student.setFirstName("Имя" + i);
                    student.setLastName("Фамилия" + i);
                    student.setAge(20 + i);
                    studentRepository.save(student);
                }
            }
        };
    }
}
