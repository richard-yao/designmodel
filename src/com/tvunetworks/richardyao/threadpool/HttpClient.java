package com.tvunetworks.richardyao.threadpool;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Dec 13, 2016 2:08:46 PM
 */
public class HttpClient {

	public static String send(String uriAddress) {
		HttpURLConnection con = null;
		OutputStream os = null;
		ObjectInputStream ois = null;
		try {
			URL url = new URL(uriAddress);
			con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setUseCaches(false);
			con.setReadTimeout(3000);
			con.connect();
			int code = con.getResponseCode();
			if (code == 200) {
				String resultData = "";
				BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine = null;
				while (((inputLine = reader.readLine()) != null)) {
					resultData += inputLine;
				}
				reader.close();
				return resultData;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (ois != null) {
				try {
					ois.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
