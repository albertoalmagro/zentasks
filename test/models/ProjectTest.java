package models;

import java.util.List;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import base.AbstractTest;

/**
 * Unit tests for Project Entity.
 * 
 * @author albertoalmagro
 *
 */
public class ProjectTest extends AbstractTest {
	
	@Test
	public void findProjectsInvolving() {
		new User("bob@gmail.com", "Bob", "secret").save();
		new User("jane@gmail.com", "Jane", "secret").save();
		
		Project.create("Play 2", "play", "bob@gmail.com");
		Project.create("Play 1", "play", "jane@gmail.com");
		
		List<Project> results = Project.findInvolving("bob@gmail.com");
		assertEquals(1, results.size());
		assertEquals("Play 2", results.get(0).name);
	}
	
	@Test
	public void testTotalProjects() {
		assertEquals(7, Project.find.findRowCount());
	}
	
	@Test
	public void testAllProjectsFromAnUser() {
		List<Project> bobsProjects = Project.findInvolving("bob@example.com");
        assertEquals(5, bobsProjects.size());
	}

}
