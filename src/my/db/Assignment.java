package my.db;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Assignment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "assignment", catalog = "demo1")
public class Assignment implements java.io.Serializable
{

	// Fields

	private Integer id;
	private String title;
	private Integer course;
	private Integer teacher;
	private String descr;
	private Timestamp timeCreated;

	// Constructors

	/** default constructor */
	public Assignment()
	{
	}

	/** full constructor */
	public Assignment(String title, Integer course, Integer teacher,
			String descr, Timestamp timeCreated)
	{
		this.title = title;
		this.course = course;
		this.teacher = teacher;
		this.descr = descr;
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

	@Column(name = "course")
	public Integer getCourse()
	{
		return this.course;
	}

	public void setCourse(Integer course)
	{
		this.course = course;
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

	@Column(name = "descr", length = 65535)
	public String getDescr()
	{
		return this.descr;
	}

	public void setDescr(String descr)
	{
		this.descr = descr;
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