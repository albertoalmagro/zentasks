package models;

import java.util.List;

import org.junit.Before;

import play.libs.Yaml;
import play.test.WithApplication;

import static play.test.Helpers.*;

import com.avaje.ebean.Ebean;

public class LoginTest extends WithApplication {
	
	@Before
    public void setUp() {
        fakeApplication(inMemoryDatabase(), fakeGlobal());
        Ebean.save((List) Yaml.load("test-data.yml"));
    }

}
