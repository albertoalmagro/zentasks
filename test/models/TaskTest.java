package models;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for Task Entity.
 * 
 * @author albertoalmagro
 *
 */
public class TaskTest extends AbstractModelTest {
	
	@Test
	public void findTodoTasksInvolving() {
		User bob = new User("bob@gmail.com", "Bob", "secret");
		bob.save();
		
		Project project = Project.create("Play 2", "play", "bob@gmail.com");
		Task t1 = new Task();
		t1.title = "Write tutorial";
		t1.assignedTo = bob;
		t1.done = true;
		t1.save();

		Task t2 = new Task();
		t2.title = "Release next version";
		t2.project = project;
		t2.save();
		
		List<Task> results = Task.findTodoInvolving("bob@gmail.com");
		assertEquals(1, results.size());
		assertEquals("Release next version", results.get(0).title);
	}
	
	@Test
	public void testTotalTasks() {
		assertEquals(5, Task.find.findRowCount());
	}
	
	@Test
	public void testAllTasksFromAnUser() {
		List<Task> bobsTasks = Task.findTodoInvolving("bob@example.com");
        assertEquals(4, bobsTasks.size());
	}

}
