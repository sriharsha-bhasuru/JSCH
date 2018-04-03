package test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class JSchPut {

	public static OutputStreamWriter createCategoryFile() throws IOException, Exception {
		File file = null;
		PrintWriter pw = null;
		OutputStreamWriter osw = null;
		try {

			file = new File("");

			osw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			String[] data = new String[3];
			data[0] = "teteasd";
			data[1] = "123";
			data[2] = "#FFADASA";
			osw.write(String.join(",", data));

		} finally {
			if (pw != null) {
				pw.close();
			}
		}

		return osw;
	}

	public static void main(String[] args) {
		try {
			String workingDirectory = "/root/APP_LOCAL_DATA/AI_Share_Root/tectest/ca1.csv";
			String csvContent = "bagsf,hghhg\nOT,dfgh";
			String host = "";
			String user = "";
			String password = "";
			JSch jsch = new JSch();
			Session session = jsch.getSession(user, host, 22);
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setPassword(password);
			session.connect();
			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp channelSftp = (ChannelSftp) channel;
			InputStream stream = new ByteArrayInputStream (csvContent.getBytes ());
			channelSftp.put (stream,workingDirectory);
			channel.disconnect();
			session.disconnect();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}