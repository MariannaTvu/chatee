package com.bvan.chatee.presentation.messaging;

import com.bvan.chatee.service.messaging.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bvanchuhov
 */
@WebServlet(urlPatterns = "/chatroom")
public class ShowChatRoom extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Message> messages = new ArrayList<>();

        messages.add(new Message(10, "I love Mariana)***").setCreationTime(System.currentTimeMillis()));
        messages.add(new Message(10, "Do you love me?").setCreationTime(System.currentTimeMillis()));
        messages.add(new Message(0, "I love you, Bohdan").setCreationTime(System.currentTimeMillis()));

        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/WEB-INF/ChatRoom.jsp").forward(req, resp);
    }
}
