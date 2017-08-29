package com.orion.dao;

import com.orion.model.Applicant;

import java.util.List;

import com.orion.model.ApplicantFilter;
import org.hibernate.Query;
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
        sessionFactory.getCurrentSession().persist(applicant);
    }

    public void updateApplicant(Applicant applicant) {
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
    public List<Applicant> filteredListApplicant(ApplicantFilter af) {

        String recruiters = af.getRecruitersStr();
        String receiving = af.getReceivedStr();

        String hql = "from Applicant A " +
                "WHERE " +
                "(A.registrationDate BETWEEN :_registration0 and :_registration1) " +
                "AND (IFNULL(A.dateOfReceivingInvitation,:_receiving0) BETWEEN :_receiving0 and :_receiving1) " +
                (recruiters.isEmpty() ? "" : "AND A.recruiter IN (" + recruiters + ")") +
                (receiving.isEmpty() ? "" : "AND A.invitationRecieved IN ("+receiving+")");

        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("_registration0",af.getRegistration0());
        query.setParameter("_registration1",af.getRegistration1());
        query.setParameter("_receiving0",af.getReceiving0());
        query.setParameter("_receiving1",af.getReceiving1());

        List results = query.list();
        return results;
    }

    @Override
    public Applicant getApplicantById(Integer id) {
        Applicant applicant = (Applicant) sessionFactory.getCurrentSession().get(Applicant.class, id);
        return applicant;
    }


}
