package com.beermingham.meets.domain.repository;

import java.time.LocalDate;
import java.util.List;

import com.beermingham.meets.domain.model.Meet;

public interface IMeetRepository {

	boolean existsByDate(LocalDate date);

	Meet get(String id);

	List<Meet> getAll();

	Meet save(Meet meet);
}
