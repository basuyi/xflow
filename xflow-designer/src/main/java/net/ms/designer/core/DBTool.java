package net.ms.designer.core;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.internal.UIPlugin;

public class DBTool implements Serializable
{	
	String conName;
	String dbType;
	String port;
	String username;
	String password;
	String server;
	String dbName;
	String url;
	Connection connection;
		
	public Connection getConnection()
	{
		String prefix = null;
		String className = null;
		StringBuffer sb = new StringBuffer();
		if(dbType.equals("Oracle"))
		{
			prefix = "jdbc:oracle:thin:@";
			className = "oracle.jdbc.driver.OracleDriver";
			sb.append(prefix);
			sb.append(server);
			sb.append(":");
			sb.append(port);
			sb.append(":");
			sb.append(dbName);
		} else if (dbType.equalsIgnoreCase("mysql")) {
			prefix = "jdbc:mysql://";
			className = "org.gjt.mm.mysql.Driver";
			sb.append(prefix);
			sb.append(server);
			sb.append(":");
			sb.append(port);
			sb.append("/");
			sb.append(dbName);
		}
		url = sb.toString();
		
		Class c = null;
		try
		{
			c = Class.forName(className);
			connection = DriverManager.getConnection(url,username,password);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			if(c == null)
			{
				MessageBox dialog1 = new MessageBox(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell());
				dialog1.setMessage("driver is null");
				dialog1.open();
			}
		}
		return connection;
	}
	public boolean testConnection()
	{
		if(this.getConnection() != null)
		{
			return true;
		}
		else
		{
			MessageBox dialog = new MessageBox(UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell());
			dialog.setMessage("url="+url);
			dialog.open();
			return false;
		}
	}
//	DBType
	public String getDbType()
	{
		return this.dbType;
	}
	public void setDbType(String str)
	{
		this.dbType = str;
	}
//	DBName
	public String getDbName()
	{
		return this.dbName;
	}
	public void setDbName(String str)
	{
		this.dbName = str;
	}
//	server
	public String getServer()
	{
		return this.server;
	}
	public void setServer(String str)
	{
		this.server = str;
	}
//	Port
	public String getPort()
	{
		return this.port;
	}
	public void setPort(String str)
	{
		this.port = str;
	}
//	userName
	public String getUsername()
	{
		return this.username;
	}
	public void setUsername(String str)
	{
		this.username = str;
	}
//	password
	public String getPassword()
	{
		return this.password;
	}
	public void setPassword(String str)
	{
		this.password = str;
	}
//	url
	public String getUrl()
	{
		return this.url;
	}
	public void setUrl(String str)
	{
		this.url = str;
	}
//	connect name
	public String getConName()
	{
		return this.conName;
	}
	public void setConName(String str)
	{
		this.conName = str;
	}
}