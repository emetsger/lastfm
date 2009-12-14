package net.uppertank.lastfm.xstream;

import com.thoughtworks.xstream.XStream;
import net.uppertank.lastfm.GetNeighboursResponse;
import net.uppertank.lastfm.Response;
import net.uppertank.lastfm.om.Neighbours;
import net.uppertank.lastfm.om.User;
import net.uppertank.lastfm.xstream.LastFmConverter;
import net.uppertank.lastfm.xstream.NeighbourConverter;
import net.uppertank.lastfm.xstream.UserConverter;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: esm
 * Date: Dec 13, 2009
 * Time: 11:29:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class XstreamResponseBuilder
{
    private final XStream xstream = new XStream();

    public XstreamResponseBuilder()
    {
        init();
    }

    public GetNeighboursResponse buildGetNeighboursResponse( InputStream responseContent )
    {
        return (GetNeighboursResponse)xstream.fromXML( responseContent, new GetNeighboursResponse() );       
    }

    private void init()
    {
        xstream.registerConverter( new UserConverter() );
        xstream.alias( "user", User.class );

        xstream.registerConverter( new NeighbourConverter() );
        xstream.alias( "neighbours", Neighbours.class );

        xstream.registerConverter( new LastFmConverter() );
        xstream.alias( "lfm", Response.class );
    }

}
