package com.student.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;

    private StudentMapper studentMapper;

    public StudentDto register(CreateStudent createStudent) {
        final Student student = Student.builder()
                .name(createStudent.getName())
                .address(createStudent.getAddress())
                .build();
        studentRepository.save(student);
        return studentMapper.toDto(student);
    }
}
