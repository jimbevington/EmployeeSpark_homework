package controllers;

import db.DBHelper;
import models.Department;
import models.Manager;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import sun.security.pkcs11.Secmod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;


public class ManagersController {


    public ManagersController() {
        this.setupEndpoints();  // call this method when instantiated
    }


    private void setupEndpoints(){

//        VIEW ALL
        get("/managers", (req, res) -> {

            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/managers/index.vtl");

            List<Manager> managers = DBHelper.getAll(Manager.class);
            model.put("managers", managers);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


//      CREATE NEW
        get("/managers/new", (req, res) -> {

            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/managers/create.vtl");

            List<Department> departments = DBHelper.getAll(Department.class);
            model.put("departments", departments);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


//        SAVE NEW
        post("/managers", (req, res) -> {

//            get the values to make the Manager obj
            String firstName = req.queryParams("firstName");
            String lastName = req.queryParams("lastName");
            int salary = Integer.parseInt(req.queryParams("salary"));
            double budget = Double.parseDouble(req.queryParams("budget"));

//            get Department id to FIND the specific department
            int departmentId = Integer.parseInt(req.queryParams("department"));
            Department department = DBHelper.find(departmentId, Department.class);

//            create the manager & save it
            Manager manager = new Manager(firstName, lastName, salary, department, budget);
            DBHelper.save(manager);

            res.redirect("/managers");
            return null;

        }, new VelocityTemplateEngine());


//        update
        get("/managers/update/:managerId", (req, res) -> {

            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/managers/update.vtl");

            int managerId = Integer.parseInt(req.params(":managerId"));
            Manager manager = DBHelper.find(managerId, Manager.class);
            model.put("manager", manager);

            List<Department> departments = DBHelper.getAll(Department.class);
            model.put("departments", departments);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

//        save update
        post("/managers/update", (req, res) -> {

//            assign form info to parameters
            int id = Integer.parseInt(req.queryParams("id"));
            String firstName = req.queryParams("firstName");
            String lastName = req.queryParams("lastName");
            int salary = Integer.parseInt(req.queryParams("salary"));
            double budget = Double.parseDouble(req.queryParams("budget"));

//            get Department id to FIND the specific department
            int departmentId = Integer.parseInt(req.queryParams("department"));
            Department department = DBHelper.find(departmentId, Department.class);

//          get the manager from DB, set properties, then save
            Manager manager = DBHelper.find(id, Manager.class);
            manager.setFirstName(firstName);
            manager.setLastName(lastName);
            manager.setSalary(salary);
            manager.setBudget(budget);
            DBHelper.save(manager);

            res.redirect("/managers");
            return null;

        }, new VelocityTemplateEngine());


//        & delete
    }
}
