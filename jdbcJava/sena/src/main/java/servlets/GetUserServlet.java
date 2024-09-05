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

//http://localhost:3000/sena/read_get

/**
 *
 * @author USUARIO
 */
@WebServlet(name = "GetUserServlet", urlPatterns = "/readusers_get")
public class GetUserServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String choice = request.getParameter("choice");
        String id = request.getParameter("id");
        
        String option_delete = request.getParameter("delete");

        if (choice == null || choice.equals("user")){  
            try {
                if (id != null && option_delete == null){
                    UserDao users = new UserDao();
                    User user = users.user(Integer.parseInt(id));

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

                    request.getRequestDispatcher("user_list.jsp").forward(request, response);
                    } else if (id != null && option_delete != null){
                        UserDao users = new UserDao();
                        int user = users.userDelete(Integer.parseInt(id));
                        if (user == 1){
                            request.setAttribute("message", "User deleted");
                            request.getRequestDispatcher("user_list.jsp").forward(request, response);
                        } else {
                            request.setAttribute("message", "User not found");
                            request.getRequestDispatcher("user_list.jsp").forward(request, response);
                        }
                    }  
                    
                } catch (NumberFormatException | SQLException e) {
            }
            } else if (choice.equals("users")){
                try {
                    UserDao users = new UserDao();
                    int total_users = users.list_users().size();

                    request.setAttribute("users", users.list_users());
                    request.setAttribute("total", total_users);

                    request.getRequestDispatcher("user_list.jsp").forward(request, response);
                } catch (SQLException ex) {
            }
        }
        request.setAttribute("choice", choice);
        request.setAttribute("id", id);
        request.getRequestDispatcher("user_list.jsp").forward(request, response);
    }
}
