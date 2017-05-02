package com.orion.dao;

import com.orion.model.Applicant;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ApplicantDAOImpl implements ApplicantDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addApplicant(Applicant applicant) {
        sessionFactory.getCurrentSession().save(applicant);
    }

    public void removeApplicant(Integer id) {
        Applicant applicant = (Applicant) sessionFactory.getCurrentSession().load(
                Applicant.class, id);
        if (null != applicant) {
            sessionFactory.getCurrentSession().delete(applicant);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Applicant> listApplicant() {
        return sessionFactory.getCurrentSession().createQuery("from Applicant")
                .list();
    }
}
