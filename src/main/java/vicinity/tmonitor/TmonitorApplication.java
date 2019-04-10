package vicinity.tmonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import vicinity.tmonitor.service.DataService;

@EnableScheduling
@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class ) // This prevents Spring to create default error-handling route /error 
public class TmonitorApplication {

	@Autowired
	public DataService service;
	
	public static String ENDPOINT ;//= "http://147.232.202.108:9080/repositories/vicinity-test";
	public static String GRAFANA_ENDPOINT;// = "http://localhost:3000";
	public static String API_KEY;// = "eyJrIjoiZkZzV2VxNlE1RWo0TzVKNDFBNnpaeTFFcHJETG5yVmYiLCJuIjoibW9uaXRvciIsImlkIjoxfQ==";
	
	private static final String SREPOSITORY_KEY = "--server.srepository=";
	private static final String GRAFANA_ENDPOINT_KEY = "--server.grafana=";
	private static final String API_KEY_KEY = "--server.token=";
	
	public static void main(String[] args) {
		for(String arg:args) {
			if(arg.startsWith(SREPOSITORY_KEY)) {
				ENDPOINT = arg.replace(SREPOSITORY_KEY, "");
			}else if(arg.startsWith(GRAFANA_ENDPOINT_KEY)) {
				GRAFANA_ENDPOINT = arg.replace(GRAFANA_ENDPOINT_KEY, "");
			}else if(arg.startsWith(API_KEY_KEY)) {
				API_KEY = arg.replace(API_KEY_KEY, "");
			}else {
				System.out.println("Unknown argument: "+arg);
			}
			
		}
		
		SpringApplication.run(TmonitorApplication.class, args);
	}

	
	
	
	@Scheduled(fixedDelay = 86400000) // 1 day update
	//@Scheduled(fixedDelay = 120000) // 1 day update
	public void scheduledUpdate() {
		System.out.println("Updating the data of the database");
		service.updateData();
	}
	
	
	
	
	

	
}
