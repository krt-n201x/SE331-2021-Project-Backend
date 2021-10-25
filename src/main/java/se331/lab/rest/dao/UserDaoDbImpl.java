package se331.lab.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.Users;
import se331.lab.rest.repository.UserRepository;

import java.util.Optional;

@Repository
@Profile("db")
public class UserDaoDbImpl implements UserDao {
    @Autowired
    UserRepository userRepository;

    @Override
    public Integer getUserSize() {
        return Math.toIntExact(userRepository.count());
    }

    @Override
    public Page<Users> getUsers(Integer pageSize, Integer page) {
        return userRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    @Override
    public Users getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Users> getUser(String title, Pageable page) {
        return userRepository.findByNameIgnoreCaseContaining(title,page);
    }

    @Override
    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }
}
