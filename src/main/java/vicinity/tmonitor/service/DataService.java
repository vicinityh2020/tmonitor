package vicinity.tmonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {

	
	@Autowired
	private OverviewService serviceOverview;
	@Autowired
	private DashboardService sericeDashboard;
	@Autowired
	private OrganizationOverviewService serviceOrganization;
	@Autowired
	private ThingTypeService serviceThingsType;
	@Autowired
	private ObservationTypeService serviceObservationType;
	
	public void updateData() {
		serviceOverview.updateData();
		System.out.println("\tOverview updated");
		serviceOrganization.updateData();
		System.out.println("\tOrganisations updated");
		serviceThingsType.updateData();
		System.out.println("\tThings updated");
		serviceObservationType.updateData();
		System.out.println("\tObservations updated");
		sericeDashboard.createVICINITYGeneralDashboard();
		System.out.println("\tDashboards updated");
		System.out.println("Monitor updated");
	}
}
