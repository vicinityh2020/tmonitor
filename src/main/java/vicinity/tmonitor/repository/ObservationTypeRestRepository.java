package vicinity.tmonitor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import vicinity.tmonitor.model.ObservationType;

@RepositoryRestResource(collectionResourceRel = "observation", path = "observation")
public interface ObservationTypeRestRepository extends CrudRepository<ObservationType, Long> {

	
}
