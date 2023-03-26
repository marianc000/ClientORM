package sample.entities.jdbc;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.LinkedList;
import java.util.List;

public class Post2 {

    @JsonProperty("POST_ID")
    Object POST_ID;

    @JsonProperty("POST_NAME")
    Object POST_NAME;

    List<Author2> likes = new LinkedList<>();

    public Post2(Object POST_ID, Object POST_NAME) { // for jdbc
        this.POST_ID = POST_ID;
        this.POST_NAME = POST_NAME;
    }

    public Object getPOST_ID() {
        return POST_ID;
    }

    public Object getPOST_NAME() {
        return POST_NAME;
    }

    public List<Author2> getLikes() {
        return likes;
    }
}
