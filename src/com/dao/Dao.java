package com.dao;

import com.model.TvUsers;
import com.model.Users;
import java.util.List;

public interface Dao {
    public Users checkUser(String username,String password);
    
    public List<TvUsers> getAllUsers();
    
    public List<TvUsers> searchUsers(String id,String fullname,String mobile,String login,String password,String address);
    
    public boolean addUser(TvUsers users);
    
    public boolean deleteUser(int selectedId);
    
    public boolean editUser(TvUsers users);
}