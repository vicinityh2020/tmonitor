# VICINITY Things Monitor

This software artefact connects to the Semantic Repository of VICINITY and performs an analysis of its content, as a result, the VICINITY Things Monitor fills a database summarising the findings. In order to visualise its results, the VICINITY Things Monitor instantiates a Grafana dashboard named ThingsInVICINITY with an overview of what has being registered in VICINITY. In addition, for each organisation, it generates a dashboard with the organisation's name and an analysis of the resources registered by such entity.

## Quick-start

### Compilation
To compile the Things Monitor project run. **However a compiled jar can be found in the ./target folder**

```
mvn clean package -DskipTests
```

### Dependencies

In order the monitor to work, we need to install [Grafana](https://grafana.com/) and a MySQL database. In the MySQL it is necessary to create a database named **dashboard**. Finally, Grafana must be configured to connect to such database. The database is expected to be configured as follows:

```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:8889/dashboard?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
```

Otherwise modify the application.properties file of the project and compile it again

### Running the monitor

To run the monitor execute in a console the following command
```
java -jar tmonitor.jar --server.port=8080 --server.grafana=[GRAFANA_ENDPOINT] --server.token=[GRAFANA_API_KEY] --server.srepository=[SEMANITC_REPOSIOTRY_ENDPOINT]
```


