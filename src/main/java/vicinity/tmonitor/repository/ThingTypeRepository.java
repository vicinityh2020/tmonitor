package vicinity.tmonitor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import vicinity.tmonitor.model.ThingType;

@RepositoryRestResource(collectionResourceRel = "things_type", path = "things_type")
public interface ThingTypeRepository extends CrudRepository<ThingType, Long> {

	
}
