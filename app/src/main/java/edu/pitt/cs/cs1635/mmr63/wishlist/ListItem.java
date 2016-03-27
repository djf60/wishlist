package edu.pitt.cs.cs1635.mmr63.wishlist;
/**
 * Created by Max on 3/27/2016.
 */
public class ListItem {
    public String name;
    public int priority;
    public String description;
    public double price;
    public String source;
    public int itemID;
    public boolean purchased;
    public String username;

    public ListItem(String n, int p, String d, double pr, String src, int id,
                    boolean purch, String user)
    {
        name = n;
        priority = p;
        description = d;
        price = pr;
        source = src;
        itemID = id;
        username = user;
        purchased = purch;
    }

    public String getName()
    {
        return name;
    }

    public int getPriority()
    {
        return priority;
    }

    public String getDescription()
    {
        return description;
    }

    public double getPrice()
    {
        return price;
    }

    public String getSource()
    {
        return source;
    }

    public int getID()
    {
        return itemID;
    }

    public boolean isPurchased()
    {
        return purchased;
    }

    public String getUser()
    {
        return username;
    }


}
