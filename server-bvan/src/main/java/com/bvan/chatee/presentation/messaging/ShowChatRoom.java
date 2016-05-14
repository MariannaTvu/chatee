package com.bvan.chatee.presentation.messaging;

import com.bvan.chatee.common.Validator;
import com.bvan.chatee.service.messaging.Conversation;
import com.bvan.chatee.service.messaging.Message;
import com.bvan.chatee.service.messaging.MessagingService;
import com.bvan.chatee.service.messaging.exception.ConversationNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.bvan.chatee.common.ChatConstants.Params.PARAM_CONVERSATION_ID;

/**
 * @author bvanchuhov
 */
@WebServlet(urlPatterns = "/chatroom")
public class ShowChatRoom extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramConversationId = req.getParameter(PARAM_CONVERSATION_ID);
        if (!Validator.isInt(paramConversationId)) {
            resp.getWriter().println("Illegal conversation id");
        }

        int conversationId = Integer.parseInt(paramConversationId);

        try {
            Conversation conversation = MessagingService.INSTANCE.getConversationById(conversationId);

            req.setAttribute("conversation", conversation);
            req.getRequestDispatcher("/WEB-INF/ChatRoom.jsp").forward(req, resp);
        } catch (ConversationNotFoundException e) {
            e.printStackTrace();
        }

    }

    private List<Message> createMockMessages() {
        List<Message> messages = new ArrayList<>();

        messages.add(new Message(10, "I love Mariana)***").setCreationTime(System.currentTimeMillis()));
        messages.add(new Message(10, "Do you love me?").setCreationTime(System.currentTimeMillis()));
        messages.add(new Message(0, "I love you, Bohdan").setCreationTime(System.currentTimeMillis()));

        return messages;
    }
}
