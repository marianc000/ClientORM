package sample.entities.jpa;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import javax.persistence.Column;

@Entity
@Table(name = "AUTHOR")
public class Author {

    @Id
    @Column(name = "AUTHOR_ID")
    @JsonProperty("AUTHOR_ID")
    Integer AUTHOR_ID;

    @Column(name = "AUTHOR_NAME")
    @JsonProperty("AUTHOR_NAME")
    String AUTHOR_NAME;

    @OneToMany
    @JoinColumn(name = "AUTHOR_ID")
    List< Post> posts;

    Author() {
    }

    public List<Post> getPosts() {
        return posts;
    }

    Author(Integer AUTHOR_ID, String AUTHOR_NAME) {
        this.AUTHOR_ID = AUTHOR_ID;
        this.AUTHOR_NAME = AUTHOR_NAME;
    }

    public Author copyWithoutPosts() {
        return new Author(AUTHOR_ID, AUTHOR_NAME);
    }
}
