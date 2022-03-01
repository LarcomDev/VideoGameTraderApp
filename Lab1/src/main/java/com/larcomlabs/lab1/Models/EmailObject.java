package com.larcomlabs.lab1.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmailObject implements Serializable
{
    private List<String> emails;
    private String username;
    private String newPass;
    private EmailType type;

    public EmailObject()
    {
    }

    public EmailObject(String email, String username, String newPass, EmailType type)
    {
        this.emails = new ArrayList<>();
        this.emails.add(email);
        this.username = username;
        this.newPass = newPass;
        this.type = type;
    }

    public EmailObject(List<String> emails, String username, String newPass, EmailType type)
    {
        this.emails = emails;
        this.username = username;
        this.newPass = newPass;
        this.type = type;
    }

    public List<String> getEmails()
    {
        return emails;
    }

    public void setEmails(List<String> emails)
    {
        this.emails = emails;
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

    public EmailType getType()
    {
        return type;
    }

    public void setType(EmailType type)
    {
        this.type = type;
    }
}
