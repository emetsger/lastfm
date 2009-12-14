package net.uppertank.lastfm;


import net.uppertank.lastfm.support.Assertion;
import org.apache.commons.httpclient.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by IntelliJ IDEA.
 * User: esm
 * Date: Dec 13, 2009
 * Time: 6:23:36 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Request
{
    private final static String DEFAULT_BASE_URL = "http://ws.audioscrobbler.com/2.0/";
    private final static String DEFAULT_API_KEY = "0ce952465e4cfe85d9bd68312c11bcd1";

    final Logger log = LoggerFactory.getLogger( this.getClass() );
    final String methodName;
    final HttpClient client;

    final String userName;
    final URI baseUri;
    String apiKey = DEFAULT_API_KEY;

    public Request( String userName, String methodName )
    {
        Assertion.notNullOrEmpty( userName );
        Assertion.notNullOrEmpty( methodName );
        this.userName = userName;
        this.methodName = methodName;
        try
        {
            this.baseUri = new URI( DEFAULT_BASE_URL );
        }
        catch ( URISyntaxException e )
        {
            throw new IllegalArgumentException( "Default base url is malformed: '" + DEFAULT_BASE_URL + "':" + e.getMessage() );
        }
        this.client = new HttpClient();
    }

    public String getMethodName()
    {
        return methodName;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getApiKey()
    {
        return apiKey;
    }

    public String getBaseUrl()
    {
        return baseUri.toString();
    }    

}
