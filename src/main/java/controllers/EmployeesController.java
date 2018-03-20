package controllers;

import db.DBHelper;
import db.Seeds;
import models.Employee;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;


public class EmployeesController {

//    MAIN method required in the Controller that LAUNCHES the app

    public static void main(String[] args) {

        staticFileLocation("/public");

        ManagersController managersController = new ManagersController();
        EngineersController engineersController = new EngineersController();

        get("/employees", (req, res) -> {

            Seeds.seedData();

            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/employees/index.vtl");

            List<Employee> employees = DBHelper.getAll(Employee.class);
            model.put("employees", employees);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

    }

}
