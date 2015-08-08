package com.journaldev.struts2hibernate.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.journaldev.struts2hibernate.dao.IUserDAO;
import com.journaldev.struts2hibernate.model.User;

public class UserDaoImpl implements IUserDAO {
    
   private SessionFactory sf;
    
   public UserDaoImpl(SessionFactory sf){
       this.sf = sf;
   }

   public User getUserByCredentials(String userId, String password) {
       Session session = sf.openSession();
       Transaction tx = session.beginTransaction();
       Query query = session.createQuery("from User where id=:id and pwd=:pwd");
       query.setString("id", userId); query.setString("pwd", password);
       User user = (User) query.uniqueResult();
       if(user != null){
           System.out.println("User Retrieved from DB::"+user);
       }
       tx.commit();session.close();
       return user;
   }

}
