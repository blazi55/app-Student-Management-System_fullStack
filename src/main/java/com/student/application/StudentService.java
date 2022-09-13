package com.student.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public StudentDto getStudent(long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(IllegalArgumentException::new);
        return studentMapper.toDto(student);
    }

    public List<StudentDto> getAll() {
        List<Student> students = (List<Student>) studentRepository.findAll();
        return studentMapper.toDtoList(students);
    }

    public void removeStudent(long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(IllegalArgumentException::new);
        studentRepository.delete(student);
    }
}
