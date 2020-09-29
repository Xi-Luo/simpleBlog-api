package com.myblog.demo.repository;

import com.myblog.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository1 {
    @Autowired
    UserRepository userRepository;

    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public User getOne(Integer id) {
        User user = userRepository.getOne(id);
        return user;
    }

    public Page<User> findAllByUsernameNot(String username, Integer page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page-1, 10, sort);
        Page<User> users = userRepository.findAllByUsernameNot(username, pageable);
        return users;
    }
}
