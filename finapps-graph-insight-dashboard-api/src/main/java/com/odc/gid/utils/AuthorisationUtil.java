package com.odc.gid.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.annotation.PostConstruct;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;



//import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class AuthorisationUtil {
	private static String URL;
	
	@Value("${bk.host.privateKey}")
	private static String PRIVATE_KEY;
	
	@Value("${bk.host.publicKey}")
	private static String PUBLIC_KEY;
	
	private static final String METHOD_TYPE = "POST";
	private static Logger logger = Logger.getLogger(AuthorisationUtil.class);
	@Value("${bk.host}")
	private String bkHost;

	@PostConstruct
	public void initAuthorisationUtil()
	{
		logger.debug("AuthorisationUtil initializing..");
		URL = "http://" + bkHost + "/Services/AuthorizationService?bksso=";
		logger.debug("AuthorisationUtil initialized");
		logger.debug("URL:"+URL);
	}
	
	public static Integer getParternIdForGivenBkSso(String bkSso) throws IOException {
		String signedUrl = getSignedUrl(URL+bkSso, PRIVATE_KEY, PUBLIC_KEY, METHOD_TYPE, null);
		Integer partnerID = getPartnerIdFromSignedUrl(signedUrl);
		return partnerID;
	}
	
	public static String getParternInfoForGivenBkSso(String bkSso) throws IOException {
		String signedUrl = getSignedUrl(URL+bkSso, PRIVATE_KEY, PUBLIC_KEY, METHOD_TYPE, null);
		String partnerInfo = getPartnerInfoFromSSO(signedUrl);
		//logger.debug(partnerInfo);
		return partnerInfo;
	}

	private static Integer getPartnerIdFromSignedUrl(String signedUrl) throws IOException {

		URL url = new URL(signedUrl);

		HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
		httpUrlConnection.setRequestMethod("POST");
		httpUrlConnection.setRequestProperty("Content-Type", "application/json");
		httpUrlConnection.setUseCaches(false);

		httpUrlConnection.connect();
		
		InputStream responseStream = httpUrlConnection.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseStream));
		String line;
		StringBuffer response = new StringBuffer(); 
		while((line = bufferedReader.readLine()) != null) {
			response.append(line);
		}
		bufferedReader.close();

		logger.debug(response);

		JsonElement jsonElement = new JsonParser().parse(response.toString());
		JsonObject  jsonObject = jsonElement.getAsJsonObject();
		return jsonObject.get("partnerID").getAsInt();
	}
	
	private static String getPartnerInfoFromSSO(String signedUrl) throws IOException {

		URL url = new URL(signedUrl);

		HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
		httpUrlConnection.setRequestMethod("POST");
		httpUrlConnection.setRequestProperty("Content-Type", "application/json");
		httpUrlConnection.setUseCaches(false);

		httpUrlConnection.connect();
		
		InputStream responseStream = httpUrlConnection.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseStream));
		String line;
		StringBuffer response = new StringBuffer(); 
		while((line = bufferedReader.readLine()) != null) {
			response.append(line);
		}
		bufferedReader.close();
		return response.toString();
	}

	private static String getSignedUrl(String Url,String privateKey,String publicKey, String requestMethodType, String postData) {
		final String algorithmString = "HmacSHA256";
		try {
			Url = Url.trim(); // For example, http://oracle.com/dmp?user=test&passwd=test
			StringBuffer stringToSign = new StringBuffer(requestMethodType);//GET or POST
			URL url = new URL(Url);

			logger.debug("URL path for signing: " + url.getPath());//http://oracle.com/dmp
			stringToSign.append(url.getPath());
			String query = url.getQuery();
			logger.debug("Query params for Signing: " + query);//user=test&passwd=test

			if (query != null && query.length() > 0) {
				String[] params = query.split("&");
				for (int ii = 0; ii < params.length; ii++) {
					String[] args = params[ii].split("=");
					stringToSign.append(args[1]);
				}
			}
			if (postData != null && "POST".equals(requestMethodType)) {
				stringToSign.append(postData);//Include post data if the  method type is POST
			}

			String signStr = stringToSign.toString();

			logger.debug("Signing string " + signStr);

			byte[] signature = null;

			// Signing algorithm and technique 
			Mac mac = Mac.getInstance(algorithmString);
			mac.init(new SecretKeySpec(privateKey.getBytes(), algorithmString));
			signature = Base64.encodeBase64(mac.doFinal(signStr.getBytes()));
			String sigString = new String(signature);
			StringBuilder newUrlString = new StringBuilder();
			newUrlString.append(url.getProtocol());
			newUrlString.append("://");
			newUrlString.append(url.getHost());
			if (url.getPort() != -1) {
				newUrlString.append(":" + url.getPort());
			}
			newUrlString.append(url.getFile());
			//append bkuid and bksig as additional params to passed URL
			newUrlString.append("&bkuid=" + publicKey);
			newUrlString.append("&bksig=" + URLEncoder.encode(sigString, "UTF-8"));

			logger.debug("Signed newUrlString = : " + newUrlString.toString());
			return newUrlString.toString();
		} catch (Exception e) {
			logger.error("Error while signing URL " + e);
			return null;
		}

	}
	
//	public static void main(String[] args) throws IOException {
//		AuthorisationUtil util = new AuthorisationUtil();
//		logger.debug(util.getParternIdForGivenBkSso("5XFH6hhmBvyWcgZvwmbGcE5R0Ns%3D"));
//	}
}
