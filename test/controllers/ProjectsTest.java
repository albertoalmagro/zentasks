package controllers;

import models.Project;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import play.mvc.Result;
import play.test.Helpers;
import base.AbstractTest;

import com.google.common.collect.ImmutableMap;

public class ProjectsTest extends AbstractTest {

	private static final String PROJECT_GROUP = "Some Group";
	private static final String USER_EMAIL = "bob@example.com";

	@Test
	public void newProject() {
		Result result = Helpers.callAction(controllers.routes.ref.Projects.add(),
				Helpers.fakeRequest().withSession("email", USER_EMAIL)
				.withFormUrlEncodedBody(ImmutableMap.of("group", PROJECT_GROUP)));

		assertEquals(200, Helpers.status(result));
		
		Project project = Project.find.where()
				.eq("folder", PROJECT_GROUP).findUnique();
		assertNotNull(project);
		assertEquals("New project", project.name);
		assertEquals(1, project.members.size());
		assertEquals(USER_EMAIL, project.members.get(0).email);
	}
	
	@Test
	public void renameProject() {
		Long id = Project.find.where()
				.eq("members.email", USER_EMAIL)
				.eq("name", "Private").findUnique().id;
		Result result = Helpers.callAction(
				controllers.routes.ref.Projects.rename(id),
				Helpers.fakeRequest().withSession("email", USER_EMAIL)
				.withFormUrlEncodedBody(ImmutableMap.of("name", "New name"))
				);
		
		assertEquals(200, Helpers.status(result));
		assertEquals("New name", Project.find.byId(id).name);
	}
	
	@Test
	public void renameProjectForbidden() {
		Long id = Project.find.where()
				.eq("members.email", USER_EMAIL)
				.eq("name", "Private").findUnique().id;
		Result result = Helpers.callAction(
				controllers.routes.ref.Projects.rename(id),
				Helpers.fakeRequest().withSession("email", "jeff@example.com")
				.withFormUrlEncodedBody(ImmutableMap.of("name", "New Name"))
				);
		
		assertEquals(403, Helpers.status(result));
		assertEquals("Private", Project.find.byId(id).name);
	}

}
