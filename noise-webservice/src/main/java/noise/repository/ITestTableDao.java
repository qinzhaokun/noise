package noise.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import noise.model.testTable;

@Repository
public interface ITestTableDao extends CrudRepository<testTable, Long> {
	
}