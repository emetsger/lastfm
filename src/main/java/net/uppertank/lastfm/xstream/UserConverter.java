package net.uppertank.lastfm.xstream;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import net.uppertank.lastfm.om.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by IntelliJ IDEA.
 * User: esm
 * Date: Dec 13, 2009
 * Time: 11:31:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserConverter implements Converter
{
    /*
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
     */

    private Logger log = LoggerFactory.getLogger( this.getClass() );

    public void marshal( Object o, HierarchicalStreamWriter writer, MarshallingContext context )
    {
        User u = (User)o;
        writer.startNode( "user" );

        writer.startNode( "name" );
        writer.setValue( u.getName() );
        writer.endNode();

        writer.startNode( "realname" );
        writer.setValue( u.getRealName() );
        writer.endNode();

        writer.startNode( "url" );
        writer.setValue( u.getUrl() );
        writer.endNode();

        writer.endNode();
    }

    public Object unmarshal( HierarchicalStreamReader reader, UnmarshallingContext context )
    {
        final User u = new User();
        // Start out inside of the <user> element.
        log.debug( "Inside element: {}", reader.getNodeName() );

        while ( reader.hasMoreChildren() )
        {
            reader.moveDown();
            final String name = reader.getNodeName();
            log.debug( "Inside element: {}", name );

            if ( name.equals( "name" ) )
            {
                u.setName( reader.getValue() );
            }

            if ( name.equals( "realname" ) )
            {
                u.setRealName( reader.getValue() );
            }

            if ( name.equals( "url" ) )
            {
                u.setUrl( reader.getValue() );
            }
            reader.moveUp();
        }

        return u;
    }

    public boolean canConvert( Class aClass )
    {
        return ( aClass == User.class );
    }
}
