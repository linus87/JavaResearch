package com.linus.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServer extends UnicastRemoteObject implements RmiServerIntf {
	public static final String message = "Hello World";

	private static final long serialVersionUID = -6548225083993107563L;

	protected RmiServer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) throws Exception {
		System.out.println("RMI server started");

		try { // special exception handler for registry creation
			LocateRegistry.createRegistry(1099);
			System.out.println("java RMI registry created.");
		} catch (RemoteException e) {
			// do nothing, error means registry already exists
			System.out.println("java RMI registry already exists.");
		}

		// Instantiate RmiServer

		RmiServer obj = new RmiServer();

		// Bind this object instance to the name "RmiServer"
		Naming.rebind("//localhost/RmiServer", obj);
		System.out.println("PeerServer bound in registry");
	}

	@Override
	public String getMessage() throws RemoteException {
		return message;
	}

}
