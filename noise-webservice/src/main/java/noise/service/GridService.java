package noise.service;

import java.util.List;

import javax.persistence.criteria.Predicate;

import noise.model.Grid;
import noise.repository.IGridDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class GridService {
	@Autowired
	private IGridDao iGridDao;
	
	public List<Grid> getGridByCity(String city){
		return iGridDao.findByCity(city);
	}
	
	public Grid getGridByLongitudeAndLatitude(Double longitude,Double latitude){
		/*String orderBy = "id";
		Direction direction = Sort.Direction.ASC;
		Pageable pageable = new PageRequest(1, 1, new Sort(direction,orderBy));*/
	        return iGridDao.findAll((root,query,cb) -> {
	        	Predicate predicate = cb.lessThanOrEqualTo(root.get("longitude1").as(Double.class), longitude);
	        	predicate = cb.and(cb.greaterThanOrEqualTo(root.get("longitude2").as(Double.class), longitude));
	        	predicate = cb.and(cb.greaterThanOrEqualTo(root.get("latitude1").as(Double.class), latitude));
	        	predicate = cb.and(cb.lessThanOrEqualTo(root.get("latitude3").as(Double.class), latitude));
	        	return predicate;
	        });
	}

}
