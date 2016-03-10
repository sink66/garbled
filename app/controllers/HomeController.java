package controllers;

import javax.inject.Inject;

import models.form.TestForm;
import play.*;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;

import views.html.*;

public class HomeController extends Controller {
	
	@Inject
	FormFactory formFactory;

    public Result index() {
        return ok(index.render(formFactory.form(TestForm.class)));
    }
    
    public Result show() {
    	
    	//　日本語が文字化けします
    	play.Logger.info(request().body().asFormUrlEncoded().get("name")[0]);
    	
		Form<TestForm> reqData = formFactory.form(TestForm.class).bindFromRequest();
		if (reqData.hasErrors()) {
			return badRequest();
		}
		
		// 日本語が文字化けします
		play.Logger.info(reqData.get().name);
		
        return ok(show.render(reqData.get()));
    }

}
