package com.beermingham.meets.infrastructure.repository;

import java.time.LocalDate;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.beermingham.meets.domain.model.Meet;

public interface IMongoMeetRepository extends MongoRepository<Meet, String> {

	public boolean existsByDate(LocalDate date);

}
