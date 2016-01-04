package noise.repository;

import java.util.List;

import noise.model.Grid;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

public interface IGridDao extends CrudRepository<Grid, Long>{
	public List<Grid> findByCity(String city);
	
	Grid findAll(Specification<Grid> spec);
}
