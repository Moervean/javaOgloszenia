package main;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;

@DataSourceDefinition(
    name = "java:global/JsfCrudDemoDataSource",
    className = "org.h2.jdbcx.JdbcDataSource",
    url = "jdbc:h2:mem:jsfcruddemo;DB_CLOSE_DELAY=-1",
    minPoolSize = 1,
    initialPoolSize = 1,
    user = "sa",
    password = ""
)
@Singleton
public class Configuration {
}

