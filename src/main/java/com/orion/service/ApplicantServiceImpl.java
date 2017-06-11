package com.orion.service;

import com.orion.dao.ApplicantDAO;
import com.orion.model.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    private ApplicantDAO applicantDAO;

    @Transactional
    public void addApplicant(Applicant applicant) {
        Integer id = applicant.getId();
        if(!applicantExist(id)){
            applicant.setRegistrationDate(new Date());
        }else if(applicant.isInvitationRecieved() && !isInvitationRecieved(id)){
            applicant.setDateOfReceivingInvitation(new Date());
        }
        applicantDAO.addApplicant(applicant);
    }

    @Transactional
    public void removeApplicant(Integer id) {
        applicantDAO.removeApplicant(id);
    }

    @Transactional
    public List<Applicant> listApplicant() {
        return applicantDAO.listApplicant();
    }

    @Override
    public Applicant getApplicantById(Integer id) {
        if(id==null) return null;
        return applicantDAO.getApplicantById(id);
    }

    public boolean isInvitationRecieved(Integer id){
        return getApplicantById(id).isInvitationRecieved();
    }

    public boolean applicantExist(Integer id){
        return getApplicantById(id)!=null;
    }
}
