package vn.self.training.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import vn.self.training.hibernate.model.Employee;
import vn.self.training.hibernate.model.Project;
import vn.self.training.hibernate.service.AppService;
import vn.self.training.hibernate.util.DateTimeUtil;
import vn.self.training.hibernate.util.HibernateUtil;

import java.text.ParseException;
import java.util.List;

public class App {
    public static void main(String[] args) {
//        List<Project> projects = AppService.findAll();
//        System.out.println(projects);

//        testState();

        System.exit(0);
    }

    public static void testState() {
        Session sess = HibernateUtil.getSessionFactory().openSession();
        // Transient state
        Employee emp = new Employee();
        emp.setCode("VVD");
        emp.setFirstName("Van");
        emp.setLastName("Duong");
        try {
            emp.setBirthDate(DateTimeUtil.parseStringToDate("10.10.1990"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Transaction tx = sess.beginTransaction();
        // Going to Persistent State
        sess.persist(emp);
        emp.setFirstName("Quoc Tuan");

        System.out.println("Done!");

        tx.commit();
        sess.close();
        // Detatched state
        // sess.evict(emp);
        // sess.clear();
        emp.setLastName("Chan");

        // Reattach object to new session
        Session sess2 = HibernateUtil.getSessionFactory().openSession();
        tx = sess2.beginTransaction();
        sess2.update(emp);
        tx.commit();
        sess2.close();
        // Go back transient state
        sess.delete(emp);
    }
}
