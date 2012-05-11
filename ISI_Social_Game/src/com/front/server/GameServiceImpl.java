package com.front.server;

import com.front.client.GameService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GameServiceImpl extends RemoteServiceServlet implements GameService{

	public boolean createGame() {
		 int lport=5656;
	        String rhost="secure.journaldev.com";
	        String host="secure.journaldev.com";
	        int rport=3306;
	        String user="commex";
	        String password="cOMm3x";
	        String dbuserName = "commex";
	        String dbpassword = "cOMm3x";
	        String url = "jdbc:mysql://localhost:"+lport+"/mydb";
	        String driverName="com.mysql.jdbc.Driver";
	        Connection conn = null;
	        Session session= null;
	        try{
	            //Set StrictHostKeyChecking property to no to avoid UnknownHostKey issue
	            java.util.Properties config = new java.util.Properties();
	            config.put("StrictHostKeyChecking", "no");
	            JSch jsch = new JSch();
	            session=jsch.getSession(user, host, 22);
	            session.setPassword(password);
	            session.setConfig(config);
	            session.connect();
	            System.out.println("Connected");
	            int assinged_port=session.setPortForwardingL(lport, rhost, rport);
	            System.out.println("localhost:"+assinged_port+" -> "+rhost+":"+rport);
	            System.out.println("Port Forwarded");
	 
	            //mysql database connectivity
	            Class.forName(driverName).newInstance();
	            conn = DriverManager.getConnection (url, dbuserName, dbpassword);
	            System.out.println ("Database connection established");
	            System.out.println("DONE");
	        }catch(Exception e){
	            e.printStackTrace();
	        }finally{
	            if(conn != null ){
	                System.out.println("Closing Database Connection");
	               
	            }
	            if(session !=null && session.isConnected()){
	                System.out.println("Closing SSH Connection");
	                session.disconnect();
	            }
	        }
	        return true;
		
	}
	
	

}
