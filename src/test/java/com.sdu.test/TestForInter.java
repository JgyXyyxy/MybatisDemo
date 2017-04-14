package com.sdu.test;

import com.sdu.mybatis.beans.User;
import com.sdu.mybatis.inter.IUserOperation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.util.List;
import java.util.Scanner;

/**
 * Created by J on 2016/12/31.
 */
public class TestForInter {

    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static{
        try{
            reader  = Resources.getResourceAsReader("Configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }


    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation=session.getMapper(IUserOperation.class);

            Scanner si = new Scanner(System.in);
            System.out.print("输入要查询的ID: ");
            int i = si.nextInt();
            User user = userOperation.selectUserByID(i);
            System.out.println("Address: "+ user.getUserAddress());
            System.out.println("Name:" + user.getUserName());

            //Scanner ss = new Scanner(System.in);
            System.out.print("输入要查询的用户名模式: ");
            String userName = si.next();
            System.out.println(userName);
            List<User> users = userOperation.selectUsers(userName);
            for(User user1:users){
                System.out.println("ID:" + user1.getId()+
                                    "   Name:"+user1.getUserName()+
                                    "   Address:"+user1.getUserAddress());
            }


        } finally {
            session.close();
        }
    }
}
