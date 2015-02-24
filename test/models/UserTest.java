package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Unit tests for User Entity.
 * 
 * @author albertoalmagro
 *
 */
public class UserTest extends AbstractModelTest {

	/**
	 * Tests create and retrieve a user.
	 */
	@Test
	public void createAndRetrieveUser() {
		new User("bob@gmail.com", "Bob", "secret").save();
		User bob = User.find.where().eq("email", "bob@gmail.com").findUnique();
		assertNotNull(bob);
		assertEquals("Bob", bob.name);
	}
	
	@Test
	public void tryAuthenticateUser() {
		new User("bob@gmail.com", "Bob", "secret").save();
		assertNotNull(User.authenticate("bob@gmail.com", "secret"));
		assertNull(User.authenticate("bob@gmail.com", "badpassword"));
		assertNull(User.authenticate("tom@gmail.com", "secret"));
	}
	
	@Test
	public void testAuthenticateUserFromLoadedContent() {
		assertNotNull(User.authenticate("bob@example.com", "secret"));
        assertNotNull(User.authenticate("jane@example.com", "secret"));
        assertNull(User.authenticate("jeff@example.com", "badpassword"));
        assertNull(User.authenticate("tom@example.com", "secret"));
	}
	
	@Test
	public void testTotalUsers() {
		assertEquals(3, User.find.findRowCount());
	}

}
