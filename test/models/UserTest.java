package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Unit tests for User Entity.
 * 
 * @author albertoalmagro
 *
 */
public class UserTest extends ModelsTest {

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

}
