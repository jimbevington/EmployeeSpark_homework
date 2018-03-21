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
import static spark.Spark.post;
import static spark.Spark.delete;

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
        post("/engineers", (req, res) -> {

            String firstName = req.queryParams("firstName");
            String lastName = req.queryParams("lastName");
            int salary = Integer.parseInt(req.queryParams("salary"));

            int departmentId = Integer.parseInt(req.queryParams("department"));
            Department department = DBHelper.find(departmentId, Department.class);

            Engineer engineer = new Engineer(firstName, lastName, salary, department);
            DBHelper.save(engineer);

            res.redirect("/engineers");
            return null;

        }, new VelocityTemplateEngine());


        get("/engineers/update/:engineerId", (req, res) -> {

            int engineerId = Integer.parseInt(req.params(":engineerId"));

            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/engineers/update.vtl");

            Engineer engineer = DBHelper.find(engineerId, Engineer.class);
            model.put("engineer", engineer);

            List<Department> departments = DBHelper.getAll(Department.class);
            model.put("departments", departments);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        post("/engineers/update", (req, res) -> {

            int id = Integer.parseInt(req.queryParams("id"));
            String firstName = req.queryParams("firstName");
            String lastName = req.queryParams("lastName");
            int salary = Integer.parseInt(req.queryParams("salary"));
            int departmentId = Integer.parseInt(req.queryParams("department"));

            Department department = DBHelper.find(departmentId, Department.class);

            Engineer engineer = DBHelper.find(id, Engineer.class);
            engineer.setFirstName(firstName);
            engineer.setLastName(lastName);
            engineer.setSalary(salary);
            engineer.setDepartment(department);
            DBHelper.save(engineer);

            res.redirect("/engineers");
            return null;
        }, new VelocityTemplateEngine());


//        get("/engineers/delete/:engineerId", (req, res) -> {
//
//            int id = Integer.parseInt(req.params(":engineerid"));
//
//            Engineer engineer = DBHelper.find(id, Engineer.class);
//            DBHelper.delete(engineer);
//
//            res.redirect("/engineers");
//            return null;
//        }, new VelocityTemplateEngine());

        post("/engineers/delete", (req, res) -> {

            int id = Integer.parseInt(req.queryParams("id"));

            Engineer engineer = DBHelper.find(id, Engineer.class);
            DBHelper.delete(engineer);

            res.redirect("/engineers");
            return null;
        }, new VelocityTemplateEngine());

    }


}
