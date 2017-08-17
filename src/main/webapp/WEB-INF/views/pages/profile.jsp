<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<div class = "container">
    <div class = "row">
        <div class = "row content_padding" style = "width: 100%; margin-top: 1%">
            <div class="col-sm-10">
                <h1 class = "page_title_text"><span class = "glyphicon glyphicon-user"></span> <spring:message code="label.edituser"/></h1>
            </div>
        </div>
        <form:form class = "form-horizontal" action="${pageContext.request.contextPath}/prifileUpdate" method = "POST" modelAttribute="user" style=" width: 100%; margin: 0 auto;">
            <div class="form-group">
                <label class="col-sm-3 control-label" for="name"><spring:message code="label.name"/>: </label>
                <div class="col-sm-4">
                    <form:input type = "text" class="form-control" id="name" path="name" value = "${name}" readonly="true"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="password"><spring:message code="label.password"/>: </label>
                <div class="col-sm-4">
                    <form:input type = "password" class="form-control" id="password" path="password" value = "${password}" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="language"><spring:message code="label.language"/>: </label>
                <div class="col-sm-4">
                    <form:input id ="language" type = "text" class="form-control" path="language" value = "${language}" style="display: none" />
                    <div class="jq-selectbox jqselect" style="z-index: 10; width: 100%">
                        <select id="select" style="z-index: 10; width: 100%" onchange="setLanguage(this)">
                            <c:forEach items="${languages}" var="lang">
                                <option id="${lang.key}">${lang.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <form:errors class = "register-user-error" path="language" />
                </div>
            </div>
            <form:input id ="id" type = "number" class="form-control" path="id" value = "${id}" style="display: none" />
            <form:input id ="enabled" type = "number" class="form-control" path="enabled" value = "${enabled}" style="display: none" />

            <div class="form-group">
                <div class="col-sm-10 col-md-offset-3">
                    <button type="submit" class="btn btn-primary"><spring:message code="label.save"/></button>
                    <!--<a class="btn btn-default" href = "${pageContext.request.contextPath}/profile">Cancel</a>-->
                </div>
            </div>
        </form:form>
    </div>
</div>
