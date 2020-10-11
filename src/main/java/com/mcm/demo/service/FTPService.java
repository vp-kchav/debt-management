package com.mcm.demo.service;

import org.apache.commons.net.ftp.FTPClient;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author kchav
 */
public class FTPService {
    
    public void downloadFile(String serverAddress,
                           Integer serverPort,
                           String username,
                           String password,
                           String sourcePath
    ) throws IOException {
        FTPClient client = new FTPClient();
        FileOutputStream fos = null;
        try {
            client.connect(serverAddress,serverPort);
            client.login(username, password);
            client.listFiles(sourcePath);
            //TODO: process all the file.
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            client.disconnect();
        }
    }
}
