package com.larcomlabs.lab1.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails
{
    @Id
    private String username;
    private String password;
    private String email;
    private String streetAddress;

    @OneToMany(mappedBy = "owner")
    private List<VideoGame> games;

    @OneToMany(mappedBy = "offeree")
    private List<Offer> offers;

    public User()
    {
    }

    public User(String username, String password, String email, String streetAddress)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.streetAddress = streetAddress;
    }

    public List<Offer> getOffers()
    {
        return offers;
    }

    public void setOffers(List<Offer> offers)
    {
        this.offers = offers;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public List<VideoGame> getGames()
    {
        return games;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setGames(List<VideoGame> games)
    {
        this.games = games;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getStreetAddress()
    {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress)
    {
        this.streetAddress = streetAddress;
    }

    public void addGame(VideoGame game)
    {
        this.games.add(game);
    }

    public void removeGame(VideoGame game)
    {
        this.games.remove(game);
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new GrantedAuthority()
        {

            private static final long serialVersionUID = 1L;

            @Override
            public String getAuthority()
            {
                return "USER";
            }
        });
        return authorities;
    }
}
