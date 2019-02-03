<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Deloitte TODO - TODO">
	<meta name="author" content="Alan Kavanagh">
	<title>TODO</title>
	<link href="${contextPath}/resources/css/todo.css" rel="stylesheet">
</head>

<body>
	<div class="container">
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form id="logoutForm" method="POST" action="${contextPath}/logout">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
			<h2> Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a> </h2>
		</c:if>
	</div>

	<div class="container">
		<section id="todoapp">
			<header id="header">
				<h1>todo tasks</h1>
				<form action="<c:url value="insert"/>" method="POST">
					<input type="hidden" name="filter" value="${filter}" /> 
					<input id="new-todo" name="desc" placeholder="What needs to be done?" autofocus>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</header>
			<section id="main">
				<input id="toggle-all" type="checkbox"> 
				<label for="toggle-all">Mark all as complete</label>
				<ul id="todo-list">
					<c:forEach var="task" items="${tasks}" varStatus="status">
						<li id="task_${status.count}" class="<c:if test="${task.isCompleted}">completed</c:if>" ondblclick="javascript:document.getElementById('task_${status.count}').className += ' editing';document.getElementById('taskDesc_${status.count}').focus();">
							<div class="view">
								<form id="toggleForm_${status.count}" action="<c:url value="toggleStatus"/>" method="POST">
									<input type="hidden" name="id" value="${task.id}" /> 
									<input type="hidden" name="filter" value="${filter}" />
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<input class="toggle" name="toggle" type="checkbox" <c:if test="${task.isCompleted}">checked</c:if> onchange="javascript:document.getElementById('toggleForm_${status.count}').submit();">
								</form>
								<label>${task.desc}&emsp;&emsp;&emsp;&emsp;&emsp;-&emsp;&emsp;&emsp;&emsp;&emsp;${task.lastUpdDt}</label>
								<form action="<c:url value="delete"/>" method="POST">
									<input type="hidden" name="id" value="${task.id}" /> 
									<input type="hidden" name="filter" value="${filter}" />
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<button class="destroy"></button>
								</form>
							</div>
							<form id="updateForm_${status.count}" action="<c:url value="update"/>" method="POST">
								<input type="hidden" name="id" value="${task.id}" /> 
								<input type="hidden" name="filter" value="${filter}" />
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								<input class="edit" id="taskDesc_${status.count}" name="desc" value="${task.desc}" onblur="javascript:document.getElementById('updateForm_${status.count}').submit();" />
							</form>
						</li>
					</c:forEach>
				</ul>
			</section>
			<footer id="footer">
				<c:if test="${stats.all >= 0}">
					<span id="todo-count"><strong><c:out value="${stats.active}" /></strong> 
					<c:choose> 
					<c:when test="${stats.active == 1}"> item </c:when>
					<c:otherwise> items </c:otherwise>
					</c:choose> left</span> 
					<ul id="filters">
					<li><a <c:if test="${filter == 'all'}">class="selected"</c:if> href="<c:url value="/all"/>">All</a></li>
					<li><a <c:if test="${filter == 'active'}">class="selected"</c:if> href="<c:url value="active"/>">Active</a></li>
					<li><a <c:if test="${filter == 'completed'}">class="selected"</c:if> href="<c:url value="completed"/>">Completed</a></li>
					</ul>
					<c:if test="${stats.completed > 0}">
						<form action="<c:url value="clearCompleted"/>" method="POST">
							<input type="hidden" name="filter" value="${filter}" />
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<button id="clear-completed"> Clear completed ( <c:out value="${stats.completed}" /> ) </button>
						</form>
					</c:if>
				</c:if>
			</footer>
		</section>
		<div id="info">
			<p>Double-click to edit a task</p>
		</div>
	</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
