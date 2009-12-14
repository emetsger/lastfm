package net.uppertank.lastfm.xstream;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import net.uppertank.lastfm.om.Neighbours;
import net.uppertank.lastfm.om.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: esm
 * Date: Dec 14, 2009
 * Time: 12:03:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class NeighbourConverter implements Converter
{
    /*
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
        <user>
            ...
        </user>
        ...
    </neighbours>
     */

    private Logger log = LoggerFactory.getLogger( this.getClass() );

    public void marshal( Object o, HierarchicalStreamWriter writer, MarshallingContext context )
    {
        final Neighbours n = (Neighbours)o;

        writer.startNode( "neighbours" );
        writer.addAttribute( "user", n.getUser() );

        for ( User neighbour : n.getNeighbours() )
        {
            context.convertAnother( neighbour );
        }

        writer.endNode();
    }

    public Object unmarshal( HierarchicalStreamReader reader, UnmarshallingContext context )
    {
        final Neighbours n = new Neighbours();
        // start out in <neighbours user="..."> element
        log.debug( "Inside element: {}", reader.getNodeName() );
        n.setUser(  reader.getAttribute( "user" ) );

        while ( reader.hasMoreChildren() )
        {
            reader.moveDown();
            log.debug( "Inside element: {}", reader.getNodeName() );
            final User u = (User)context.convertAnother( n, User.class );
            n.addNeighbour( u );
            reader.moveUp();
        }

        return n;
    }

    public boolean canConvert( Class aClass )
    {
        return aClass == Neighbours.class;

    }
}
