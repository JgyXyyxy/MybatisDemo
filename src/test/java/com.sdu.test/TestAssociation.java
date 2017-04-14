package com.sdu.test;

import com.sdu.mybatis.beans.Article;
import com.sdu.mybatis.inter.IUserOperation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.util.List;
import java.util.Scanner;

/**
 * Created by J on 2017/1/2.
 */
public class TestAssociation {

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

    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation=session.getMapper(IUserOperation.class);

            Scanner si = new Scanner(System.in);
            System.out.print("输入要查询作者的文章ID: ");
            int userid = si.nextInt();
            List<Article> articles = userOperation.getUserArticles(userid);
            for(Article article:articles){
                System.out.println("Title：" + article.getTitle()+"  Content:"+article.getContent()+
                        "  Author:"+article.getUser().getUserName()+"  Address:"+
                        article.getUser().getUserAddress());
            }
        } finally {
            session.close();
        }
    }
}
