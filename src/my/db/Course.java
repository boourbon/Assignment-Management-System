package my.db;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Course entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "course", catalog = "demo1")
public class Course implements java.io.Serializable
{

	// Fields

	private Integer id;
	private String title;
	private Integer teacher;
	private Timestamp timeCreated;

	// Constructors

	/** default constructor */
	public Course()
	{
	}

	/** full constructor */
	public Course(String title, Integer teacher, Timestamp timeCreated)
	{
		this.title = title;
		this.teacher = teacher;
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

	@Column(name = "title", length = 128)
	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	@Column(name = "teacher")
	public Integer getTeacher()
	{
		return this.teacher;
	}

	public void setTeacher(Integer teacher)
	{
		this.teacher = teacher;
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