public class ListItem
{
	private String name;
	private int priority;
	private String description;
	private double price;
	private String source;
	private int itemID;
	private boolean purchased;
	private String username;

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
