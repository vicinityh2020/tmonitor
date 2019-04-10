package vicinity.tmonitor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import vicinity.tmonitor.model.OrganizationOverview;

@RepositoryRestResource(collectionResourceRel = "organization", path = "organization_overview")
public interface OrganizationOverviewRestRepository extends CrudRepository<OrganizationOverview, Long> {

	
}
