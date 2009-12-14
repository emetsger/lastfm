package net.uppertank.lastfm.support;

/**
 * Created by IntelliJ IDEA.
 * User: esm
 * Date: Dec 13, 2009
 * Time: 6:14:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class Assertion
{
    public static void notNull( Object o )
    {
        if ( o == null )
        {
            throw new IllegalArgumentException( "Object must not be null." );
        }
    }

    public static void notNullOrEmpty( String s )
    {
        if ( s == null || s.trim().length() == 0 )
        {
            throw new IllegalArgumentException( "String must not be null or empty" );
        }
    }

}
