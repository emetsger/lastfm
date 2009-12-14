package net.uppertank.lastfm.om;

import net.uppertank.lastfm.support.Assertion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: esm
 * Date: Dec 13, 2009
 * Time: 6:13:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Neighbours
{
    private String user;
    private List<User> neighbours = new ArrayList<User>();

    public String getUser()
    {
        return user;
    }

    public void setUser( String user )
    {
        Assertion.notNullOrEmpty( user );
        this.user = user;
    }

    public List<User> getNeighbours()
    {
        return neighbours;
    }

    public void setNeighbours( List<User> neighbours )
    {
        Assertion.notNull( neighbours );
        this.neighbours = neighbours;
    }

    public void addNeighbour( User... neighbour )
    {
        this.neighbours.addAll( Arrays.asList( neighbour ) );       
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        Neighbours that = (Neighbours)o;

        if ( user != null ? !user.equals( that.user ) : that.user != null ) return false;
        if ( this.neighbours == null &&  that.neighbours != null ) return false;
        if ( that.neighbours == null && this.neighbours != null ) return false;
        if ( this.neighbours.size() != that.neighbours.size() ) return false;
        if ( ! this.neighbours.equals( that.neighbours ) ) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + ( neighbours != null ? neighbours.hashCode() : 0 );
        return result;
    }
}
