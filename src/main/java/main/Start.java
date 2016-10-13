package main;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import TCPServer.TCPServer;

public class Start {

	public static void main(String[] args) {
		
		TCPServer tcpServer = new TCPServer(1111);
		
		new Thread(tcpServer).start();
		
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(new String[] { "application-context.xml" });
        BeanFactory factory = (BeanFactory) appContext;
	}
}
