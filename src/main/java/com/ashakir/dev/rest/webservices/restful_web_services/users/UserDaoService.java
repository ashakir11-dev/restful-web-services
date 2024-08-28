package com.ashakir.dev.rest.webservices.restful_web_services.users;

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
        users.add(new User(++usersCount,new ArrayList<>(),"Abdullah", LocalDate.now().minusYears(26)));
        users.add(new User(++usersCount,new ArrayList<>(),"Messi", LocalDate.now().minusYears(36)));
        users.add(new User(++usersCount,new ArrayList<>(),"CR7", LocalDate.now().minusYears(40)));
    }

    public List<User> findAll(){
        return users;
    }

    public User findById(int id){
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);

    }

    public User findByName(String name){
        return users.stream().filter(user -> user.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public boolean save(User user) {
        Predicate<? super User> predicate = listUser -> listUser.getName().equalsIgnoreCase(user.getName());
        if(users.stream().noneMatch(predicate)){
            user.setId(++usersCount);
            users.add(user);
            return true;
        }

        return false;

    }

    public void deleteById(int id) {
        Predicate<? super User> predicate = user -> user.getId() == id;
        users.removeIf(predicate);
    }
}
