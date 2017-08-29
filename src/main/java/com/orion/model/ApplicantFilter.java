package com.orion.model;

import com.orion.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Vova on 21.08.2017.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ApplicantFilter{

    private boolean received;
    private boolean notReceived;
    private Date registration0;
    private Date registration1;
    private Date receiving0;
    private Date receiving1;
    private HashMap<String,Boolean> recruitersFilter;
    private boolean active;

    @Autowired
    public ApplicantFilter(UserManagementService userManagementService) {
        this.recruitersFilter = new HashMap<String,Boolean>();
        userManagementService.userList().forEach(recruiter ->{
            this.recruitersFilter.put(recruiter.getName(),false);
        });
        this.registration0 = new Date(117,1,1);
        this.registration1 = new Date();
        this.receiving0 = new Date(117,1,1);
        this.receiving1 = new Date();
    }

    public ApplicantFilter() {
    }

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean invitationReceived) {
        this.received = invitationReceived;
    }

    public Date getRegistration0() {
        return registration0;
    }

    public void setRegistration0(Date registration0) {
        this.registration0 = registration0;
    }

    public Date getRegistration1() {
        return registration1;
    }

    public void setRegistration1(Date registration1) {
        this.registration1 = registration1;
    }

    public Date getReceiving0() {
        return receiving0;
    }

    public void setReceiving0(Date receiving0) {
        this.receiving0 = receiving0;
    }

    public Date getReceiving1() {
        return receiving1;
    }

    public void setReceiving1(Date receiving1) {
        this.receiving1 = receiving1;
    }

    public HashMap<String, Boolean> getRecruitersFilter() {
        return recruitersFilter;
    }

    public void setRecruitersFilter(HashMap<String, Boolean> recruitersFilter) {
        this.recruitersFilter = recruitersFilter;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isNotReceived() {
        return notReceived;
    }

    public void setNotReceived(boolean notReceived) {
        this.notReceived = notReceived;
    }

    public void fillFields(ApplicantFilter applicantFilter){
        this.active = applicantFilter.active;
        this.received = applicantFilter.received;
        this.notReceived = applicantFilter.notReceived;
        this.receiving0 = applicantFilter.receiving0;
        this.receiving1 = applicantFilter.receiving1;
        this.registration0 = applicantFilter.registration0;
        this.registration1 = applicantFilter.registration1;
        applicantFilter.recruitersFilter.forEach((k,v)->{
            this.recruitersFilter.replace(k,v);
        });
    }

    public String getRecruitersStr(){
        StringBuilder stringBuilder = new StringBuilder();
        this.recruitersFilter.forEach((recruiter,checked)->{
            if(checked){
                stringBuilder.append("'");
                stringBuilder.append(recruiter);
                stringBuilder.append("',");
            }
        });
        if(stringBuilder.length()!=0)
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

    public String getReceivedStr(){
        StringBuilder stringBuilder = new StringBuilder();
        if(received){
            stringBuilder.append("'1',");
        }
        if(notReceived){
            stringBuilder.append("'0',");
        }

        if(stringBuilder.length()!=0)
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }
}
