package com.orion.dao;

import com.orion.model.Applicant;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ApplicantDAOImpl implements ApplicantDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addApplicant(Applicant applicant) {
        //sessionFactory.getCurrentSession().saveOrUpdate(applicant);
        sessionFactory.getCurrentSession().merge(applicant);
    }

    public void removeApplicant(Integer id) {
        Applicant applicant = getApplicantById(id);
        if (null != applicant) {
            sessionFactory.getCurrentSession().delete(applicant);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Applicant> listApplicant() {
        return sessionFactory.getCurrentSession().createQuery("from Applicant")
                .list();
    }

    @Override
    public Applicant getApplicantById(Integer id) {
        Applicant applicant = (Applicant) sessionFactory.getCurrentSession().get(Applicant.class, id);
        return applicant;
    }
}
