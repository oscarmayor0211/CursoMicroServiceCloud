package com.microservice.daoUserService;

import com.microservice.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount,"Oscar", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount,"Cris", LocalDate.now().minusYears(20)));
        users.add(new User(++usersCount,"Juan", LocalDate.now().minusYears(50)));
        users.add(new User(++usersCount,"Fabian", LocalDate.now().minusYears(30)));

    }

    public List<User> findAll(){
        return users;
    }
    public User save(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }
    public void deleteById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }


    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }
}
