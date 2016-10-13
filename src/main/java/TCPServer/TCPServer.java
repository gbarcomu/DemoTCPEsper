package TCPServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import esper.OclusionEventHandler;
import model.BDT;

public class TCPServer implements Runnable{
	
	private int port;
	private boolean running;
	private ServerSocket serverSocket;
	private OclusionEventHandler oclusionEventHandler;
	
	public TCPServer (int _port) {
		
		port = _port;
		running = true;
	}
	
	private void startup(int port) {
		
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	@Override
	public void run() {
		
		System.out.println("Starting new TCP server on port " + port);
		
		startup(port);
		
		String data = null;
		
		while (running) {
			Socket connectionSocket = null;
			try {
				connectionSocket = serverSocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedReader inFromClient = null;
			try {
				inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				data = inFromClient.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Received: " + data);
			BDT bdt = new BDT(data);
			oclusionEventHandler.handle(bdt);
		}
		
	}
	
	public void shutdown() {
		
		running = false;
	}
}