package com.beermingham.meets.infrastructure.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.beermingham.meets.domain.model.Meet;
import com.beermingham.meets.domain.repository.IMeetRepository;

@Repository
@Qualifier(value = "meetRepository")
public class MongoMeetRepository implements IMeetRepository {

	private IMongoMeetRepository iMongoMeetRepository;

	public MongoMeetRepository(IMongoMeetRepository iMongoMeetRepository) {
		this.iMongoMeetRepository = iMongoMeetRepository;
	}

	@Override
	public boolean existsByDate(LocalDate date) {
		return iMongoMeetRepository.existsByDate(date);
	}

	@Override
	public Meet get(String id) {
		return iMongoMeetRepository.findById(id).orElse(null);
	}

	@Override
	public List<Meet> getAll() {
		return iMongoMeetRepository.findAll();
	}

	@Override
	public Meet save(Meet meet) {
		return iMongoMeetRepository.save(meet);
	}
}
