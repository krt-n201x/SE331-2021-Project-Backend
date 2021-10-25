package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Users;

import java.util.Optional;

public interface UserDao {
    Integer getUserSize();
    Page<Users> getUsers(Integer pageSize, Integer page);
    Users getUser(Long id);
    Page<Users> getUser(String name, Pageable page);
    Optional<Users> findById(Long id);
}
