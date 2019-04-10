package vicinity.tmonitor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import vicinity.tmonitor.model.OrganizationOverview;
import vicinity.tmonitor.model.OrganizationThing;

@RepositoryRestResource(collectionResourceRel = "organization", path = "organization")
public interface OrganizationThingRestRepository extends CrudRepository<OrganizationThing, Long> {

	
}
