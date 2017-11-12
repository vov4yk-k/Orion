<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<main style="margin-top:5px">
    <table id="usersTable" class="display compact" cellspacing="0" style="overflow-x:auto">
        <thead>
        <tr>
            <th class="text-center"><spring:message code="label.ida"/></th>
            <th class="text-center"><spring:message code="label.user"/></th>
            <th class="text-center"><spring:message code="label.language"/></th>
            <th class="text-center"><spring:message code="label.enabled"/></th>
            <th class="text-center"><spring:message code="label.group"/></th>
        </tr>
        </thead>
    </table>
</main>

<!-- The modal -->
<div class="modal fade" id="userModalWindow" tabindex="-1" role="dialog" aria-labelledby="modalLabel"
     aria-hidden="true">
    <div id="modal-dialog" class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><spring:message code="label.user"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form:form method="POST" action="postUser" commandName="groupMember">
                <div class="modal-body">
                    <fieldset class="form-group">
                        <label for="username-name">
                            <spring:message code="label.name"/>
                        </label>
                        <input type="text" maxlength="50" class="form-control" id="username-name" name="username.name">
                    </fieldset>
                    <fieldset class="form-group">
                        <label for="username-password">
                            <spring:message code="label.password"/>
                        </label>
                        <input type="text" maxlength="50" class="form-control" id="username-password"
                               name="username.password">
                    </fieldset>
                    <fieldset class="form-group">
                        <label for="username.language"><spring:message code="label.language"/>: </label>
                        <div>
                            <form:input id="language" type="text" class="form-control" path="username.language"
                                        value="${username.language}" name="username.language" style="display: none"/>
                            <div class="jq-selectbox jqselect" style="z-index: 10; width: 100%">
                                <select id="select" style="z-index: 10; width: 100%" onchange="setLanguage(this)">
                                    <c:forEach items="${languages}" var="lang">
                                        <option id="${lang.key}">${lang.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </fieldset>
                    <fieldset class="form-group">
                        <label for="group.group_name"><spring:message code="label.group"/></label>
                        <!--<input type="text" maxlength="50" class="form-control" id="group-group_name"
                               name="group.group_name">-->

                        <form:input id="group_name" type="text" class="form-control" path="group.group_name"
                                    value="${group.group_name}" name="group.group_name" style="display: none"/>

                        <div class="jq-selectbox jqselect" style="z-index: 10; width: 100%">
                            <select id="select-group" style="z-index: 10; width: 100%" onchange="setGroup(this)">
                                <c:forEach items="${groups}" var="gr">
                                    <option id="${gr.id}">${gr.group_name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </fieldset>
                    <div class="form-check">
                        <label for="username-enabled" class="form-check-label">
                            <input type="checkbox" class="form-check-input" id="username-enabled"
                                   name="username.enabled">
                            <spring:message code="label.enabled"/>
                        </label>
                    </div>
                    <input type="text" id="id" name="id" style="display: none">
                    <input type="text" id="group-id" name="group.id" style="display: none">
                    <input type="text" id="username-id" name="username.id" style="display: none">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" onclick="deleteGroupMember(this)"><spring:message
                            code="label.delete"/></button>
                    <button type="submit" class="btn btn-primary"><spring:message code="label.save"/></button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message
                            code="label.close"/></button>
                </div>
            </form:form>
        </div>
    </div>
</div>
