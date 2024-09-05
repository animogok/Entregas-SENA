package com.SENA.CRUD_MODULE.userClass;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserCRUDImp implements IUserCRUD {

    @Autowired(required = true)
    private IUserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public List<User> userList() {
        return (List<User>) userDao.findAll();   
    }

    @Override
    @Transactional
    public void save(User user){
        userDao.save(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(String username) {
        List<User> users = userList();
        for (int i = 0; i < users.size(); i++){
            if (users.get(i).getName().equals(username)){
                return users.get(i);
            }
        }
        return null;
    }
    
}
