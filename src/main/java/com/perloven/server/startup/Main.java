package com.perloven.server.startup;

import com.perloven.server.controller.Controller;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {


    public static void main(String[] args) {
        try {
            new Main().startRegistry();
            Naming.rebind(Controller.SERVER, new Controller());
            System.out.println("Server is running");
        } catch (MalformedURLException | RemoteException e) {
            System.err.println("Could not start server.");
        }

    }

    private void startRegistry() throws RemoteException{
        try {
            LocateRegistry.getRegistry().list();
        } catch (RemoteException e) {
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        }
    }
}
