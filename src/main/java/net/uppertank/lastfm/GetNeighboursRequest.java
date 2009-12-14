package net.uppertank.lastfm;

import net.uppertank.lastfm.om.Neighbours;
import net.uppertank.lastfm.xstream.XstreamResponseBuilder;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: esm
 * Date: Dec 13, 2009
 * Time: 6:23:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class GetNeighboursRequest extends Request
{
    public GetNeighboursRequest( String userName )
    {
        super( userName, "user.getNeighbours" );
    }

    public Neighbours execute() throws IOException
    {
        StringBuilder query = new StringBuilder( "user=" ).append( userName ).append( "&" )
                .append( "api_key=" ).append( apiKey ).append( "&" )
                .append( "method=" ).append( getMethodName() );

        final String queryString = query.toString();
        final HttpMethod m = new GetMethod( baseUri.toString() );
        m.setQueryString( queryString );

        final GetNeighboursResponse response;
        try
        {
            log.debug( "Executing query: {}", m.getURI().getURI()  );
            final int status = client.executeMethod( m );
            log.debug( "Response status: {}", status );
            final InputStream responseInputStream = m.getResponseBodyAsStream();
            final XstreamResponseBuilder builder = new XstreamResponseBuilder();
            response = builder.buildGetNeighboursResponse( responseInputStream );
        }
        catch ( IOException e )
        {
            final String msg = "Error executing query '" + m.getURI().getURI() + "': " + e.getMessage();
            log.error( msg, e );
            throw e;
        }
        finally
        {
            m.releaseConnection();
        }

        if ( !"ok".equals( response.getLastfmStatus() ) )
        {
            throw new IOException( "Request failed: LastFm response status was not equal to 'ok'" );
        }
        return response.getNeighbours();
    }
}
