package com.perloven.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerIF extends Remote {

    String SERVER = "SERVER";

    Long login(Credentials credentials) throws RemoteException;

    void register(Credentials credentials) throws RemoteException;

    void unregister(Long userId) throws RemoteException;
}
