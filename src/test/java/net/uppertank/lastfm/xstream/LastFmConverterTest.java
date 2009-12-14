package net.uppertank.lastfm.xstream;

import com.thoughtworks.xstream.XStream;
import net.uppertank.lastfm.GetNeighboursResponse;
import net.uppertank.lastfm.Response;
import net.uppertank.lastfm.om.Neighbours;
import net.uppertank.lastfm.om.User;
import net.uppertank.lastfm.xstream.LastFmConverter;
import net.uppertank.lastfm.xstream.NeighbourConverter;
import net.uppertank.lastfm.xstream.UserConverter;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by IntelliJ IDEA.
 * User: esm
 * Date: Dec 14, 2009
 * Time: 1:00:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class LastFmConverterTest
{
    private Logger log = LoggerFactory.getLogger( this.getClass() );
    private XStream x;

    @Before
    public void setUp()
    {
        NeighbourConverter neighbourConverter = new NeighbourConverter();
        UserConverter userConverter = new UserConverter();
        LastFmConverter lfmConverter = new LastFmConverter();

        assertTrue( neighbourConverter.canConvert( Neighbours.class ) );
        assertTrue( userConverter.canConvert( User.class ) );
        assertTrue( lfmConverter.canConvert( GetNeighboursResponse.class ) );

        x = new XStream();
        x.registerConverter( neighbourConverter );
        x.registerConverter( userConverter );
        x.registerConverter( lfmConverter );
        x.alias( "neighbours", Neighbours.class );
        x.alias( "user", User.class );
        x.alias( "lfm", Response.class );
    }

    @Test
    public void testUnmarshal()
    {
        final String testXml = "<lfm status=\"ok\">\n" +
                "<neighbours user=\"emetsger\">\n" +
                    "<user>\n" +
                    "    <name>emetsger</name>\n" +
                    "    <realname>Elliot Metsger</realname>\n" +
                    "    <url>http://www.last.fm/user/emetsger</url>\n" +
                    "</user>\n" +
                "</neighbours>\n" +
            "</lfm>";

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

        final Response response = (Response)x.fromXML( new ByteArrayInputStream( testXml.getBytes() ), new GetNeighboursResponse() );
        assertNotNull( response );
        assertTrue( response instanceof GetNeighboursResponse );
        final GetNeighboursResponse gnr = (GetNeighboursResponse)response;
        assertEquals( "ok", gnr.getLastfmStatus() );
        assertEquals( expectedNeighbours, gnr.getNeighbours() );
    }

    @Test
    public void testBigBoysUnmarshal()
    {
        InputStream testXml = this.getClass().getResourceAsStream( "/test-getneighbours-response.xml" );

        final String expectedNeighbourName = "dissolved__girl";
        final String expectedNeighbourRealname = "Ren";
        final String expectedNeighbourUrl = "http://www.last.fm/user/dissolved__girl";
        final User expectedNeighbour = new User();
        expectedNeighbour.setName( expectedNeighbourName );
        expectedNeighbour.setRealName( expectedNeighbourRealname );
        expectedNeighbour.setUrl( expectedNeighbourUrl );

        final Response response = (Response)x.fromXML( testXml, new GetNeighboursResponse() );
        assertNotNull( response );
        assertTrue( response instanceof GetNeighboursResponse );
        final GetNeighboursResponse gnr = (GetNeighboursResponse)response;
        assertEquals( "ok", gnr.getLastfmStatus() );

        Neighbours n = gnr.getNeighbours();
        assertNotNull( n );
        log.debug( "Got {} neighbors", n.getNeighbours().size() );
        assertEquals( "emetsger", n.getUser() );
        for ( User u : gnr.getNeighbours().getNeighbours() )
        {
            if ( u.equals( expectedNeighbour ) ) return;
        }

        fail( "Did not find expected user" );
    }
}
