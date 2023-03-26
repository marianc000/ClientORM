package sample.entities.jpa;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;

@Entity
@Table(name = "POST")
public class Post {

    @Id
    @Column(name = "POST_ID")
    @JsonProperty("POST_ID")
    Integer POST_ID;

    @Column(name = "POST_NAME")
    @JsonProperty("POST_NAME")
    String POST_NAME;

    @ManyToMany
    @JoinTable(name = "POST_LIKES", inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID"), joinColumns = @JoinColumn(name = "POST_ID"))
    List<Author> likes;

    public void removePostsInFans() {
        likes = likes.stream().map(a -> a.copyWithoutPosts()).toList();
    }

    @PostLoad
    public void doesNotWorkWithoutPersistenceContext() {
        System.out.println(">@PostLoad");
    }
}
