package com.johnscodinglab.controller;

import com.johnscodinglab.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private static final List<Student> STUDENTS = List.of(
            new Student(1, "James Bond"),
            new Student(2, "Maria Jones"),
            new Student(3, "Anna Smith")
    );

    @GetMapping
    public List<Student> getStudents() {
        return STUDENTS;
    }

    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId) {
        return STUDENTS.stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("student not found"));
    }

    @GetMapping("/user")
    public String userEndpoint() {
        return "Hello User";
    }


    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Hello Admin";
    }
}
