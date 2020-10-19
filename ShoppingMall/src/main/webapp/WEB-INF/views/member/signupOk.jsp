<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="root" value="${pageContext.request.contextPath }"/>
	<%-- ${root } --%>
	<c:if test="${check>0 }">
		<script type="text/javascript">
			alert("회원가입을 축하합니다! 가입 축하 적립금 2000원을 드립니다");
			location.href="${root}/member/login.do";
		</script>
	</c:if>
	
	<c:if test="${check==0 }">
		<script type="text/javascript">
			alert("회원가입이 되지 않습니다.");
			location.href="${root}/member/join.do";
		</script>
	</c:if>
</body>
</html>