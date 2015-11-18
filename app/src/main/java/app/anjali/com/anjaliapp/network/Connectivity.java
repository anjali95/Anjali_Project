package app.anjali.com.anjaliapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import app.anjali.com.anjaliapp.util.ConvertToString;

/**
 * Created by GB on 11/14/2015.
 */
public class Connectivity {


    public static String makeServiceCall(String surl) {

        InputStream in = null;
        int resCode = -1;

        try {
            URL url = new URL(surl);
            URLConnection urlConn = url.openConnection();

            if (!(urlConn instanceof HttpURLConnection)) {
                throw new IOException("URL is not an Http URL");
            }
            HttpURLConnection httpConn = (HttpURLConnection) urlConn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            resCode = httpConn.getResponseCode();

            if (resCode == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
             return    ConvertToString.toString(in);
            }
        }
        catch(Exception e)
        {

        }

        return null;

    }

}

