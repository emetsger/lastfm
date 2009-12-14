package net.uppertank.lastfm;

import net.uppertank.lastfm.om.Neighbours;

/**
 * Created by IntelliJ IDEA.
 * User: esm
 * Date: Dec 14, 2009
 * Time: 12:42:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class GetNeighboursResponse extends Response
{
    private Neighbours neighbours;

    public Neighbours getNeighbours()
    {
        return neighbours;
    }

    public void setNeighbours( Neighbours neighbours )
    {
        this.neighbours = neighbours;
    }
}
