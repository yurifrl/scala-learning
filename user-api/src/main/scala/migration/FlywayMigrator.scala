package migration

import io.getquill.util.LoadConfig
import org.flywaydb.core.Flyway

object FlywayMigrator {
    private config = LoadConfig("database")

    private host = config.getString("dataSource.serverName")
    private port = config.getInt("dataSource.portNumber")
    private database = config.getString("dataSource.databaseName")

    private user = config.getString("dataSource.user")
    private password = config.getString("dataSource.password")

    private url = s"jdbc:postgresql://$host:$port/$database"

    private flyway: Flyway = Flyway.configure.dataSource(url, user, password).load

    def migrate(): Int = {
        flyway.migrate()
    }

    def clean(): Int = {
        flyway.clean() //DON'T DO THIS!! FOR TESTING PURPOSES ONLY
    }
}