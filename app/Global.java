import java.util.List;

import models.User;
import play.Application;
import play.GlobalSettings;
import play.libs.Yaml;

import com.avaje.ebean.Ebean;


public class Global extends GlobalSettings {
	
	@Override
	public void onStart(Application arg0) {
        if (isDatabaseEmpty()) {
            loadInitialData();
        }
	}
	
	private boolean isDatabaseEmpty() {
		return User.find.findRowCount() == 0;
	}

	@SuppressWarnings("rawtypes")
	private void loadInitialData() {
		Ebean.save((List) Yaml.load("test-data.yml"));
	}

}
