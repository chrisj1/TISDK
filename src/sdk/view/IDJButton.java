package sdk.view;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class IDJButton extends JButton 
{

	private int id;

	public IDJButton(int id) 
	{
		super();
		this.id = id;
	}

	public IDJButton() 
	{
		super();
		this.id = 0;
	}

	public IDJButton(Action arg0) 
	{
		super(arg0);
		this.id = 0;
	}

	public IDJButton(Icon arg0) 
	{
		super(arg0);
		this.id = 0;
	}

	public IDJButton(String arg0, Icon arg1) 
	{
		super(arg0, arg1);
		this.id = 0;
	}

	public IDJButton(String arg0) 
	{
		super(arg0);
		this.id = 0;
	}

	/**
	 * @return the id
	 */
	public int getId() 
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) 
	{
		this.id = id;
	}



}
