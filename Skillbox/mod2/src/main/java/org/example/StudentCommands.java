package org.example;

import org.example.Student;
import org.example.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class StudentCommands {

    @Autowired
    private StudentRepository studentRepository;

    @ShellMethod("Добавить студента")
    public void addStudent(
            @ShellOption(value = {"--firstName"}, help = "Имя студента") String firstName,
            @ShellOption(value = {"--lastName"}, help = "Фамилия студента") String lastName,
            @ShellOption(value = {"--age"}, help = "Возраст студента") int age) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setAge(age);
        studentRepository.save(student);
        System.out.println("Студент добавлен: " + student);
    }

    @ShellMethod("Просмотреть всех студентов")
    public void viewStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            System.out.println("Список студентов пуст.");
        } else {
            students.forEach(System.out::println);
        }
    }

    @ShellMethod("Удалить студента")
    public void deleteStudent(@ShellOption(value = {"--id"}, help = "ID студента для удаления") Long id) {
        studentRepository.delete(id);
        System.out.println("Студент с ID " + id + " удалён.");
    }

    @ShellMethod("Очистить список студентов")
    public void clearStudents() {
        studentRepository.clear();
        System.out.println("Список студентов очищен.");
    }
}
