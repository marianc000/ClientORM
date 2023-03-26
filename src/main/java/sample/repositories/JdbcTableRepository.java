package sample.repositories;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import static sample.repositories.Queries.SELECT_ALL;

@Repository
public class JdbcTableRepository {

    @Autowired
    DataSource ds;
 
    public List loadAs2DArray(String sql) throws SQLException {

        try (Connection con = ds.getConnection(); 
                Statement st = con.createStatement(); 
                ResultSet rs = st.executeQuery(sql)) {
            ResultSetMetaData md = rs.getMetaData();
            int colCount = md.getColumnCount();

            List rows = new LinkedList<>();
            List row = new LinkedList<>();
            for (int c = 1; c <= colCount; c++) {
                row.add(md.getColumnLabel(c));
            }
            rows.add(row);
            while (rs.next()) {
                row = new LinkedList<>();
                for (int c = 1; c <= colCount; c++) {
                    row.add(rs.getObject(c));
                }
                rows.add(row);
            }

            return rows;
        }
    }
}
