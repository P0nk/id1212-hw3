package com.perloven.server.integration;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionManager {

    public static SessionFactory factory;

    private SessionManager() {

    }

    public static synchronized SessionFactory getSessionFactory() {
        if(factory == null) {
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }
        return factory;
    }
}
