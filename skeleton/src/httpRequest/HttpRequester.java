package httpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/* Esta clase se encarga de realizar efectivamente el pedido de feed al servidor de noticias
 * Leer sobre como hacer una http request en java
 * https://www.baeldung.com/java-http-request
 * */

public class HttpRequester {
	
	public String getFeedRss(String urlFeed){
		String feedRssXml = null;
		URI uri = URI.create(urlFeed);

		try{
			URL url = uri.toURL();
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			int status = connection.getResponseCode();
			if(status != 200){
				System.err.println("ERROR: Connection Error");
				System.exit(1);
			}

			BufferedReader in = new BufferedReader(
				new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while((inputLine = in.readLine()) != null){
				content.append(inputLine);
			}
			in.close();
			connection.disconnect();
			feedRssXml = content.toString();
		}catch(IOException e){
			System.err.println("ERROR: IOException");
			System.exit(1);
		}
		return feedRssXml;
	}

	public String getFeedReedit(String urlFeed) {
		String feedReeditJson = null;
		return feedReeditJson;
	}

}
