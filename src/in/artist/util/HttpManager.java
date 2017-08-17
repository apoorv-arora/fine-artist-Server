package in.artist.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import in.artist.util.mail.EmailModel;

public class HttpManager {
	// API calls follow
	/**
	 * REST API HTTP Get call
	 */
	public static Object requestHttpGet(String url, String type, String params, int callType) {

		URL obj;
		ConnectionRetryManager retry = new ConnectionRetryManager(callType);
		while (retry.shouldRetry()) {
			try {
				obj = new URL(url);
				System.setProperty("jsse.enableSNIExtension", "false");
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("User-Agent", "Mozilla/5.0");

				int responseCode = con.getResponseCode();
				System.out.println("\nSending 'GET' request to URL : " + url);
				System.out.println("Response Code : " + responseCode);

				boolean redirect = false;
				if (responseCode != HttpURLConnection.HTTP_OK) {
					if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP
							|| responseCode == HttpURLConnection.HTTP_MOVED_PERM
							|| responseCode == HttpURLConnection.HTTP_SEE_OTHER)
						redirect = true;
				}
				if (redirect) {

					// get redirect url from "location" header field
					String newUrl = con.getHeaderField("Location");

					// get the cookie if need, for login
					String cookies = con.getHeaderField("Set-Cookie");

					// open the new connnection again
					con = (HttpURLConnection) new URL(newUrl).openConnection();
					con.setRequestProperty("Cookie", cookies);
					con.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
					con.addRequestProperty("User-Agent", "Mozilla");
					con.addRequestProperty("Referer", "google.com");

					System.out.println("Redirect to URL : " + newUrl);
					responseCode = con.getResponseCode();
					System.out.println("Response Code : " + responseCode);
				}

				if (responseCode != 200) {
					if (CommonLib.LIVE_BUILD) {
						String content = params;
						EmailModel eModel = new EmailModel();
						eModel.setFrom(CommonLib.ZAPP_ID);
						ArrayList<String> senders = new ArrayList<String>();
						senders.add("hello@zapplon.com");
						senders.add("pratik@zapplon.com");
						eModel.setSenders(senders);
						eModel.setSubject("Server returned HTTP response code: " + responseCode);
						eModel.setContent("Sent parameters are \n" + "url:" + url + "\n" + "\n" + content);
//						EmailUtil.getInstance().sendHtmlEmail(eModel);
					}
				}

				return parse(con.getInputStream(), type);

			} catch (MalformedURLException e) {
				retry.errorOccured();
			} catch (IOException e) {
				retry.errorOccured();
			} catch (Exception e) {
				retry.errorOccured();
			}
		}
		return false;
	}

	/**
	 * REST API HTTPs Post/Delete call
	 */
	public static Object requestHttpsPost(String url, String type, List<NameValuePair> params, String token,
			int callType) {

		URL obj;
		ConnectionRetryManager retry = new ConnectionRetryManager(callType);
		while (retry.shouldRetry()) {
			try {
				obj = new URL(url);
				// System.setProperty("jsse.enableSNIExtension", "false");
				HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
				con.setRequestMethod("POST");

				String apiKey = "";
				String passKey = "";
				String auth = "";

				con.setDoInput(true);
				con.setDoOutput(true);

				OutputStream os = con.getOutputStream();
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
				writer.write(HttpManager.getQuery(params));
				writer.flush();
				writer.close();
				os.close();

				int responseCode = con.getResponseCode();
				System.out.println("\nSending 'GET' request to URL : " + url);
				System.out.println("Response Code : " + responseCode);

				boolean redirect = false;
				if (responseCode != HttpURLConnection.HTTP_OK) {
					if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP
							|| responseCode == HttpURLConnection.HTTP_MOVED_PERM
							|| responseCode == HttpURLConnection.HTTP_SEE_OTHER)
						redirect = true;
				}
				if (redirect) {

					// get redirect url from "location" header field
					String newUrl = con.getHeaderField("Location");

					// get the cookie if need, for login
					String cookies = con.getHeaderField("Set-Cookie");

					// open the new connnection again
					con = (HttpsURLConnection) new URL(newUrl).openConnection();
					con.setRequestProperty("Cookie", cookies);
					con.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
					con.addRequestProperty("User-Agent", "Mozilla");
					con.addRequestProperty("Referer", "google.com");

					con.setRequestMethod("POST");

					con.setDoInput(true);
					con.setDoOutput(true);

					os = con.getOutputStream();
					writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
					writer.write(HttpManager.getQuery(params));
					writer.flush();
					writer.close();
					os.close();

					responseCode = con.getResponseCode();
					System.out.println("Response Code : " + responseCode);
				}

				if (responseCode != 200) {

					if (CommonLib.LIVE_BUILD) {
						String content = "";

						for (NameValuePair pair : params) {
							content = content + " " + pair;
						}

						EmailModel eModel = new EmailModel();
						eModel.setFrom(CommonLib.ZAPP_ID);
						ArrayList<String> senders = new ArrayList<String>();
						senders.add("hello@zapplon.com");
						senders.add("pratik@zapplon.com");
						eModel.setSenders(senders);
						eModel.setSubject("Server returned HTTP response code: " + responseCode);
						eModel.setContent(
								"Sent parameters are \n" + "url:" + url + "\n" + "token:" + token + "\n" + content);
//						EmailUtil.getInstance().sendHtmlEmail(eModel);
					}

				}

				return parse(con.getInputStream(), type);
			} catch (MalformedURLException e) {
				retry.errorOccured();
			} catch (IOException e) {
				retry.errorOccured();
			} catch (Exception e) {
				retry.errorOccured();
			}

		}

		return false;
	}

	/**
	 * REST API HTTP Post call
	 */
	public static Object requestHttpPost(String url, String type, List<NameValuePair> nameValuePairs, int callType) {

		ConnectionRetryManager retry = new ConnectionRetryManager(callType);
		while (retry.shouldRetry()) {
			try {
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(url);

				// add header
				post.setHeader("User-Agent", "Mozilla/5.0");

				if (nameValuePairs != null)
					post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

				HttpResponse response = client.execute(post);
				System.out.println("\nSending 'POST' request to URL : " + url);
				System.out.println("Post parameters : " + post.getEntity());
				System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

				int responseCode = response.getStatusLine().getStatusCode();
				boolean redirect = false;
				if (responseCode != HttpURLConnection.HTTP_OK) {
					if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP
							|| responseCode == HttpURLConnection.HTTP_MOVED_PERM
							|| responseCode == HttpURLConnection.HTTP_SEE_OTHER)
						redirect = true;
				}
				if (redirect) {
					client = new DefaultHttpClient();
					Header[] httpHeader = response.getHeaders("Location");
					url = httpHeader[0].getValue();

					post = new HttpPost(url);

					// add header
					post.setHeader("User-Agent", "Mozilla/5.0");

					if (nameValuePairs != null)
						post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

					response = client.execute(post);
					responseCode = response.getStatusLine().getStatusCode();
					System.out.println(responseCode);
				}

				if (responseCode != 200) {

					if (CommonLib.LIVE_BUILD) {
						String content = "";

						for (NameValuePair pair : nameValuePairs) {
							content = content + " " + pair;
						}

						EmailModel eModel = new EmailModel();
						eModel.setFrom(CommonLib.ZAPP_ID);
						ArrayList<String> senders = new ArrayList<String>();
						senders.add("hello@zapplon.com");
						senders.add("pratik@zapplon.com");
						eModel.setSenders(senders);
						eModel.setSubject("Server returned HTTP response code: " + responseCode);
						eModel.setContent("Sent parameters are \n" + "url:" + url + "\n" + content);
//						EmailUtil.getInstance().sendHtmlEmail(eModel);
					}
				}
				return parse(response.getEntity().getContent(), type);
			} catch (MalformedURLException e) {
				retry.errorOccured();
			} catch (IOException e) {
				retry.errorOccured();
			} catch (Exception e) {
				retry.errorOccured();
			}

		}

		return false;
	}

	/**
	 * SOAP APIs - Make an HTTP SOAP request
	 */
	public static Object requestHTTPSoap(String SOAPUrl, String type, String xmlFile2Send, String SOAPAction,
			int callType) {

		ConnectionRetryManager retry = new ConnectionRetryManager(callType);
		while (retry.shouldRetry()) {
			try {

				// Create the connection where we're going to send the file.
				URL url = new URL(SOAPUrl);
				URLConnection connection = url.openConnection();
				HttpURLConnection httpConn = (HttpURLConnection) connection;

				// Open the input file. After we copy it to a byte array, we can
				// see
				// how big it is so that we can set the HTTP Cotent-Length
				// property. (See complete e-mail below for more on this.)
				byte[] b = xmlFile2Send.getBytes();

				// Set the appropriate HTTP parameters.
				httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
				httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
				httpConn.setRequestProperty("SOAPAction", SOAPAction);
				httpConn.setRequestMethod("POST");
				httpConn.setDoOutput(true);
				httpConn.setDoInput(true);

				// Everything's set up; send the XML that was read in to b.
				OutputStream out = httpConn.getOutputStream();
				out.write(b);
				out.close();

				// Read the response and write it to standard out.
				int responseCode = httpConn.getResponseCode();

				System.out.println("responseCode: " + responseCode + " to url: " + url);
				if (responseCode != 200) {

					if (CommonLib.LIVE_BUILD) {
						String content = xmlFile2Send;

						EmailModel eModel = new EmailModel();
						eModel.setFrom(CommonLib.ZAPP_ID);
						ArrayList<String> senders = new ArrayList<String>();
						senders.add("hello@zapplon.com");
						senders.add("pratik@zapplon.com");
						eModel.setSenders(senders);
						eModel.setSubject("Server returned HTTP response code: " + responseCode);
						eModel.setContent("Sent parameters are \n" + "url:" + url + "\n" + content);
//						EmailUtil.getInstance().sendHtmlEmail(eModel);

					}

				}
				return parse(httpConn.getInputStream(), type);
			} catch (MalformedURLException e) {
				retry.errorOccured();
			} catch (IOException e) {
				retry.errorOccured();
			} catch (Exception e) {
				retry.errorOccured();
			}

		}

		return false;
	}

	/**
	 * Convert name-value pairs to query params. Url must be ending with ?
	 * before appending the output of this function
	 */
	private static String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();
		boolean first = true;

		for (NameValuePair pair : params) {
			if (first)
				first = false;
			else
				result.append("&");

			result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
		}

		return result.toString();
	}

	/**
	 * Parsing handler
	 */
	public static final Object parse(InputStream is, String type) {
		Object returnObject = null;

		if (is == null)
			return returnObject;

		return returnObject;
	}
}
