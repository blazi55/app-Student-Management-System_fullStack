package com.student.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public Student register(CreateStudentDto createStudent) {
        final Student student = Student.builder()
                .name(createStudent.getName())
                .address(createStudent.getAddress())
                .build();
        studentRepository.save(student);
        return student;
    }
}
