package models;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;

import play.mvc.Result;
import play.test.Helpers;
import base.AbstractTest;

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertNull; 

public class LoginTest extends AbstractTest {
	
	@Test
	public void authenticateSuccess() {
		Result result = Helpers.callAction(controllers.routes.ref.Application.authenticate(),
				Helpers.fakeRequest().withFormUrlEncodedBody(ImmutableMap.of(
						"email", "bob@example.com",
						"password", "secret")));
		assertEquals(303, Helpers.status(result));
		assertEquals("bob@example.com", Helpers.session(result).get("email"));
	}
	
	@Test
	public void authenticateFailure() {
		Result result = Helpers.callAction(controllers.routes.ref.Application.authenticate(),
				Helpers.fakeRequest().withFormUrlEncodedBody(ImmutableMap.of(
						"email", "bob@example.com",
						"password", "badpassword")));
		assertEquals(400, Helpers.status(result));
		assertNull(Helpers.session(result).get("email"));
	}
	
	@Test
	public void authenticated() {
		Result result = Helpers.callAction(controllers.routes.ref.Application.index(),
				Helpers.fakeRequest().withSession("email", "bob@example.com"));
		assertEquals(200, Helpers.status(result));
	}
	
	@Test
	public void notAuthenticated() {
		Result result = Helpers.callAction(controllers.routes.ref.Application.index(),
				Helpers.fakeRequest());
		assertEquals(303, Helpers.status(result));
		assertEquals("/login", Helpers.header("Location", result));
	}

}
