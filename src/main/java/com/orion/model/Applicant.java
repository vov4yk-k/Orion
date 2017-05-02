package com.orion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "APPLICANTS")
public class Applicant {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "NAMECIRILIC")
    private String name;

    @Column(name = "NAMELATIN")
    private String nameTranslit;

    @Column(name = "REGISTRATIONDATE")
    private Date registrationDate;

    @Column(name = "VACANCY")
    private String vacancy;

    @Column(name = "INVITATIONRECIEVED")
    private boolean invitationRecieved;

    @Column(name = "DATEOFRECEIVINGINVITATION")
    private Date dateOfReceivingInvitation;

    @Column(name = "COMMENTARY")
    private String comment;

    @Column(name = "CONTACT")
    private String contact;

    @Column(name = "RECRUITER")
    private String recruiter;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameTranslit() {
        return nameTranslit;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public String getVacancy() {
        return vacancy;
    }

    public boolean isInvitationRecieved() {
        return invitationRecieved;
    }

    public Date getDateOfReceivingInvitation() {
        return dateOfReceivingInvitation;
    }

    public String getComment() {
        return comment;
    }

    public String getContact() {
        return contact;
    }

    public String getRecruiter() {
        return recruiter;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameTranslit(String nameTranslit) {
        this.nameTranslit = nameTranslit;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }

    public void setInvitationRecieved(boolean invitationRecieved) {
        this.invitationRecieved = invitationRecieved;
    }

    public void setDateOfReceivingInvitation(Date dateOfReceivingInvitation) {
        this.dateOfReceivingInvitation = dateOfReceivingInvitation;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setRecruiter(String recruiter) {
        this.recruiter = recruiter;
    }
}
