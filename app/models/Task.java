package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

@Entity
public class Task extends Model {

	private static final long serialVersionUID = -6801788207756190144L;
	
	@Id
	public Long id;
	public String title;
	public boolean done = false;
	public Date dueDate;
	@ManyToOne
	public User assignedTo;
	public String folder;
	@ManyToOne
	public Project project;

	public static Model.Finder<Long, Task> find = new Model.Finder<>(
			Long.class, Task.class);

	public static List<Task> findTodoInvolving(String userEmail) {
		return find.fetch("project").where().eq("done", false)
				.eq("project.members.email", userEmail).findList();
	}

	public static Task create(Task task, Long project, String folder) {
		task.project = Project.find.ref(project);
		task.folder = folder;
		task.save();
		return task;
	}

}
