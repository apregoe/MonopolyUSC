package MonopolyServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Created by avast on 11/19/2015.
 */
public class DatabaseConnector {
    private String CONNECTION_NAME = "jdbc:mysql://45.55.25.51:3306/userdatabase?user=app&password=cs201";
    public DatabaseConnector() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(CONNECTION_NAME);
            Statement st = conn.createStatement();

            st.close();
            conn.close();
        } catch (SQLException sqle){
            System.out.println("SQLException: " + sqle.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println("ClassNotFoundException: " + cnfe.getMessage());
        }
    }

    public boolean addUser(String username, String password) { //returns false if user already exists
        try{
            Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
            Connection conn = DriverManager.getConnection(CONNECTION_NAME);
            PreparedStatement pst = conn.prepareStatement("Select * from users where username=?");
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if(rs.next())
                return false;
            else {
                PreparedStatement addUserStatement = conn.prepareStatement("INSERT INTO users (username, password) " +
                        "VALUES (?, ?)");
                addUserStatement.setString(1, username);
                addUserStatement.setString(2, hash(password));
                addUserStatement.executeUpdate();
                return true;
            }

        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean authenticateUser(String username, String password){
        try{
            Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
            Connection conn = DriverManager.getConnection(CONNECTION_NAME);
            PreparedStatement pst = conn.prepareStatement("Select * from users where username=? and password=?");
            pst.setString(1, username);
            pst.setString(2, hash(password));
            ResultSet rs = pst.executeQuery();
            if(rs.next())
                return true;
            else
                return false;
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String hash(String password) throws Exception {
        return byteArrayToHexString(computeHash(password));
    }

    public static byte[] computeHash(String x) throws Exception { //credit to http://www.rgagnon.com/javadetails/java-0400.html
        java.security.MessageDigest d = null;
        d = java.security.MessageDigest.getInstance("SHA-1");
        d.reset();
        d.update(x.getBytes());
        return  d.digest();
    }

    public static String byteArrayToHexString(byte[] b){
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++){
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }

    public boolean updateScore(String username, int score){
        try{
            Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
            Connection conn = DriverManager.getConnection(CONNECTION_NAME);
            PreparedStatement pst = conn.prepareStatement("Select * from users where username=?");
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if(!rs.next())
                return false;
            else {
                pst = conn.prepareStatement("Select * from scores where username = ?");
                pst.setString(1, username);
                rs = pst.executeQuery();
                if(rs.next()) {
                    PreparedStatement updateScoreStatement = conn.prepareStatement("UPDATE scores SET score=? WHERE username=?");

                    updateScoreStatement.setInt(1, Math.max(score, rs.getInt("score"))); //update score to max of user's
                    updateScoreStatement.setString(2, username);        //previous score and new score
                    updateScoreStatement.executeUpdate();
                }else{
                    PreparedStatement addScoreStatement = conn.prepareStatement("INSERT INTO scores (username, score) " +
                            "VALUES (?, ?)");
                    addScoreStatement.setString(1, username);
                    addScoreStatement.setInt(2, score);
                    addScoreStatement.executeUpdate();
                }
                return true;
            }

        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}

