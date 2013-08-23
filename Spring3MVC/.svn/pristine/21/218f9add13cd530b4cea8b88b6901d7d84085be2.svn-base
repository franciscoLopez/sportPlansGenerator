<%-- <%@ include file="/WEB-INF/controllers/configuration/includes.jsp"%>
<c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication!=null}">
	<sec:authorize ifAnyGranted="ROLE_SPORT_CENTRE">
		<c:redirect url="/menuSportCentre.do" />
	</sec:authorize>
	<sec:authorize ifAnyGranted="ROLE_ADMIN">
		<c:redirect url="/menuAdmin.do" />
	</sec:authorize>
	<sec:authorize ifAnyGranted="ROLE_SPORT_TRAINER">
		<c:redirect url="/menuSportTrainer.do" />
	</sec:authorize>
</c:if> --%>