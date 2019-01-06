package vn.self.training.hibernate.service;

import lombok.NoArgsConstructor;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import vn.self.training.hibernate.model.Project;
import vn.self.training.hibernate.util.HibernateUtil;

import java.util.List;

@NoArgsConstructor
public class AppService {
    public static List<Project> findAll() {
        List<Project> projects;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Criteria criteria = session.createCriteria(Project.class);
            projects = criteria.list();

            for (Project p : projects) {
                Hibernate.initialize(p.getEmployees());
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return projects;
    }
}
