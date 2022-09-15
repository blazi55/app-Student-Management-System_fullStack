package com.student.application;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor
@CrossOrigin
public class StudentController {

    private final StudentService studentService;

    @PostMapping()
    public StudentDto addStudent(@RequestBody CreateStudentDto createStudent) {
        return studentService.register(createStudent);
    }

    @DeleteMapping("{studentId}")
    public void removeStudent(@PathVariable long studentId) {
        studentService.removeStudent(studentId);
    }

    @GetMapping("{studentId}")
    public StudentDto getStudent(@PathVariable long studentId) {
        return studentService.getStudent(studentId);
    }

    @GetMapping("/all")
    public List<StudentDto> getStudents() {
        return studentService.getAll();
    }

    @GetMapping("/studentName")
    public Student getStudentByName(@RequestParam String name) {
        return studentService.getStudentByName(name);
    }
}
