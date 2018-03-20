package controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.tools.internal.xjc.model.Model;
import db.DBHelper;
import models.Department;
import models.Engineer;
import org.dom4j.rule.Mode;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;

public class EngineersController {

    public EngineersController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

//        view all
        get("/engineers", (req, res) -> {

            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/engineers/index.vtl");

            List<Engineer> engineers = DBHelper.getAll(Engineer.class);
            model.put("engineers", engineers);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

//        create new
        get("/engineers/new", (req, res) -> {

            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/engineers/create.vtl");

            List<Department> departments = DBHelper.getAll(Department.class);
            model.put("departments", departments);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


//        save new


    }


}
