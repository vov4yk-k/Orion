package com.orion.service;

import com.orion.model.Applicant;

import java.util.List;

/**
 * Created by Vova on 22.04.2017.
 */
public interface ApplicantService {
    public void addApplicant(Applicant applicant);

    public void removeApplicant(Integer id);

    public List<Applicant> listApplicant();

    public Applicant getApplicantById(Integer id);
}
