package sample.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sample.entities.jdbc.Author2;
import sample.entities.jdbc.Country2;
import sample.entities.jdbc.Post2;
import static sample.jdbc.collectors.GroupingBy.groupingBy;
import static sample.repositories.Queries.SELECT_ALL;

@Repository
public class CountryJdbcRepository {
    
    @Autowired
    DataSource ds;
    
    public List<Country2> getAll() throws SQLException {
        return group(load(SELECT_ALL));
    }
    
    List<Country2> load(String sql) throws SQLException {
        try ( Connection con = ds.getConnection();  Statement st = con.createStatement();  ResultSet rs = st.executeQuery(sql)) {
            List<Country2> l = new LinkedList<>();
            while (rs.next()) {
                
                Country2 c = new Country2(rs.getObject("COUNTRY_ID"), rs.getObject("COUNTRY_NAME"));
                Author2 a = new Author2(rs.getObject("AUTHOR_ID"), rs.getObject("AUTHOR_NAME"));
                Post2 p = new Post2(rs.getObject("POST_ID"), rs.getObject("POST_NAME"));
                Author2 a2 = new Author2(rs.getObject("FAN_ID"), rs.getObject("FAN_NAME"));
                
                l.add(c);
                
                if (a.getAUTHOR_ID() != null) {
                    c.getAuthors().add(a);
                    
                    if (p.getPOST_ID() != null) {
                        a.getPosts().add(p);
                        
                        if (a2.getAUTHOR_ID() != null) {
                            p.getLikes().add(a2);
                        }
                    }
                }
            }
            return l;
        }
    }
//

    List<Country2> group(List<Country2> countries) {
        countries = countries.stream()
                .collect(groupingBy(c -> c.getCOUNTRY_ID(), c -> c.getAuthors()));
        
        countries.forEach(c -> {
            List<Author2> l2 = c.getAuthors().stream().
                    collect(groupingBy(a -> a.getAUTHOR_ID(), a -> a.getPosts()));
            
            c.setAuthors(l2);
            l2.forEach(a -> {
                List<Post2> posts = a.getPosts().stream().collect(groupingBy(p -> p.getPOST_ID(), p -> p.getLikes()));
                a.setPosts(posts);
            });
        });
        
        return countries;
    }
}
