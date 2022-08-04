package com.student.application;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping()
    public StudentDto addStudent(@RequestBody CreateStudentDto createStudent) {
        return studentService.register(createStudent);
    }

    @GetMapping("{studentId}")
    public StudentDto getStudent(@PathVariable long studentId) {
        return studentService.getStudent(studentId);
    }
}
