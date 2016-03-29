package edu.pitt.cs.cs1635.mmr63.wishlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 3/27/2016.
 */
public class WishList {
    private ArrayList<ListItem> items;
    private String username;
    private int listID;
    boolean isPurchasedList;

    public WishList(String u, int id, List i, boolean purchased)
    {
        username = u;
        listID = id;
        items = (ArrayList)i;
        isPurchasedList = purchased;
    }

    public WishList(String u, int id)
    {
        this(u, id, new ArrayList(), false);
    }

    public WishList(String u)
    {
        this(u, 0, new ArrayList(), false);
    }
    public WishList()
    {
        this("", 0, new ArrayList(), false);
    }

    public void addItem(ListItem newItem)
    {
        items.add(newItem);
    }

    public void removeItem(ListItem toRemove)
    {
        items.remove(toRemove);
    }

    public ArrayList<ListItem> getList()
    {
        return items;
    }

    public String getUser()
    {
        return username;
    }

    public int getID()
    {
        return listID;
    }

    public boolean isPurchaseList()
    {
        return isPurchasedList;
    }

    public void setUser(String u)
    {
        username = u;
    }

    public void setID(int i)
    {
        listID = i;
    }
}
