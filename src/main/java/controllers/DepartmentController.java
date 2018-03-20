package controllers;

import db.DBHelper;
import models.Department;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;

public class DepartmentController {

    public DepartmentController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

//        view all
        get("/departments", (req, res) -> {

            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/departments/index.vtl");

            List<Department> departments = DBHelper.getAll(Department.class);
            model.put("departments", departments);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        //    create new


        //    save new

    }


}