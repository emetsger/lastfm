package net.uppertank.lastfm.xstream;

import com.thoughtworks.xstream.XStream;
import net.uppertank.lastfm.om.Neighbours;
import net.uppertank.lastfm.om.User;
import net.uppertank.lastfm.xstream.NeighbourConverter;
import net.uppertank.lastfm.xstream.UserConverter;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: esm
 * Date: Dec 14, 2009
 * Time: 12:14:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class NeighbourConverterTest
{
    private XStream x;

    @Before
    public void setUp()
    {
        NeighbourConverter neighbourConverter = new NeighbourConverter();
        UserConverter userConverter = new UserConverter();

        assertTrue( neighbourConverter.canConvert( Neighbours.class ) );
        assertTrue( userConverter.canConvert( User.class ) );

        x = new XStream();
        x.registerConverter( neighbourConverter );
        x.registerConverter( userConverter );
        x.alias( "neighbours", Neighbours.class );
        x.alias( "user", User.class );
    }

    @Test
    public void testUnmarshal()
    {
        final String testXml = "<neighbours user=\"emetsger\">\n" +
                "        <user>\n" +
                "            <name>emetsger</name>\n" +
                "            <realname>Elliot Metsger</realname>\n" +
                "            <url>http://www.last.fm/user/emetsger</url>\n" +
                "        </user>\n" +
                "    </neighbours>";

        final String expectedName = "emetsger";
        final String expectedRealname = "Elliot Metsger";
        final String expectedUrl = "http://www.last.fm/user/emetsger";

        final User expectedUser = new User();
        expectedUser.setName( expectedName );
        expectedUser.setRealName( expectedRealname );
        expectedUser.setUrl( expectedUrl );

        final Neighbours expectedNeighbours = new Neighbours();
        expectedNeighbours.addNeighbour( expectedUser );
        expectedNeighbours.setUser( expectedName );

        Neighbours actualNeighbours = (Neighbours)x.fromXML( new ByteArrayInputStream( testXml.getBytes() ) );
        assertEquals( expectedNeighbours, actualNeighbours );
    }
}
