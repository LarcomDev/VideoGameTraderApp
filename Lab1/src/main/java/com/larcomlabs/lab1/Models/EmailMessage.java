package com.larcomlabs.lab1.Models;

public class EmailMessage
{
    private String email;
    private String username;
    private String newPass;

    public EmailMessage(String email, String username, String newPass)
    {
        this.email = email;
        this.username = username;
        this.newPass = newPass;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getNewPass()
    {
        return newPass;
    }

    public void setNewPass(String newPass)
    {
        this.newPass = newPass;
    }

    @Override
    public String toString()
    {
        return email + ":" + username + ":" + newPass;
    }
}
