package com.perloven.server.integration;

import com.perloven.common.Credentials;
import com.perloven.server.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Random;

public class UserDAO {

    private SessionFactory factory;
    private User user;
    private final Random idGenerator = new Random();

    public UserDAO() {
        factory = SessionManager.getSessionFactory();
    }

    public void register(Credentials credentials) {
        Session session = factory.getCurrentSession();
        Long userId = idGenerator.nextLong();
        try {
            user = new User(credentials.getUsername(), credentials.getPassword(), userId);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User " + user.getUsername() + " registered");
        } catch (Exception e) {
            System.out.println("The username already exists, try another.");
            System.err.println("Caught error in userDAO -> register " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void unregister(Long userId) {
        Session session = factory.getCurrentSession();
        try{
            User user = getUserById(userId);
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
            System.out.println("User " + user.getUsername() + " deleted");
        } catch (Exception e) {
            System.err.println("Exception in UserDAO -> Unregister " + e.getMessage());
        }
    }

    public Long login(Credentials credentials) {
        User user = getUserByName(credentials.getUsername());
        String password = credentials.getPassword();
        if (user != null && user.getPassword().equals(password)) {
            user.loggedIn = true;
            logOut(user.getUserId());
            return user.getUserId();
        }
        return null;
    }

    public void logOut(Long userId) {
        User user = getUserById(userId);
        System.out.println(user);
        user.loggedIn = false;
        System.out.println(user);
    }

    private User getUserByName(String username) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where username='"+username+"'");
        User user = (User) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return user;
    }

    private User getUserById(Long userId) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where userId='"+userId+"'");
        User user = (User) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return user;
    }


}
