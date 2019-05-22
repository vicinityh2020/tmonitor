package vicinity.tmonitor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import vicinity.tmonitor.model.Overview;

@RepositoryRestResource(collectionResourceRel = "overview", path = "overview")
public interface OverviewRestRepository extends CrudRepository<Overview, Long> {

	
}
