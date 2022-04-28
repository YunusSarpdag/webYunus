package com.yunus.web.webyunus;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Repository
public class UserService {
    public static List<User> userList = new ArrayList<>();

    static {
        userList.add(new User(1 , "Ali" , new Date() , "a"));
        userList.add(new User(2 , "Veli" , new Date(), "a"));
        userList.add(new User(3, "Ata" , new Date(), "a"));
    }

    public User getUserById(int id){
        for(User user:userList){
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }


    public void addUser(User user){
        userList.add(user);
    }

    public List<User> getUserList(){
        return userList;
    }

    public User deleteUserById(int i){
        List<User> tmpList = userList.stream().filter(f -> f.getId() == i).collect(Collectors.toList());
        userList.removeAll(tmpList);
        return tmpList.get(0);
    }
}
