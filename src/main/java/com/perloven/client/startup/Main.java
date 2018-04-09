package com.perloven.client.startup;

import com.perloven.client.view.UserInterface;
import java.rmi.RemoteException;

public class Main {


    public static void main(String[] args) {
        try {
            new UserInterface().start();
        } catch (RemoteException e) {
            System.err.println("Could not start the client" + e.getMessage());
        }
    }
}
