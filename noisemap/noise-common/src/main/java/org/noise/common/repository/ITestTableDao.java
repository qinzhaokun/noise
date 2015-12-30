package org.noise.common.repository;

import org.noise.common.model.testTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ITestTableDao extends CrudRepository<testTable, Long> {
	
}