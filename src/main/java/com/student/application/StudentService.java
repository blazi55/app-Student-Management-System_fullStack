package com.student.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;

    private StudentMapper studentMapper;

    public StudentDto register(StudentDto studentDto) {
        Student student = Student.builder()
                .id(studentDto.getId())
                .name(studentDto.getName())
                .address(studentDto.getAddress())
                .build();
        studentRepository.save(student);
        return studentMapper.toDto(student);
    }
}
