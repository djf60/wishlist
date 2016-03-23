public class Wishlist
{
	private ArrayList<ListItem> items;
	private String username;
	private int listID;
	boolean isPurchasedList;

	public Wishlist(String u, int id, List i, boolean purchased)
	{
		username = u;
		listID = id;
		items = i;
		isPurchasedList = purchased;
	}

	public Wishlist(String u, int id)
	{
		this(u, id, null, false);
	}

	public Wishlist(String u)
	{
		this(u, 0, null, false);
	}
	public Wishlist()
	{
		this("", 0, null, false);
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
