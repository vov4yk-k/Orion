<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                <a ${'home'.equals(currentItem) ? 'class ="nav-link active" ': 'class = "nav-link" '} href="/index"><spring:message code="label.home"/></a>
            </li>
            <li class="nav-item">
                <a ${'settings'.equals(currentItem) ? 'class ="nav-link active" ': 'class = "nav-link" '} href="/settings"><spring:message code="label.settings"/> </a>
            </li>
            <li class="nav-item">
                <a ${'profile'.equals(currentItem) ? 'class ="nav-link active" ': 'class = "nav-link" '} href="/profile"><spring:message code="label.profile"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout"><spring:message code="label.logout"/></a>
            </li>
        </ul>
        <form class="form-inline mt-2 mt-md-0">
            <input class="form-control mr-sm-2" type="text" placeholder=<spring:message code="label.search"/> id="text-to-find">
        </form>
    </div>
</nav>
