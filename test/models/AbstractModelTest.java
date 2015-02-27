package models;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeGlobal;
import static play.test.Helpers.inMemoryDatabase;

import java.util.List;

import org.junit.Before;

import com.avaje.ebean.Ebean;

import play.libs.Yaml;
import play.test.Helpers;
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
        Helpers.start(fakeApplication(inMemoryDatabase(), fakeGlobal()));
        Ebean.save((List) Yaml.load("test-data.yml"));
    }
	
}
