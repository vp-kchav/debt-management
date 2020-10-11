package com.mcm.demo.service;

import com.jcraft.jsch.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author kchav
 */
public class SFTPService {

    public void uploadFile(String serverAddress,
                           Integer serverPort,
                           String username,
                           String password,
                           MultipartFile file,
                           String destinationPath
    ) throws JSchException, SftpException, IOException {

        Session session = null;
        ChannelSftp channelSftp = null;
        try {


            JSch jsch = new JSch();
            session = jsch.getSession(username, serverAddress, serverPort);
            session.setPassword(password);

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");

            session.setConfig(config);
            session.connect();
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            channelSftp.cd(destinationPath);
            channelSftp.put(file.getInputStream(), file.getOriginalFilename(), ChannelSftp.OVERWRITE);

            channelSftp.disconnect();
            session.disconnect();
        } finally {
            if (channelSftp != null && channelSftp.isConnected()) {
                channelSftp.disconnect();
            }
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
    }
}
