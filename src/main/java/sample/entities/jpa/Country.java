package sample.entities.jpa;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTRY")
public class Country {

    @Id
    @Column(name = "COUNTRY_ID")
    @JsonProperty("COUNTRY_ID")
    Integer COUNTRY_ID;

    @Column(name = "COUNTRY_NAME")
    @JsonProperty("COUNTRY_NAME")
    String COUNTRY_NAME;

    @OneToMany
    @JoinColumn(name = "COUNTRY_ID")
    List<Author> authors;

    public List<Author> getAuthors() {
        return authors;
}
}
