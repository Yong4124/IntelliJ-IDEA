<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/board/save">
    <input type="hidden" name="id" value="${form.id}">
    제목: <input type="text" name="title" value="${firm.title}"><br/>
    내용: <textarea name="content">${form.content}</textarea>
    <button type="submit">등록</button>
</form>
<br/>
<table>
    <tr><th>번호</th><th>제목</th><th>작성일</th><th>액션</th></tr>
    <c:forEach var="item" items="${list}">
        <tr>
            <td>${item.id}</td>
            <td>${item.title}</td>
            <td>${item.content}</td>
            <td>${item.createdAt}</td>
            <td>
                <a href="/board?editId=${item.id}">수정</a>
                <a href="/board/delete/${item.id}" oneclick="return confirm('정말 삭제할까요?')">삭제</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
