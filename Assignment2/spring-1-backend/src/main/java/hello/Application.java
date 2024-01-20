package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootApplication
public class Application implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {
        log.info("Creating table");
        jdbcTemplate.execute("DROP TABLE admissions IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE admissions(" +
                "id SERIAL, name VARCHAR(255), address VARCHAR(255), email VARCHAR(255)" +
                ", phone VARCHAR(255), level VARCHAR(255), admissionStatus VARCHAR(255))"
        );
    }
}