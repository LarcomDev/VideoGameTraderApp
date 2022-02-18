package com.larcomlabs.lab1.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class VideoGame
{
    public enum eCondition {
        MINT,GOOD,FAIR,POOR
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameId;

    private String name;
    private String publisher;
    private String system;
    private int releaseYear;
    private eCondition condition;

    @JsonIgnore
    @ManyToOne
    private User owner;

    public int getGameId() {
        return gameId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPublisher()
    {
        return publisher;
    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    public String getSystem()
    {
        return system;
    }

    public void setSystem(String system)
    {
        this.system = system;
    }

    public int getReleaseYear()
    {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear)
    {
        this.releaseYear = releaseYear;
    }

    public User getOwner()
    {
        return owner;
    }

    public void setOwner(User user)
    {
        this.owner = user;
    }

    public eCondition getCondition()
    {
        return condition;
    }

    public void setCondition(eCondition condition)
    {
        this.condition = condition;
    }

    @Override
    public String toString()
    {
        return "VideoGame{" +
                "gameId=" + gameId +
                ", name='" + name + '\'' +
                '}';
    }
}
