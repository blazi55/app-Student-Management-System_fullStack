package com.student.application;

import lombok.Builder;

import java.util.UUID;

import static java.util.Optional.ofNullable;

public class CreateStudentDtoSample {

	@Builder
	public CreateStudentDto build(final String name, final String address) {
		return new CreateStudentDto(
				ofNullable(name).orElse(UUID.randomUUID().toString()),
				ofNullable(address).orElse(UUID.randomUUID().toString())
		);
	}
}
