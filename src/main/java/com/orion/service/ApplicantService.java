package com.orion.service;

import com.orion.model.Applicant;
import com.orion.model.ApplicantFilter;
import com.orion.model.User;

import java.util.List;

/**
 * Created by Vova on 22.04.2017.
 */
public interface ApplicantService {

    void addApplicant(Applicant applicant);

    void removeApplicant(Integer id);

    List<Applicant> listApplicant();

    Applicant getApplicantById(Integer id);

    ApplicantFilter getApplicantFilter();

    void setApplicantFilter(ApplicantFilter applicantFilter);
}
