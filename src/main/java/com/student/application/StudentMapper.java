package com.student.application;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentMapper {

    public StudentDto toDto(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .name(student.getName())
                .address(student.getAddress())
                .build();
    }

    public List<StudentDto> toDtoList(List<Student> students) {
        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student student: students) {
            studentDtos.add(StudentDto.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .address(student.getAddress())
                    .build());
        }

        return studentDtos;
    }
}
