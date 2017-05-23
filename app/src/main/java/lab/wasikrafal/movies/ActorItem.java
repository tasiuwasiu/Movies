package lab.wasikrafal.movies;

import java.io.Serializable;

/**
 * Created by Rafał on 23.05.2017.
 */

class ActorItem implements Serializable
{
    private String name;

    public ActorItem(String n)
    {
        name=n;
    }

    public String getName()
    {
        return name;
    }
}
