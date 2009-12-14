package net.uppertank.lastfm;

import net.uppertank.lastfm.om.Neighbours;
import net.uppertank.lastfm.om.User;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by IntelliJ IDEA.
 * User: esm
 * Date: Dec 14, 2009
 * Time: 1:20:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class GetNeighboursRequestTest
{
    @Test
    public void testSimpleLive() throws IOException
    {
        final GetNeighboursRequest request = new GetNeighboursRequest( "emetsger" );
        final Neighbours n = request.execute();
        assertEquals( "emetsger", n.getUser() );
        final String expectedNeighbourName = "dissolved__girl";
        final String expectedNeighbourRealname = "Ren";
        final String expectedNeighbourUrl = "http://www.last.fm/user/dissolved__girl";
        final User expectedNeighbour = new User();
        expectedNeighbour.setName( expectedNeighbourName );
        expectedNeighbour.setRealName( expectedNeighbourRealname );
        expectedNeighbour.setUrl( expectedNeighbourUrl );
        for ( User u : n.getNeighbours() )
        {
            if ( u.equals( expectedNeighbour ) ) return;
        }

        fail( "Did not find expected user" );
    }

}
