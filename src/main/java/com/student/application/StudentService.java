package com.student.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;

    public StudentDto register(StudentDto studentDto) {
        Student student = Student.builder()
                .name(studentDto.getName())
                .address(studentDto.getAddress())
                .build();
        studentRepository.save(student);
        return StudentDto.builder()
                .name(student.getName())
                .address(student.getAddress())
                .build();
    }
}
