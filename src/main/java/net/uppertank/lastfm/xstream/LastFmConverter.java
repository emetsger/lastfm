package net.uppertank.lastfm.xstream;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import net.uppertank.lastfm.GetNeighboursResponse;
import net.uppertank.lastfm.Response;
import net.uppertank.lastfm.om.Neighbours;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: esm
 * Date: Dec 14, 2009
 * Time: 12:43:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class LastFmConverter implements Converter
{
    /*
    <lfm status="ok">
        <neighbours user="emetsger">
            <user>
                <name>t00littletim3</name>
                <realname>Mandy</realname>
                <url>http://www.last.fm/user/t00littletim3</url>
                <image size="small">http://userserve-ak.last.fm/serve/34/14959507.png</image>
                <image size="medium">http://userserve-ak.last.fm/serve/64/14959507.png</image>
                <image size="large">http://userserve-ak.last.fm/serve/126/14959507.png</image>
                <image size="extralarge">http://userserve-ak.last.fm/serve/252/14959507.png</image>
                <match>0.0060814199969172</match>
            </user>
            ...
        </neighbours>
        ...
     </lfm>
     */

    private Logger log = LoggerFactory.getLogger( this.getClass() );

    public void marshal( Object o, HierarchicalStreamWriter writer, MarshallingContext context )
    {
        writer.startNode( "lfm" );

        if ( o instanceof GetNeighboursResponse )
        {
            final GetNeighboursResponse r = (GetNeighboursResponse)o;
            writer.addAttribute( "status", r.getLastfmStatus() );
            context.convertAnother( o );
        }

        writer.endNode();
    }

    public Object unmarshal( HierarchicalStreamReader reader, UnmarshallingContext context )
    {
        // Start inside the <lfm ...> element
        Response response = null;
        log.debug( "Inside element: {}", reader.getNodeName() );
        final String lastfmStatus = reader.getAttribute( "status" );

        while ( reader.hasMoreChildren() )
        {
            reader.moveDown();
            final String name = reader.getNodeName();
            log.debug( "Inside element: {}", name );

            if ( name.equals( "neighbours" ) )
            {
                Neighbours n = (Neighbours)context.convertAnother( new Object(), Neighbours.class );
                response = (GetNeighboursResponse)context.currentObject();
                ( (GetNeighboursResponse)response ).setNeighbours( n );
                ( (GetNeighboursResponse)response ).setLastfmStatus( lastfmStatus );
                return response;
            }

            reader.moveUp();
        }

        return null;

    }

    public boolean canConvert( Class aClass )
    {
        return Response.class.isAssignableFrom( aClass );
    }
}
