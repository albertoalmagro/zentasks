package models;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeGlobal;
import static play.test.Helpers.inMemoryDatabase;

import org.junit.Before;

import play.test.WithApplication;

/**
 * Abstract Class that set up a fake application with an in memory database.
 * 
 * @author albertoalmagro
 *
 */
public abstract class AbstractModelTest extends WithApplication {

	@Before
	public void setUp() {
		startFakeApplicationWithInMemoryDatabase();
	}

	private void startFakeApplicationWithInMemoryDatabase() {
		fakeApplication(inMemoryDatabase(), fakeGlobal());
	}
	
}
