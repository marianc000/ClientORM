package sample.entities.jdbc;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.LinkedList;
import java.util.List;

public class Country2 {

    @JsonProperty("COUNTRY_ID")
    Object COUNTRY_ID;

    @JsonProperty("COUNTRY_NAME")
    Object COUNTRY_NAME;

    List<Author2> authors=new LinkedList<>();
 
    public Country2(Object COUNTRY_ID, Object COUNTRY_NAME) { // for JDBC
        this.COUNTRY_ID = COUNTRY_ID;
        this.COUNTRY_NAME = COUNTRY_NAME;
    }

    public Object getCOUNTRY_ID() {
        return COUNTRY_ID;
    }

    public Object getCOUNTRY_NAME() {
        return COUNTRY_NAME;
    }

    public List<Author2> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author2> authors) { //convenience for jdbc
        this.authors = authors;
    }

}
