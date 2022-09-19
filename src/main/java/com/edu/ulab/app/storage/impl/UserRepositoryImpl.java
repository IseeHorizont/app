package com.edu.ulab.app.storage.impl;

import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.exception.UserRepositoryException;
import com.edu.ulab.app.storage.Repository;
import com.edu.ulab.app.storage.Storage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepositoryImpl implements Repository<User> {

    private final Storage storage;

    public UserRepositoryImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public List<User> getAll() {
        return storage.findAllUser();
    }

    @Override
    public User getById(Long id) {
        if (id == null) {
            throw new UserRepositoryException("Incorrect user's id");
        }
        User foundUserById = storage.findUserById(id);
        if (foundUserById == null) {
            throw new NotFoundException("Such user not found");
        }
        return foundUserById;
    }

    @Override
    public User create(User user) {
        User createdUser = storage.createUser(user);
        return createdUser;
    }

    @Override
    public User update(User user) {
        if (user == null) {
            throw new NotFoundException("Can't update empty user");
        }
        User updatedUser = storage.updateUser(user);
        return updatedUser;
    }

    @Override
    public User deleteById(Long id) {
        if (id == null) {
            throw new UserRepositoryException("Incorrect user's id");
        }
        User deletedUser = storage.deleteUserById(id);
        return deletedUser;
    }
}
