package sample.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sample.entities.jpa.Country;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class CountryRepositoryTests {

    @Autowired
    CountryRepository db;

    @Autowired
    ObjectMapper om;

    @Test
    void testGetAll() throws JsonProcessingException {
        var l = db.getAll();
        om.writeValueAsString(l);
    }

}
