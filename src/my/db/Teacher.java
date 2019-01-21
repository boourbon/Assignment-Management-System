package my.db;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Teacher entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "teacher", catalog = "demo1")
public class Teacher implements java.io.Serializable
{

	// Fields

	private Integer id;
	private String username;
	private String password;
	private String displayName;
	private String cellphone;
	private Timestamp timeCreated;

	// Constructors

	/** default constructor */
	public Teacher()
	{
	}

	/** full constructor */
	public Teacher(String username, String password, String displayName,
			String cellphone, Timestamp timeCreated)
	{
		this.username = username;
		this.password = password;
		this.displayName = displayName;
		this.cellphone = cellphone;
		this.timeCreated = timeCreated;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	@Column(name = "username", length = 64)
	public String getUsername()
	{
		return this.username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	@Column(name = "password", length = 64)
	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@Column(name = "displayName", length = 64)
	public String getDisplayName()
	{
		return this.displayName;
	}

	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	@Column(name = "cellphone", length = 16)
	public String getCellphone()
	{
		return this.cellphone;
	}

	public void setCellphone(String cellphone)
	{
		this.cellphone = cellphone;
	}

	@Column(name = "timeCreated", length = 19)
	public Timestamp getTimeCreated()
	{
		return this.timeCreated;
	}

	public void setTimeCreated(Timestamp timeCreated)
	{
		this.timeCreated = timeCreated;
	}

}