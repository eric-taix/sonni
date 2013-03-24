package org.jug.montpellier.sonni.jugapis.impl.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class aims to provide easy way to request a server
 * 
 * @author eric
 * 
 */
public class HttpRequester {

	private String hostURL;

	/**
	 * Create a instance. All request will be send to the hostURL
	 * 
	 * @param hostURL
	 */
	public HttpRequester(String hostURL) {
		this.hostURL = hostURL;
	}

	/**
	 * Send an HTTP Get request
	 * 
	 * @param query
	 * @param urlParameters
	 * @return
	 * @throws IOException
	 */
	public String get(String query, String urlParameters) throws IOException {
		return send("GET", query, urlParameters);
	}

	/**
	 * Send an HTTP Post request
	 * 
	 * @param query
	 * @param urlParameters
	 * @return
	 * @throws IOException
	 */
	public String post(String query, String urlParameters) throws IOException {
		return send("POST", query, urlParameters);
	}

	private String send(String method, String query, String urlParameters) throws IOException {
		URL url;
		HttpURLConnection connection = null;
		try {
			// Create connection
			url = new URL(hostURL + query);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// Send request
			if (urlParameters != null && !urlParameters.isEmpty()) {
				DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
				wr.writeBytes(urlParameters);
				wr.flush();
				wr.close();
			}

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();
		} finally {

			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}
