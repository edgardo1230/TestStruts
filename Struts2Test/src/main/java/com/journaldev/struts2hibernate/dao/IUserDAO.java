package com.journaldev.struts2hibernate.dao;

import com.journaldev.struts2hibernate.model.User;

public interface IUserDAO {
 
    User getUserByCredentials(String userId, String password);
}