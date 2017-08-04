package com.orion.service;

import com.orion.dao.ApplicantDAO;
import com.orion.model.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    private ApplicantDAO applicantDAO;


    public ApplicantServiceImpl() {
        TimeZone.setDefault(TimeZone.getTimeZone("EEST"));
    }

    @Transactional
    public void addApplicant(Applicant applicant) {
        Integer id = applicant.getId();
        Date currentDate = new Date();

        if(!applicantExist(id)){
            applicant.setRegistrationDate(currentDate);
            if(applicant.getInvitationRecieved())
                applicant.setDateOfReceivingInvitation(currentDate);
            applicantDAO.addApplicant(applicant);
            return;
        }

        if(applicant.getInvitationRecieved() && !isInvitationRecieved(id)){
            applicant.setDateOfReceivingInvitation(currentDate);
        }

        applicantDAO.updateApplicant(applicant);
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
        return getApplicantById(id).getInvitationRecieved();
    }

    public boolean applicantExist(Integer id){
        return getApplicantById(id)!=null;
    }
}
