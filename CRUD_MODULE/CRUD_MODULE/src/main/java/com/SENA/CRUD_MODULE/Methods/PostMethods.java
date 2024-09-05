package com.SENA.CRUD_MODULE.Methods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SENA.CRUD_MODULE.userClass.User;
import com.SENA.CRUD_MODULE.userClass.UserCRUDImp;

@Controller
public class PostMethods {

    @Autowired
    private UserCRUDImp userCrud;

    //GET SPECIFIC USER METHOD - PROCESSING OF THE FORM
    @PostMapping("/user/")
    public String createOrUpdateUser(@RequestParam("username") String username,
                                     @RequestParam("password") String password1,
                                     @RequestParam("passwordConfirmation") String password2,
                                     Model model) {
        // Verificar si las contraseñas coinciden
        if (password1.length() < 8){
            model.addAttribute("error", "Password must be avobe 8 characters");
            return "userForm"; 
        }
        
        if (!password1.equals(password2)) {
            model.addAttribute("error", "passsword mismatch");
            return "userForm";  // Volver a la vista del formulario si las contraseñas no coinciden
        }

        // Crear o actualizar el usuario en la base de datos
        User user = userCrud.getUser(username);

        // Redirigir a la vista de usuario encontrado
        return "redirect:/user-finded?username=" + user.getName();
    }

    //USER REGISTRATION METHODS
    @PostMapping("/user-registration/")
    public String registerUser(@RequestParam("name") String name,
                                @RequestParam("surname") String surname,
                                @RequestParam("number") String number,
                                @RequestParam("email") String email,
                                @RequestParam("password") String password1,
                                @RequestParam("password confirmation") String password2,
                                Model model)
    {
        
        try {
            if (password1.length() < 8){
                model.addAttribute("error", "Password must be avobe 8 characters");
                return "userForm"; 
            }

            if (!password1.equals(password2)) {
                model.addAttribute("error", "passsword mismatch");
                return "userPOST";  // Volver a la vista del formulario si las contraseñas no coinciden
            }

            int number_int = Integer.parseInt(number);
            User new_user = new User(name, surname, number_int ,email, password1);

            userCrud.save(new_user);

            return "redirect:/Home";

        } catch (Exception e) {
            model.addAttribute("error", "The number must contain only numbers in it");
            return "userPOST";
        }

    }

    @PostMapping("/user-delete/")
    public String deleteUser(@RequestParam("username") String username, @RequestParam("delete") int number, Model model) {
        
        if (number < 0){
            model.addAttribute("error", "The number must contain only numbers greatter or equal than 0");
            return "userDelete";
        }

        if (userCrud.getUser(username).getId() == number){
            userCrud.delete(userCrud.getUser(username));
            return "redirect:/Home";    
        }

        return "userDelete";
    }
}
