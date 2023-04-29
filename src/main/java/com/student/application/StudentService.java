package com.student.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student with ID: " + studentId + " doesn't not exists"));
        return studentMapper.toDto(student);
    }

    public List<StudentDto> getAll() {
        List<Student> students = (List<Student>) studentRepository.findAll();
        return studentMapper.toDtoList(students);
    }

    public void removeStudent(long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student with ID: " + studentId + " doesn't not exists"));
        studentRepository.delete(student);
    }

    public List<Student> getStudentByName(String name) {
        return studentRepository.findStudentByName(name);
    }
}
