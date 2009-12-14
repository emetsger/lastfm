package net.uppertank.lastfm;

/**
 * Created by IntelliJ IDEA.
 * User: esm
 * Date: Dec 13, 2009
 * Time: 6:12:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Response
{
    private String lastfmStatus;
    private int httpStatus;
    private String xmlResponse;

    public String getXmlResponse()
    {
        return xmlResponse;
    }

    public void setXmlResponse( String xmlResponse )
    {
        this.xmlResponse = xmlResponse;
    }

    public String getLastfmStatus()
    {
        return lastfmStatus;
    }

    public void setLastfmStatus( String lastfmStatus )
    {
        this.lastfmStatus = lastfmStatus;
    }

    public int getHttpStatus()
    {
        return httpStatus;
    }

    public void setHttpStatus( int httpStatus )
    {
        this.httpStatus = httpStatus;
    }    
}
