package lab.wasikrafal.movies;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafał on 10.05.2017.
 */

public class Movie implements Serializable
{
    private String title, genre, year;
    private float rating=2;
    private List<ActorItem> actorList=new ArrayList<ActorItem>();
    private List<Integer> imageList = new ArrayList<Integer>();

    public Movie()
    {
    }

    public Movie(String title, String genre, String year)
    {
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String name)
    {
        this.title = name;
    }

    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public String getGenre()
    {
        return genre;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public float getRating()
    {
        return rating;
    }

    public void setRating(float rating)
    {
        this.rating = rating;
    }

    public String toString()
    {
        return "Title: " + title + "\nGenre: " + genre + "\nYear: " + year;
    }

    public void setActorList(List<String> names)
    {
        for (String s:names)
            actorList.add(new ActorItem(s));

    }

    public List<ActorItem> getActorList()
    {
        return actorList;
    }

    public void setImageList(List<Integer> images)
    {
        for (Integer s:images)
            imageList.add(s);
    }

    public int getImage(int i)
    {
        return imageList.get(i);
    }
}