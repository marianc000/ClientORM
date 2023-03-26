package sample.repositories;

import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ContextConfiguration;
import sample.entities.jdbc.Country2;
  

@JdbcTest
@ContextConfiguration(classes = CountryJdbcRepository.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class CountryJdbcRepositoryTest {

    @Autowired
    CountryJdbcRepository db;

    @Test
    public void testGetAll() throws SQLException {
        List<Country2> l = db.getAll();
  
    }
}
