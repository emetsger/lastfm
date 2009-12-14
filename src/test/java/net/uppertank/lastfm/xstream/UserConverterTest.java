package net.uppertank.lastfm.xstream;

import com.thoughtworks.xstream.XStream;
import net.uppertank.lastfm.om.User;
import net.uppertank.lastfm.xstream.UserConverter;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: esm
 * Date: Dec 13, 2009
 * Time: 11:39:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserConverterTest
{
    @Test
    public void testUnmarshal()
    {
        final String testXml = "<user>\n" +
                "    <name>emetsger</name>\n" +
                "    <realname>Elliot Metsger</realname>\n" +
                "    <url>http://www.last.fm/user/emetsger</url>\n" +
                "</user>";
        final String expectedName = "emetsger";
        final String expectedRealname = "Elliot Metsger";
        final String expectedUrl = "http://www.last.fm/user/emetsger";
        final User expectedUser = new User();
        expectedUser.setName( expectedName );
        expectedUser.setRealName( expectedRealname );
        expectedUser.setUrl( expectedUrl );

        UserConverter converter = new UserConverter();
        assertTrue( converter.canConvert( expectedUser.getClass() ) );
        XStream x = new XStream();
        x.registerConverter( converter );
        x.alias( "user", expectedUser.getClass() );
        User actualUser = (User)x.fromXML( new ByteArrayInputStream( testXml.getBytes() ) );
        assertEquals( expectedUser, actualUser );
    }

}
