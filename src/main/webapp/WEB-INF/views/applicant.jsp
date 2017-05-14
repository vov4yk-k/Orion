<%--
  Created by IntelliJ IDEA.
  User: Vova
  Date: 23.04.2017
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%request.setCharacterEncoding("UTF-8");%>
<fmt:requestEncoding value="utf-8"/>
<html lang="ua">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="http://orionwork.eu/wp-content/uploads/2016/08/cropped-favicon-32x32.png">

    <title>Orion</title>

    <!-- Bootstrap core CSS -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/resources/css/dashboard.css" rel="stylesheet">
    <link href="/resources/css/tables.css" rel="stylesheet">
    <link href="/resources/css/jquery.dataTables.min.css" rel="stylesheet">


<body>


<nav class="navbar navbar-toggleable-md navbar-inverse fixed-top bg-inverse">
    <button class="navbar-toggler navbar-toggler-right hidden-lg-up" type="button" data-toggle="collapse"
            data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="https://v4-alpha.getbootstrap.com/examples/dashboard/#">Dashboard</a>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="https://v4-alpha.getbootstrap.com/examples/dashboard/#">Home <span
                        class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="https://v4-alpha.getbootstrap.com/examples/dashboard/#">Settings</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="https://v4-alpha.getbootstrap.com/examples/dashboard/#">Profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="https://v4-alpha.getbootstrap.com/examples/dashboard/#">Help</a>
            </li>
        </ul>
        <form class="form-inline mt-2 mt-md-0">
            <input class="form-control mr-sm-2" type="text" placeholder="Search"id="text-to-find">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit" onclick="return search('text-to-find')">Search</button>
        </form>
    </div>
</nav>

<div class="container-fluid">

    <!-- The modal -->
    <div class="modal fade" id="applicantModalWindow" tabindex="-1" role="dialog" aria-labelledby="modalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
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
                        <label for="namecirilic">
                            <spring:message code="label.namecirilic"/>
                        </label>
                        <input type="text" class="form-control" id="namecirilic" name="name">
                    </fieldset>
                    <fieldset class="form-group">
                        <label for="namelatin">
                            <spring:message code="label.namelatin"/>
                        </label>
                        <input type="text" class="form-control" id="namelatin" name="nameTranslit">
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
                        <label for="commentary">
                            <spring:message code="label.commentary"/>
                        </label>
                        <textarea class="form-control" id="commentary" name="comment"></textarea>
                    </fieldset>
                    <div class="form-check">
                        <label class="form-check-label">
                            <input type="checkbox" class="form-check-input" name="invitationRecieved">
                            <spring:message code="label.invitationrecieved"/>
                        </label>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger"><spring:message code="label.delete"/></button>
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
            </ul>

            <ul class="nav nav-pills flex-column">
                <!-- will be filters-->
            </ul>

        </nav>

        <main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
            <section class="content-area">
            <div class="table-area">
                <c:if test="${!empty applicantList}">
                    <table class="table-fill" id="mainTable">
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
                            <!--<th class="text-center">&nbsp;</th>-->
                        </tr>
                        </thead>
                        <tbody class="table-hover">
                        <c:forEach items="${applicantList}" var="applicant">
                            <tr>
                                <td class="text-center">${applicant.id}</td>
                                <td class="text-left">${applicant.name}</td>
                                <td class="text-left">${applicant.nameTranslit}</td>
                                <td class="text-center">${applicant.registrationDate}</td>
                                <td class="text-left">${applicant.vacancy}</td>
                                <td class="text-center">${applicant.invitationRecieved}</td>
                                <td class="text-center">${applicant.dateOfReceivingInvitation}</td>
                                <td class="text-left">${applicant.comment}</td>
                                <td class="text-center">${applicant.contact}</td>
                                <td class="text-left">${applicant.recruiter}</td>
                                <!--<td><a href="delete/${applicant.id}"><spring:message code="label.delete" /></a></td>-->
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
                </section>
        </main>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="/resources/js/jquery-3.1.1.slim.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="/resources/js/tether.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/resources/js/ie10-viewport-bug-workaround.js"></script>

<script src="/resources/js/jquery-1.12.4.js"></script>
<script src="/resources/js/jquery.dataTables.min.js"></script>
<script>
    $(document).ready(function() {
        var table = $('#mainTable').DataTable({
            "scrollY":        "75%",
            "scrollX": "70%",
            "scrollCollapse": true,
            "paging":         false,
            "bFilter": false,
            "bInfo": false,
            "searching": true
        });

        $('#text-to-find').on( 'keyup', function () {
            table.search(this.value ).draw();});
    } );
    function search(inputId){
        var obj = document.getElementById(inputId);
        $('#mainTable').DataTable().search(obj.value).draw();
    }
</script>
</body>
</html>
</html>
