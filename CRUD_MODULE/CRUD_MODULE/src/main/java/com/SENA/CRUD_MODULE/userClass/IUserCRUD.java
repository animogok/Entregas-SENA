package com.SENA.CRUD_MODULE.userClass;

import java.util.List;

public interface IUserCRUD{
    
    public List<User> userList();
    
    public void save(User user);

    public void delete(User user);

    public User getUser(String username);

}
