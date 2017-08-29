<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" language="java" %>
<div class="container-fluid">

    <!-- The modal -->
    <div class="modal fade" id="applicantModalWindow" tabindex="-1" role="dialog" aria-labelledby="modalLabel"
         aria-hidden="true">
        <div id="modal-dialog" class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title"><spring:message code="label.applicant"/></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form:form method="POST" action="postApplicant" commandName="applicant">
                    <div class="modal-body">
                        <fieldset class="form-group">
                            <label for="name">
                                <spring:message code="label.namecirilic"/>
                            </label>
                            <input type="text" maxlength="50" class="form-control" id="name" name="name">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="nameTranslit">
                                <spring:message code="label.namelatin"/>
                            </label>
                            <input type="text" maxlength="50" class="form-control" id="nameTranslit"
                                   name="nameTranslit">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="vacancy">
                                <spring:message code="label.vacancy"/>
                            </label>
                            <input type="text" maxlength="50" class="form-control" id="vacancy" name="vacancy">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="contact">
                                <spring:message code="label.contact"/>
                            </label>
                            <input type="text" maxlength="50" class="form-control" id="contact" name="contact">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="recruiter">
                                <spring:message code="label.recruiter"/>
                            </label>
                            <input type="text" class="form-control" id="recruiter" name="recruiter"
                                   style="display: none">
                            <div class="jq-selectbox jqselect" style="z-index: 10; width: 100%">
                                <select id="select-recruiter" style="z-index: 10; width: 100%"
                                        onchange="setRecruiter(this)">
                                    <option><spring:message code="label.select"/></option>
                                    <c:forEach items="${recruiters}" var="recruiter">
                                        <option>${recruiter.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="status">
                                <spring:message code="label.status"/>
                            </label>
                            <input type="text" class="form-control" id="status" name="status" style="display: none">
                            <div class="jq-selectbox jqselect" style="z-index: 10; width: 100%">
                                <select id="select-status" style="z-index: 10; width: 100%" onchange="setStatus(this)">
                                    <c:forEach items="${statuses}" var="status">
                                        <option id="${status}"><spring:message code="label.${status}"/></option>
                                    </c:forEach>
                                </select>
                            </div>
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="comment">
                                <spring:message code="label.commentary"/>
                            </label>
                            <textarea class="form-control" maxlength="100" id="comment" name="comment"></textarea>
                        </fieldset>
                        <div class="form-check">
                            <label for="invitationRecieved" class="form-check-label">
                                <input type="checkbox" class="form-check-input" id="invitationRecieved"
                                       name="invitationRecieved">
                                <spring:message code="label.invitationrecieved"/>
                            </label>
                        </div>
                        <input type="text" id="id" name="id" style="display: none">
                        <input type="text" id="registrationDate" name="registrationDate" style="display: none">
                        <input type="text" id="dateOfReceivingInvitation" name="dateOfReceivingInvitation"
                               style="display: none">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" onclick="deleteApplicant(this)"><spring:message
                                code="label.delete"/></button>
                        <button type="submit" class="btn btn-primary"><spring:message code="label.save"/></button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message
                                code="label.close"/></button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>

    <div class="row">
        <nav class="col-sm-3 col-md-2 hidden-xs-down bg-faded sidebar">
            <ul class="nav nav-pills flex-column">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="modal" href="#applicantModalWindow"><spring:message
                            code="label.newapplicant"/><span
                            class="sr-only">(current)</span></a>
                </li>
            </ul>
            <ul class="nav nav-pills flex-column">
            </ul>

            <div class="container">
                <form:form role="form" class="form-horizontal"
                           action="${pageContext.request.contextPath}/setApplicantFilter"
                           method="POST" modelAttribute="applicantFilter" style=" width: 100%; margin: 0 auto;">
                    <div class="form-group">
                        <label class="col-sm-10" for="received"><spring:message code="label.invitationrecieved"/>:</label>
                        <div class="col-sm-10">
                            <label  for="received"><spring:message code="label.yes"/></label>
                            <form:checkbox class="col-sm-1" id="received" path="received" value="${received}"/>
                            <label  for="received"><spring:message code="label.no"/></label>
                            <form:checkbox class="col-sm-1" id="notReceived" path="notReceived" value="${notReceived}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-10"><spring:message code="label.registrationdate"/>: </label>
                        <div class="col-sm-9">
                            <form:input type="date" class="form-control" id="registration0" path="registration0"
                                        value="${registration0}"/>
                            <form:input type="date" class="form-control" id="registration1" path="registration1"
                                        value="${registration1}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-10"><spring:message code="label.dateinvitation"/>: </label>
                        <div class="col-sm-9">
                            <form:input type="date" class="form-control" id="receiving0" path="receiving0"
                                        value="${receiving0}"/>
                            <form:input type="date" class="form-control" id="receiving1" path="receiving1"
                                        value="${receiving1}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="recruiter" class="col-sm-10">
                            <spring:message code="label.recruiter"/>
                        </label>
                        <c:forEach items="${applicantFilter.getRecruitersFilter()}" var="recruiter">
                            <div class="col-sm-10" id="${recruiter.key}">
                                <input type="checkbox" path="recruitersFilter" class="col-sm-1" id="${recruiter.key}" value="${recruiter.value}" name="${recruiter.key}" <c:if test="${recruiter.value}">checked</c:if>/>
                                <form:label path="recruitersFilter" value="${recruiter.value}" name="${recruiter.key}">${recruiter.key}</form:label>
                            </div>
                        </c:forEach>
                    </div>
                    <form:checkbox id="active" path="active"
                                   value="true" checked="checked" style="display: none" />
                    <div class="form-group">
                        <div class="col-sm-10 col-md-offset-3">
                            <button type="submit" class="btn btn-primary" style="background-color:#666B85"><spring:message
                                    code="label.save"/></button>
                            <a class="btn btn-default" href = "${pageContext.request.contextPath}/clearApplicantFilter"><spring:message
                                    code="label.clear"/></a>
                        </div>
                    </div>
                </form:form>
            </div>
        </nav>

        <main id="main" class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
            <table id="applicantsTable" class="display compact" cellspacing="0" width="100%" style="overflow-x:auto">
                <thead>
                <tr>
                    <th class="text-center"><spring:message code="label.ida"/></th>
                    <th class="text-center"><spring:message code="label.namecirilic"/></th>
                    <th class="text-center"><spring:message code="label.namelatin"/></th>
                    <th class="text-center"><spring:message code="label.registrationdate"/></th>
                    <th class="text-center"><spring:message code="label.vacancy"/></th>
                    <th class="text-center"><spring:message code="label.invitationrecieved"/></th>
                    <th class="text-center"><spring:message code="label.dateinvitation"/></th>
                    <th class="text-center"><spring:message code="label.commentary"/></th>
                    <th class="text-center"><spring:message code="label.contact"/></th>
                    <th class="text-center"><spring:message code="label.recruiter"/></th>
                </tr>
                </thead>
            </table>
        </main>
    </div>
</div>

