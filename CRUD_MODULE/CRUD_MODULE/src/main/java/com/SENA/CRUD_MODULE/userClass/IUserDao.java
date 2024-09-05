package com.SENA.CRUD_MODULE.userClass;
import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<User, Long> {   
}
