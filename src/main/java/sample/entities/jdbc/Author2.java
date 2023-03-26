package sample.entities.jdbc;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.LinkedList;
import java.util.List;

public class Author2 {

    @JsonProperty("AUTHOR_ID")
    Object AUTHOR_ID;

    @JsonProperty("AUTHOR_NAME")
    Object AUTHOR_NAME;

    List< Post2> posts=new LinkedList<>();
 
    public Author2(Object AUTHOR_ID, Object AUTHOR_NAME) {
        this.AUTHOR_ID = AUTHOR_ID;
        this.AUTHOR_NAME = AUTHOR_NAME;
    }

    public Object getAUTHOR_ID() {
        return AUTHOR_ID;
    }

    public Object getAUTHOR_NAME() {
        return AUTHOR_NAME;
    }

    public List<Post2> getPosts() {
        return posts;
    }

    public void setPosts(List<Post2> posts) {
        this.posts = posts;
    }
}
