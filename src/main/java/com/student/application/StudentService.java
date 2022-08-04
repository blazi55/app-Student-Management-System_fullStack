package com.student.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentDto register(CreateStudentDto createStudentDto) {
        final Student student = Student.builder()
                .name(createStudentDto.getName())
                .address(createStudentDto.getAddress())
                .build();
        studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    public StudentDto getStudent(long id) {
        Student student = studentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return studentMapper.toDto(student);
    }
}
