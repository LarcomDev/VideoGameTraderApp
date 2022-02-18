package com.larcomlabs.lab1.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Offer
{
    public enum eStatus {
        ACCEPTED, PENDING, REJECTED
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int offerId;

    @ManyToOne
    @JsonIgnore
    private User offeree;
    private LocalDateTime dateTime;

    //need jpa to ignore these
    @ManyToMany
    private List<VideoGame> offeredFor;

    @ManyToMany
    private List<VideoGame> offeredTo;

    private eStatus status;

    public Offer(){
        setOfferedFor(new ArrayList<VideoGame>());
        setOfferedTo(new ArrayList<VideoGame>());
        setStatus(eStatus.PENDING);
    }

    public Offer(User offeree, List<VideoGame> offeredFor, List<VideoGame> offeredTo)
    {
        this.offeree = offeree;
        this.offeredFor = offeredFor;
        this.offeredTo = offeredTo;
    }

    public int getOfferId()
    {
        return offerId;
    }

    public User getOfferee()
    {
        return offeree;
    }

    public List<VideoGame> getOfferedFor()
    {
        return offeredFor;
    }

    public List<VideoGame> getOfferedTo()
    {
        return offeredTo;
    }

    public void setOfferId(int offerId)
    {
        this.offerId = offerId;
    }

    public void setOfferee(User offeree)
    {
        this.offeree = offeree;
    }

    public void setOfferedFor(List<VideoGame> offeredFor)
    {
        this.offeredFor = offeredFor;
    }

    public void setOfferedTo(List<VideoGame> offeredTo)
    {
        this.offeredTo = offeredTo;
    }

    @Override
    public String toString()
    {
        return "Offer{" +
                "offerId=" + offerId +
                ", offeree=" + offeree +
                ", offeredFor=" + offeredFor.toString() +
                ", offeredTo=" + offeredTo.toString() +
                '}';
    }

    public LocalDateTime getDateTime()
    {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime)
    {
        this.dateTime = dateTime;
    }

    public eStatus getStatus()
    {
        return status;
    }

    public void setStatus(eStatus status)
    {
        this.status = status;
    }
}
