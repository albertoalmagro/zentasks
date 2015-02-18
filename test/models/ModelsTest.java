package models;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;

import org.junit.Before;

import play.test.WithApplication;

/**
 * Abstract Class that set up a fake application with an in memory database.
 * 
 * @author albertoalmagro
 *
 */
public abstract class ModelsTest extends WithApplication {

	@Before
	public void setUp() {
		fakeApplication(inMemoryDatabase());
	}

}
