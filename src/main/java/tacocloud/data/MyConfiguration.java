package tacocloud.data;

import org.springframework.jdbc.core.JdbcTemplate;

//@Configuration
public class MyConfiguration {
    //@Bean
    JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate();
    }
}
