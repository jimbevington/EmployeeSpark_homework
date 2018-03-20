package db;

import models.Department;
import models.Employee;
import models.Engineer;
import models.Manager;

public class Seeds {

    public static void seedData(){

        DBHelper.deleteAll(Employee.class);
        DBHelper.deleteAll(Department.class);

        Department department1 = new Department("HR");
        DBHelper.save(department1);
        Department department2 = new Department("IT");
        DBHelper.save(department2);
        Department department3 = new Department("Smelting");
        DBHelper.save(department3);
        Department department4 = new Department("Taxidermy");
        DBHelper.save(department4);

        Manager manager1 = new Manager("Peter", "Griffin", 40000,department1, 100000);
        DBHelper.save(manager1);
        Manager manager2 = new Manager("Randolph", "Hearst", 5600000,department4, 6000000);
        DBHelper.save(manager2);


        Engineer engineer1 = new Engineer("Lois", "Griffin", 29000, department1);
        DBHelper.save(engineer1);
        Engineer engineer2 = new Engineer("Stewie", "Griffin", 27000, department1);
        DBHelper.save(engineer2);
        Engineer engineer3 = new Engineer("Manfred", "Moonface", 27000, department2);
        DBHelper.save(engineer3);
        Engineer engineer4 = new Engineer("Alexander Graham", "Bell", 27000, department3);
        DBHelper.save(engineer4);
        Engineer engineer5 = new Engineer("Isambard Kingdom", "Brunel", 27000, department3);
        DBHelper.save(engineer5);
        Engineer engineer6 = new Engineer("Jony", "Ive", 4, department4);
        DBHelper.save(engineer6);

    }
}
