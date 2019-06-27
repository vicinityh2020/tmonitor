package vicinity.tmonitor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import vicinity.tmonitor.model.ThingType;
import vicinity.tmonitor.model.ThingsOwned;

@RepositoryRestResource(collectionResourceRel = "things_owned", path = "things_owned")
public interface ThingsOwnedRepository extends CrudRepository<ThingsOwned, Long> {

	
}
