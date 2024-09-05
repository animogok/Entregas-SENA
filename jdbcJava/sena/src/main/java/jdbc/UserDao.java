package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class UserDao {

    private static final String SQL_SELECT = "SELECT * FROM users";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM users WHERE idUsers =?";
    private static final String SQL_INSERT = "INSERT INTO users(name, lastname, email, password, idnumber) VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE users SET name=?, lastname=?, email=?, password=?, idnumber=? WHERE idUsers =?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM users WHERE idUsers=?";

    public List<User> list_users() throws SQLException {
        ConectionPathtoBD conn = new ConectionPathtoBD();
        List<User> users = new ArrayList<>();

        try {
            Connection st = conn.get_connection();
            PreparedStatement stmt = st.prepareStatement(SQL_SELECT);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idUsers");
                String current_name = rs.getString("name");
                String current_lastname = rs.getString("lastname");
                String current_email = rs.getString("email");
                String current_idnumber = rs.getString("idnumber");

                users.add(new User(id, current_name, current_lastname, current_email, current_email, current_idnumber));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            conn.disconnect();
        }
        return users;
    }

    public User user(int id) throws SQLException {
        ConectionPathtoBD conn = new ConectionPathtoBD();
        User user = null;
        
        try {
            Connection st = conn.get_connection();
            PreparedStatement stmt = st.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            rs.next();
            String current_name = rs.getString("name");
            String current_lastname = rs.getString("lastname");
            String current_email = rs.getString("email");
            String current_idnumber = rs.getString("idnumber");

            user = new User(id, current_name, current_lastname, current_email, current_email, current_idnumber);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            conn.disconnect();
        }
        return user;
    }

    public int insertUser(User user) throws SQLException {
        ConectionPathtoBD conn = new ConectionPathtoBD();

        int rows = 0;

        try {
            Connection st = conn.get_connection();
            PreparedStatement stmt = st.prepareStatement(SQL_INSERT);

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getIdNumber());

            rows = stmt.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            conn.disconnect();
        }
        return rows;
    }

    public int userUpdate(User user) throws SQLException {
        ConectionPathtoBD conn = new ConectionPathtoBD();
        int rows = 0;

        try {
            Connection st = conn.get_connection();
            PreparedStatement stmt = st.prepareStatement(SQL_UPDATE);

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getIdNumber());
            stmt.setInt(6, user.get_idUser());

            rows = stmt.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            conn.disconnect();
        }
        return rows;
    }

    public int userDelete(int id) throws SQLException {
        ConectionPathtoBD conn = new ConectionPathtoBD();
        int rows = 0;

        try {
            Connection st = conn.get_connection();
            PreparedStatement stmt = st.prepareStatement(SQL_DELETE_BY_ID);

            stmt.setInt(1, id);

            rows = stmt.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            conn.disconnect();
        }
        return rows;
    }

    public static void main(String[] args){
        try{

        UserDao users = new UserDao();
        User user = new User("Sebastian", "Perez", "hoalsa@gmail.com", "1231231", "123123");

        users.insertUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
