package models;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;

import java.util.List;

import org.junit.Before;

import play.db.ebean.Model;
import play.libs.Yaml;
import play.test.WithApplication;

import com.avaje.ebean.Ebean;

/**
 * Abstract Class that set up a fake application with an in memory database.
 * 
 * @author albertoalmagro
 *
 */
public abstract class AbstractModelTest extends WithApplication {

	@Before
	public void setUp() {
		loadTestData();
		startFakeApplicationWithInMemoryDatabase();
	}

	private void startFakeApplicationWithInMemoryDatabase() {
		fakeApplication(inMemoryDatabase());
	}
	
	@SuppressWarnings("rawtypes")
	private void loadTestData() {
		Ebean.save((List) Yaml.load("test-data.yml"));
	}

}
