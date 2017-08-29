package com.orion.service;

import com.orion.dao.ApplicantDAO;
import com.orion.model.Applicant;
import com.orion.model.ApplicantFilter;
import com.orion.model.ApplicantStatus;
import com.orion.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    private ApplicantDAO applicantDAO;

    @Autowired
    private ApplicantFilter applicantFilter;

    public ApplicantServiceImpl() {
        TimeZone.setDefault(TimeZone.getTimeZone("EEST"));
    }

    public ApplicantFilter getApplicantFilter() {
        return applicantFilter;
    }

    public void setApplicantFilter(ApplicantFilter applicantFilter) {
        this.applicantFilter.fillFields(applicantFilter);
    }

    @Transactional
    public void addApplicant(Applicant applicant) {
        Integer id = applicant.getId();
        Date currentDate = new Date();

        if(applicant.getStatus() == null){
            applicant.setStatus(ApplicantStatus.NEW);
        }

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
        if(applicantFilter.isActive()){
            return applicantDAO.filteredListApplicant(applicantFilter);
        }else {
            return applicantDAO.listApplicant();
        }
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
