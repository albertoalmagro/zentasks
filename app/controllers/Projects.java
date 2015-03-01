package controllers;

import static play.data.Form.form;
import play.mvc.*;
import views.html.projects.*;
import models.*;

@Security.Authenticated(Secured.class)
public class Projects extends Controller {
	
	public static Result add() {
		Project newProject = Project.create(
				"New project",
				form().bindFromRequest().get("group"),
				request().username()
			);
		return ok(item.render(newProject));
	}

}
