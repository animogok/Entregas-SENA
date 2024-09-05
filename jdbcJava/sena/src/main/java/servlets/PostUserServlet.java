package servlets;
import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdbc.User;
import jdbc.UserDao;

/**
 *
 * @author USUARIO
 */
@WebServlet(urlPatterns = "/create_post")
public class PostUserServlet extends HttpServlet{
    
    private String id = "";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choice = request.getParameter("choice");
        String id = request.getParameter("id");

        if (choice!= null){
            if (choice.equals("create")){
                try {
                    this.insertUser(request, response);
                } catch (IOException | SQLException e) {
                }
            } else if (choice.equals("update")){   
                try {
                    this.id = id;
                    this.updatetUser(request, response);
                } catch (IOException | SQLException e) {
                }
            }
        }

        if (id != null){
            request.setAttribute("id", id);
        }

        request.setAttribute("choice", choice); 
        request.getRequestDispatcher("create_users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choice = request.getParameter("choice");

        if (choice != null){
            if (choice.equals("create")){
                try {
                    this.insertUser(request, response);
                } catch (IOException | SQLException e) {
                }
            } else if (choice.equals("update")){   
                try {
                    this.updatetUser(request, response);
                } catch (IOException | SQLException e) {
                }
            }
        }
        request.getRequestDispatcher("create_users.jsp").forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        try {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String email = request.getParameter("email");
            String id_number = request.getParameter("id_number");
            String password = request.getParameter("password");

            
            if (name != null & surname != null & email != null & id_number != null & password != null){
                User user = new User(name, surname, email, password, id_number);
                UserDao new_user= new UserDao();
                new_user.insertUser(user);
            }
            
            }catch (SQLException e) {
               
        }
    }

    private void updatetUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        try {

            if (this.id != null){
                UserDao users = new UserDao();
                User user = users.user(Integer.parseInt(this.id));

                int id_user = user.get_idUser();
                String name = user.getName();
                String lastname = user.getSurname();
                String email = user.getEmail();
                String id_number = user.getIdNumber();

                request.setAttribute("id_user", id_user);
                request.setAttribute("name", name);
                request.setAttribute("lastname", lastname);
                request.setAttribute("email", email);
                request.setAttribute("id_numbe", id_number);
                
                String name_page = request.getParameter("name");
                String surname_page = request.getParameter("surname");
                String email_page = request.getParameter("email");
                String id_number_page = request.getParameter("id_number");
                String password_page = request.getParameter("password");

                user.setName(name_page);
                user.setSurname(surname_page);
                user.setEmail(email_page);
                user.setIdNumber(id_number_page);
                user.setPassword(password_page);

                users.userUpdate(user);
                }

            }catch (SQLException e) {
        }
    }
}
