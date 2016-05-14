<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Chatee</title>

</head>
<body>

<div class="chat">

    <div class="messages" align="center">
        <table border="1" width="100%">
            <caption>Messages</caption>
            <tr>
                <th>Sender</th>
                <th>Created</th>
                <th>Text</th>
            </tr>

            <jsp:useBean id="creationDate" class="java.util.Date"/>
            <c:forEach var="message" items="${conversation.messages}">
                <tr>
                    <td>${message.senderId}</td>
                    <td>
                        <jsp:setProperty name="creationDate" property="time" value="${message.creationTime}"/>
                        <fmt:formatDate value="${creationDate}" pattern="dd/MM/yyyy mm:ss"/>
                    </td>
                    <td>${message.text}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <br>
        <br>
        <br>
    </div>

    <form action="/msg/create" method="post">
        <input type="hidden" name="convId" value="${conversation.id}"/>
        <input type="hidden" name="respType" value="html"/>

        <div class="userIdRequest">
            <input type="text" name="userId" placeholder="Your id"></input>
            <%--<button>Join</button>--%>
        </div>

        <div class="chat-message">
            <input type="text" name="text" placeholder="Type your message"></input>
            <button>Send</button>
        </div>
    </form>
</div>

</body>
</html>
