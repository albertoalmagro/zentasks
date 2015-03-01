package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import play.db.ebean.Model;

@Entity
public class Project extends Model {
	
	private static final long serialVersionUID = 1295470725921306847L;
	
	@Id
	public Long id;
	public String name;
	public String folder;
	
	@ManyToMany(cascade = CascadeType.REMOVE)
	public List<User> members = new ArrayList<User>();

	public Project(String name, String folder, User ownerEmail) {
		this.name = name;
		this.folder = folder;
		this.members.add(ownerEmail);
	}
	
	public static Model.Finder<Long, Project> find = new Model.Finder<>(Long.class, Project.class);
	
	public static Project create(String name, String folder, String owner) {
		Project project = new Project(name, folder, User.find.ref(owner));
		project.save();
		project.saveManyToManyAssociations("members");
		return project;
	}
	
	public static List<Project> findInvolving(String userEmail) {
		return find.where().eq("members.email", userEmail).findList();
	}

	public static boolean isMember(Long project, String username) {
		return find.where()
				.eq("members.email", username)
				.eq("id", project)
				.findRowCount() > 0;
	}
	
	public static String rename(Long projectId, String newName) {
		Project project = find.ref(projectId);
		project.name = newName;
		project.update();
		return newName;
	}

}
