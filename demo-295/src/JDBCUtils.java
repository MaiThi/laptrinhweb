import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    static String driverclass = null;
    static String url = null;
    static String name = null;
    static String password = null;

    static{
        try {
            Properties pros = new Properties();
            // Using classLoader to get resources under src
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            pros.load(is);//Read input stream

            //read attribute
            driverclass = pros.getProperty("driverclass");
            url = pros.getProperty("url");
            name = pros.getProperty("name");
            password = pros.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Get database connection
     * @return
     */

    public static Connection getConn(){

        Connection conn = null;
        try {
            //1. Register driverclass with reflection
            Class.forName(driverclass);
            //2. Establish connection parameter 1: Protocol + ACCESS database, parameter 2: user name, parameter 3: password.
            conn = DriverManager.getConnection(url, name, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConn(Connection conn){
        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }

    public static void closePs(PreparedStatement ps){
        try {
            if(ps !=null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps=null;
        }
    }
    public static void closeRs(ResultSet rs){
        try {
            if(rs !=null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs=null;
        }
    }

    /**
     *  Close connection resources
     * @param conn
     */
    public static void release(Connection conn, PreparedStatement ps){
        closeConn(conn);
        closePs(ps);
    }
    public static void release(Connection conn, PreparedStatement ps, ResultSet rs){
        closePs(ps);
        closeConn(conn);
        closeRs(rs);
    }
}
