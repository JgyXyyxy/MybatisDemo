package com.sdu.mybatis.inter;

import com.sdu.mybatis.beans.Article;
import com.sdu.mybatis.beans.User;

import java.util.List;

/**
 * Created by J on 2016/12/31.
 */
public interface IUserOperation {

    public User selectUserByID(int id);
    public List<User> selectUsers(String userName);
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(int id);
    public List<Article> getUserArticles(int id);

}
