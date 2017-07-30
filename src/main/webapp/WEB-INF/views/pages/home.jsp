<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <form:form method="post" action="add" commandName="applicant">
                    <div class="modal-body">
                        <fieldset class="form-group">
                            <label for="name">
                                <spring:message code="label.namecirilic"/>
                            </label>
                            <input type="text" class="form-control" id="name" name="name">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="nameTranslit">
                                <spring:message code="label.namelatin"/>
                            </label>
                            <input type="text" class="form-control" id="nameTranslit" name="nameTranslit">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="vacancy">
                                <spring:message code="label.vacancy"/>
                            </label>
                            <input type="text" class="form-control" id="vacancy" name="vacancy">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="contact">
                                <spring:message code="label.contact"/>
                            </label>
                            <input type="text" class="form-control" id="contact" name="contact">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="recruiter">
                                <spring:message code="label.recruiter"/>
                            </label>
                            <input type="text" class="form-control" id="recruiter" name="recruiter">
                        </fieldset>
                        <fieldset class="form-group">
                            <label for="comment">
                                <spring:message code="label.commentary"/>
                            </label>
                            <textarea class="form-control" id="comment" name="comment"></textarea>
                        </fieldset>
                        <div class="form-check">
                            <label for="invitationRecieved" class="form-check-label">
                                <input type="checkbox" class="form-check-input" id="invitationRecieved" name="invitationRecieved">
                                <spring:message code="label.invitationrecieved"/>
                            </label>
                        </div>
                        <input type="text" id="id" name="id" style="visibility: hidden; height: 0px;font-size: 0;">
                        <input type="text" id="registrationDate" name="registrationDate" style="visibility: hidden; height: 0px;font-size: 0;">
                        <input type="text" id="dateOfReceivingInvitation" name="dateOfReceivingInvitation" style="visibility: hidden; height: 0px;font-size: 0;">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" onclick="deleteApplicant(this)"><spring:message code="label.delete"/></button>
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
                    <a class="nav-link active" data-toggle="modal" href="#applicantModalWindow">New item <span
                            class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <label for="invitationRecieved" class="form-check-label">
                        <input type="checkbox" class="form-check-input" id="invitationRecievedFilter" name="invitationRecieved">
                        <spring:message code="label.invitationrecieved"/>
                    </label>

                </li>
            </ul>

            <ul class="nav nav-pills flex-column">

            </ul>

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

