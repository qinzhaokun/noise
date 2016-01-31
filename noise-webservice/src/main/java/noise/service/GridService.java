package noise.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import noise.model.Grid;
import noise.repository.IGridDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class GridService {
	@Autowired
	private IGridDao iGridDao;
	
	public List<Grid> getGridByCity(String city){
		return iGridDao.findByCity(city);
	}
	
	public Grid getGridByLongitudeAndLatitude(final Double longitude, final Double latitude){
		/*String orderBy = "id";
		Direction direction = Sort.Direction.ASC;
		Pageable pageable = new PageRequest(1, 1, new Sort(direction,orderBy));*/
		Specification<Grid> spec = new Specification<Grid>() {  
				@Override
	        	public Predicate toPredicate(Root<Grid> root,  
	        	        CriteriaQuery<?> query, CriteriaBuilder cb) {  
					Predicate predicate = cb.lessThanOrEqualTo(root.get("longitude1").as(Double.class), longitude);
		        	predicate = cb.and(cb.greaterThanOrEqualTo(root.get("longitude2").as(Double.class), longitude));
		        	predicate = cb.and(cb.greaterThanOrEqualTo(root.get("latitude1").as(Double.class), latitude));
		        	predicate = cb.and(cb.lessThanOrEqualTo(root.get("latitude3").as(Double.class), latitude));
		        	return predicate;
	        	}

			 
	        	}; 
	        	return iGridDao.findAll(spec);
	        
	}

}
