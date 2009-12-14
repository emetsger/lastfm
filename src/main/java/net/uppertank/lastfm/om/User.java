package net.uppertank.lastfm.om;

/**
 * Created by IntelliJ IDEA.
 * User: esm
 * Date: Dec 13, 2009
 * Time: 6:09:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class User
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
    private String name;
    private String realName;
    private String url;

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getRealName()
    {
        return realName;
    }

    public void setRealName( String realName )
    {
        this.realName = realName;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl( String url )
    {
        this.url = url;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        User user = (User)o;

        if ( name != null ? !name.equals( user.name ) : user.name != null ) return false;
        if ( realName != null ? !realName.equals( user.realName ) : user.realName != null ) return false;
        if ( url != null ? !url.equals( user.url ) : user.url != null ) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + ( realName != null ? realName.hashCode() : 0 );
        result = 31 * result + ( url != null ? url.hashCode() : 0 );
        return result;
    }
}
