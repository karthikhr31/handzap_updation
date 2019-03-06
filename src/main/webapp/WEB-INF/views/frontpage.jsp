<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>ARTICLE SEARCH</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">

<style>
.mycolor1
{
	background:#8bc34a !important;
}
.mycolor2
{
	background:#ff9800 !important;
}
.mycolor3
{
	background:#e91e63  !important;
}
.mycolor4
{
	background:#2196f3  !important;
}
.mycolor5
{
	background:#f44336  !important;
}
.mycolor6
{
	background:#607d8b !important;
}
.mycolor7
{
	background:#cddc39  !important;
}
.mycolor8
{
	background:#9c27b0  !important;
}
.iconColor
{
	color:#FFF;
}
body
{
	background-color:lightblue;
}
h3
{
	color:#e91e63;

</style>
</head>
<body>
	<div class="mdl-grid">
		<div class="mdl-cell mdl-cell--4-col"></div>
		<div class="mdl-cell mdl-cell--4-col">
			<h3>Article Search</h3>
			<c:url var="getAuthors" value="/handzap/getAuthors" />
			<form:form id="all_form" modelAttribute="attr1" method="GET"
				action="${getAuthors}">
				<div
					class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
					<input class="mdl-textfield__input" type="text" id="search1">
					<label class="mdl-textfield__label" for="search1">available
						authors</label>
				</div>
				<button
					class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mycolor1">
					<i class="material-icons iconColor">search</i>
				</button>
			</form:form>
			<c:url var="searchAuthors" value="/handzap/searchAuthors" />
			<form:form id="author_form" modelAttribute="attr2" method="POST"
				action="${searchAuthors}">
				<div
					class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
					<form:input id="author_name" path="author"
						class="mdl-textfield__input" />
					<label class="mdl-textfield__label" for="search2">articles
						based on input author name</label>
				</div>
				<button
					class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mycolor2">
					<i class="material-icons">search</i>
				</button>
			</form:form>
			<c:url var="searchTitle" value="/handzap/searchTitle" />
			<form:form id="title_form" modelAttribute="attr3" method="POST"
				action="${searchTitle}">
				<div
					class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
					<form:input id="title" path="title" class="mdl-textfield__input" />
					<label class="mdl-textfield__label" for="search3">article
						based on input article title & description</label>
				</div>
				<button
					class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mycolor3">
					<i class="material-icons iconColor">search</i>
				</button>
			</form:form>
			<c:url var="searchDate" value="/handzap/searchDate" />
			<form:form id="date_form" modelAttribute="attr4" method="POST"
				action="${searchDate}">
				<div
					class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
					<form:input id="date" path="date" class="mdl-textfield__input" />
					<label class="mdl-textfield__label" for="search4">articles
						based on date(YYY-MM-DD)</label>
				</div>
				<button
					class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mycolor4">
					<i class="material-icons">search</i>
				</button>
			</form:form>
			<c:url var="searchCity" value="/handzap/searchCity" />
			<form:form id="city_form" modelAttribute="attr5" method="POST"
				action="${searchCity}">
				<div
					class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
					<form:input id="city" path="city" class="mdl-textfield__input" />
					<label class="mdl-textfield__label" for="search7">articles
						based on city name</label>
				</div>
				<button
					class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mycolor7">
					<i class="material-icons iconColor">search</i>
				</button>
			</form:form>
			<c:url var="getAuthorsOnDate" value="/handzap/getAuthorsOnDate" />
			<form:form id="authordate_form" modelAttribute="attr6" method="POST"
				action="${getAuthorsOnDate}">
				<div
					class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
					<form:input id="date" path="date" class="mdl-textfield__input" />
					<label class="mdl-textfield__label" for="search5">author
						name based on date(YYY-MM-DD)</label>
				</div>
				<button
					class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mycolor5">
					<i class="material-icons iconColor">search</i>
				</button>
			</form:form>
			<c:url var="categoryList" value="/handzap/categoryList" />
			<form:form id="category_form" modelAttribute="attr7" method="POST"
				action="${categoryList}">
				<div
					class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
					<form:input id="category" path="category"
						class="mdl-textfield__input" />
					<label class="mdl-textfield__label" for="search8">articles
						based on new categories</label>
				</div>
				<button
					class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mycolor8">
					<i class="material-icons">search</i>
				</button>
			</form:form>
			<c:url var="tagList" value="/handzap/tagList" />
			<form:form id="tag_form" modelAttribute="attr8" method="POST"
				action="${tagList}">
				<div
					class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
					<form:input id="tags" path="tags" class="mdl-textfield__input" />
					<label class="mdl-textfield__label" for="search6">articles
						based on tags associated with articles</label>
				</div>
				<button
					class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mycolor6">
					<i class="material-icons">search</i>
				</button>
			</form:form>
		</div>
		<div class="mdl-cell mdl-cell--4-col"></div>
	</div>
	<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
</body>
</html>
