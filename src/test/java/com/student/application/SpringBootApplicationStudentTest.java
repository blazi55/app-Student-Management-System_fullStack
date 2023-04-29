package com.student.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class SpringBootApplicationStudentTest {

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentMapper studentMapper;

	@MockBean
	private StudentRepository studentRepository;

	@Test
	void should_register_student() {
		// given
		final CreateStudentDto createStudentDto = new CreateStudentDtoSample().builder().build();
		final Student student = Student.builder()
				.name(createStudentDto.getName())
				.address(createStudentDto.getAddress())
				.build();
		final StudentDto studentDto = studentService.register(createStudentDto);

		// when
		when(studentRepository.save(student)).thenReturn(student);

		// then
		assertEquals(studentDto, studentService.register(createStudentDto));
	}

	@Test
	void should_get_single_student() {
		// given
		final CreateStudentDto createStudentDto = new CreateStudentDtoSample().builder().build();
		final Student student = Student.builder()
				.id(1L)
				.name(createStudentDto.getName())
				.address(createStudentDto.getAddress())
				.build();

		// when
		studentRepository.save(student);
		when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));

		// then
		assertThat(studentService.getStudent(student.getId())).isEqualToComparingFieldByField(student);
	}

	@Test
	void should_get_students_by_the_same_name() {
		// given
		final CreateStudentDto createStudentDto = new CreateStudentDtoSample().builder().build();
		final Student firstStudent = Student.builder()
				.name(createStudentDto.getName())
				.address(createStudentDto.getAddress())
				.build();
		final Student secondStudent = Student.builder()
				.name(createStudentDto.getName())
				.address(createStudentDto.getAddress())
				.build();
		studentRepository.save(firstStudent);
		studentRepository.save(secondStudent);

		// when
		when(studentRepository.findStudentByName(createStudentDto.getName())).thenReturn(
				Arrays.asList(
						firstStudent,
						secondStudent
				)
		);

		// then
		assertThat(studentService.getStudentByName(createStudentDto.getName())).hasSize(2);
	}

	@Test
	void should_remove_single_student() {
		// given
		final CreateStudentDto createStudentDto = new CreateStudentDtoSample().builder().build();
		final Student student = Student.builder()
				.id(1L)
				.name(createStudentDto.getName())
				.address(createStudentDto.getAddress())
				.build();
		studentRepository.save(student);

		// when
		when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		studentService.removeStudent(student.getId());

		// then
		verify(studentRepository, times(1)).delete(student);
	}
}