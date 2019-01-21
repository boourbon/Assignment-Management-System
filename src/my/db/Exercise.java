package my.db;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Exercise entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "exercise", catalog = "demo1")
public class Exercise implements java.io.Serializable
{

	// Fields

	private Integer id;
	private String title;
	private Integer assignment;
	private Integer course;
	private Integer teachear;
	private String student;
	private Short status;
	private Short score;
	private Timestamp timeCreated;
	private Timestamp timeCommit;
	private String storePath;
	private String files;

	// Constructors

	/** default constructor */
	public Exercise()
	{
	}

	/** full constructor */
	public Exercise(String title, Integer assignment, Integer course,
			Integer teachear, String student, Short status, Short score,
			Timestamp timeCreated, Timestamp timeCommit, String storePath,
			String files)
	{
		this.title = title;
		this.assignment = assignment;
		this.course = course;
		this.teachear = teachear;
		this.student = student;
		this.status = status;
		this.score = score;
		this.timeCreated = timeCreated;
		this.timeCommit = timeCommit;
		this.storePath = storePath;
		this.files = files;
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

	@Column(name = "assignment")
	public Integer getAssignment()
	{
		return this.assignment;
	}

	public void setAssignment(Integer assignment)
	{
		this.assignment = assignment;
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

	@Column(name = "teachear")
	public Integer getTeachear()
	{
		return this.teachear;
	}

	public void setTeachear(Integer teachear)
	{
		this.teachear = teachear;
	}

	@Column(name = "student", length = 64)
	public String getStudent()
	{
		return this.student;
	}

	public void setStudent(String student)
	{
		this.student = student;
	}

	@Column(name = "status")
	public Short getStatus()
	{
		return this.status;
	}

	public void setStatus(Short status)
	{
		this.status = status;
	}

	@Column(name = "score")
	public Short getScore()
	{
		return this.score;
	}

	public void setScore(Short score)
	{
		this.score = score;
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

	@Column(name = "timeCommit", length = 19)
	public Timestamp getTimeCommit()
	{
		return this.timeCommit;
	}

	public void setTimeCommit(Timestamp timeCommit)
	{
		this.timeCommit = timeCommit;
	}

	@Column(name = "storePath", length = 256)
	public String getStorePath()
	{
		return this.storePath;
	}

	public void setStorePath(String storePath)
	{
		this.storePath = storePath;
	}

	@Column(name = "files", length = 512)
	public String getFiles()
	{
		return this.files;
	}

	public void setFiles(String files)
	{
		this.files = files;
	}

}