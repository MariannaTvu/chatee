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
            <c:forEach var="message" items="${messages}">
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

    <div class="userIdRequest" align="right">
        <textarea placeholder="Enter id to join user" rows="1"></textarea>
        <button>Join</button>
    </div>

    <div class="chat-message">
        <textarea placeholder="Type your message" rows="7"></textarea>
        <button>Send</button>
    </div>

</div>

</body>
</html>
