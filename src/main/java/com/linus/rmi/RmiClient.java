package com.linus.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RmiClient {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		RmiServerIntf obj = (RmiServerIntf)Naming.lookup("//localhost/RmiServer");
        System.out.println(obj.getMessage()); 
	}

}
