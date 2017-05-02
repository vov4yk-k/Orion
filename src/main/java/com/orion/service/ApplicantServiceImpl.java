package com.orion.service;

import com.orion.dao.ApplicantDAO;
import com.orion.model.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    private ApplicantDAO applicantDAO;

    @Transactional
    public void addApplicant(Applicant applicant) {
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
}
