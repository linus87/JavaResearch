package com.linus.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUtil {
	
	public static boolean uploadFile(String remoteName, File f){
		FTPClient client = new FTPClient();
		FileInputStream input = null;
		try {
			client.connect("10.9.216.182", 21);
			boolean login = client.login("yageng", "*****");
			if(login){
				int replay = client.getReplyCode();
		        if (FTPReply.isPositiveCompletion(replay)) {
		          client.setFileType(FTP.BINARY_FILE_TYPE);
		          input = new FileInputStream(f);
		          if (!client.storeFile(remoteName, input)) {
		        	  return false;
		          }          
		        }
		        return true;				
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != input){
					input.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static File downloadFile(String fileName){
		FTPClient client = new FTPClient();
		File tempFile = new File("c:\\Home\\output" + fileName);
		try {
			client.connect("***", 21);
			boolean login = client.login("yageng", "***");
			if(login){
				int replay = client.getReplyCode();

		        if (FTPReply.isPositiveCompletion(replay)) {
		        	client.setFileType(FTP.BINARY_FILE_TYPE);
		        	OutputStream output = new FileOutputStream(tempFile);
		        	client.retrieveFile(fileName, output);
		        	output.close();
		        }
		        
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
