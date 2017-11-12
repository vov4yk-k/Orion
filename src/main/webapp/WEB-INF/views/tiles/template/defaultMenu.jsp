<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-toggleable-md navbar-inverse fixed-top bg-inverse">
    <button class="navbar-toggler navbar-toggler-right hidden-lg-up" type="button" data-toggle="collapse"
            data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="/profile">${userName}</a>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul id="menu" class="navbar-nav mr-auto">
            <li class="nav-item">
                <a ${'home'.equals(currentItem) ? 'class ="nav-link active" ': 'class = "nav-link" '}
                        href="/index"><spring:message code="label.home"/></a>
            </li>
            <sec:authorize access="hasRole('ADMIN')">
                <li class="nav-item">
                    <a ${'settings'.equals(currentItem) ? 'class ="nav-link active" ': 'class = "nav-link" '}
                            href="/settings"><spring:message code="label.settings"/> </a>
                </li>
            </sec:authorize>
            <li class="nav-item">
                <a ${'profile'.equals(currentItem) ? 'class ="nav-link active" ': 'class = "nav-link" '}
                        href="/profile"><spring:message code="label.profile"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout"><spring:message code="label.logout"/></a>
            </li>
        </ul>
        <form class="form-inline mt-2 mt-md-0">

            <input ${'home'.equals(currentItem) ? '': 'style="display: none" '} class="form-control mr-sm-2" type="text"
                                                                                placeholder=
                                                                                <spring:message
                                                                                        code="label.search"/> id="text-to-find">
            <button ${'settings'.equals(currentItem) ? '': 'style="display: none" '} type="button" class="btn btn-block"
                                                                                     onclick="newUser()"><spring:message
                    code="label.newUser"/></button>
        </form>
    </div>
</nav>
