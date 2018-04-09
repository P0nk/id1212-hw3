package com.perloven.server.controller;

import com.perloven.common.Credentials;
import com.perloven.common.ServerIF;
import com.perloven.server.integration.UserDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Controller extends UnicastRemoteObject implements ServerIF{


    private UserDAO userDAO;

    public Controller() throws RemoteException{
        userDAO = new UserDAO();
    }



    public void register(Credentials credentials) {
        userDAO.register(credentials);
    }

    public void unregister(Long userId) {
        userDAO.unregister(userId);
    }

    public Long login(Credentials credentials) {
        return userDAO.login(credentials);
    }

    public void logout() {

    }
}
