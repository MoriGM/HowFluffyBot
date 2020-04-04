package de.morigm.howfluffybot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config 
{
	
	private Properties prop;

	public Config() 
	{
		this.prop = new Properties();
		
		if (!new File("config.prop").exists())
		{
			try 
			{
				new File("config.prop").createNewFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		try
		{
			prop.load(new FileInputStream("config.prop"));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		setDefaultValue();
		
	}
	
	private void setDefaultValue() 
	{
		boolean save = false;
		if (!prop.containsKey("bot.key")) { prop.setProperty("bot.key", "key"); save = true;}
		if (!prop.containsKey("bot.key")) { prop.setProperty("bot.name", "name"); save = true;}
		if (!prop.containsKey("bot.language")) { prop.setProperty("bot.language", "en"); save = true;}
		
		if (save)
			save();
	}
	
	public String getProperty(String prop)
	{
		if (this.prop.containsKey(prop))
			return this.prop.getProperty(prop);
		return "";
	}

	public void save()
	{
		try 
		{
			prop.store(new FileOutputStream("config.prop"), null);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
