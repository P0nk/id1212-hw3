package com.perloven.client.view;

import com.perloven.common.Credentials;
import com.perloven.common.ServerIF;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class UserInterface implements Runnable {

    private boolean running = false;
    private Scanner userinput;
    private ServerIF server;
    private Long myIdAtServer;

    public UserInterface() throws RemoteException {
        userinput = new Scanner(System.in);
    }

    public void start() {
        if (running) {
            return;
        }
        running = true;
        new Thread(this).run();
    }

    public void run() {
        while (running) {
            try {
                String msg = userinput.nextLine();
                switch (msg.toUpperCase()) {
                    case "REGISTER":
                        register();
                        break;
                    case "UNREGISTER":

                    case "LOGIN":
                        login();
                        break;
                    case "HELP":
                        help();
                        break;
                    default:
                        help();
                }
            } catch (Exception e) {
                System.err.println("Could not process user message " + e.getMessage());
            }
        }
    }

    private void lookupServer() throws NotBoundException, MalformedURLException, RemoteException{
        server = (ServerIF) Naming.lookup("//localhost/" + ServerIF.SERVER);
    }

    private void help() {
        System.out.println("Commands: "
                            + "REGISTER\t"
                            + "UNREGISTER\t"
                            + "LOGIN\t"
                            + "LOGOUT\t"
                            + "LIST\t"
                            + "HELP\t");
    }

    private void login() throws RemoteException, MalformedURLException, NotBoundException {
        lookupServer();
        System.out.println("username: ");
        String loginUsername = userinput.nextLine();
        System.out.println("password: ");
        String loginPassword = userinput.nextLine();
        if ((myIdAtServer = server.login(new Credentials(loginUsername, loginPassword))) != null) {
            System.out.println("Logged in!");
        } else {
            System.out.println("Wrong credentials");
        }
    }

    private void register() throws RemoteException, MalformedURLException, NotBoundException{
        lookupServer();
        System.out.println("username: ");
        String registerUsername = userinput.nextLine();
        System.out.println("password: ");
        String registerPassword = userinput.nextLine();
        server.register(new Credentials(registerUsername, registerPassword));
    }

    private void unregister() throws RemoteException, MalformedURLException, NotBoundException{
        lookupServer();
        server.unregister(myIdAtServer);

    }




}
