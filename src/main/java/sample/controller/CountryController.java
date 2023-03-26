package sample.controller;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.repositories.CountryJdbcRepository;

import sample.repositories.CountryRepository;
import sample.repositories.JdbcTableRepository;
import static sample.repositories.Queries.SELECT_ALL;

@CrossOrigin
@RestController
class CountryController {

    @Autowired
    CountryRepository rep;

    @Autowired
    CountryJdbcRepository jdbc;

    @Autowired
    JdbcTableRepository jdbcTable;

    @GetMapping("/jpa")
    public Object jpa() {
        return rep.getAll();
    }

    @GetMapping("/jdbc")
    public Object jdbc() throws SQLException {
        return jdbc.getAll();
    }

    @GetMapping("/jdbcTable")
    public Object jdbcTable() throws SQLException {
        return jdbcTable.loadAs2DArray("SELECT * FROM OVERVIEW"); 
    }
}
