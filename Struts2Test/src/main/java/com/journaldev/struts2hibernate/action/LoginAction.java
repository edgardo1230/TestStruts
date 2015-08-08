package com.journaldev.struts2hibernate.action;

import javax.servlet.ServletContext;
 
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.journaldev.struts2hibernate.dao.IUserDAO;
import com.journaldev.struts2hibernate.dao.impl.UserDaoImpl;
import com.journaldev.struts2hibernate.model.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
 
public class LoginAction implements Action, ModelDriven<User>, ServletContextAware {
     
    public String execute() throws Exception {
         
        SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
        IUserDAO userDAO = new UserDaoImpl(sf);
        User userDB = userDAO.getUserByCredentials(user.getId(), user.getPwd());
        if(userDB == null) return ERROR;
        else {
            user.setEmail(userDB.getEmail());
            user.setName(userDB.getName());
            return SUCCESS;
        }
    }
 
    public User getModel() {
        return user;
    }
     
    private User user = new User();
     
    private ServletContext ctx;
 
    
    public void setServletContext(ServletContext sc) {
        this.ctx = sc;
    }
     
}
