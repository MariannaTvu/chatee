package com.bvan.chatee.presentation;

import com.bvan.chatee.service.messaging.MessagingService;
import com.bvan.chatee.service.messaging.exception.ConversationNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author bvanchuhov
 */
@WebServlet(urlPatterns = "/conversation/link_to_conversation")
public class LinkUserToConversationServlet extends HttpServlet {

    private static final String PARAM_CONVERSATION_ID = "conversationId";
    private static final String PARAM_SENDER_ID = "userId";

    private MessagingService messagingService = MessagingService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    private void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int conversationId = Integer.parseInt(req.getParameter(PARAM_CONVERSATION_ID));
        int userId = Integer.parseInt(req.getParameter(PARAM_SENDER_ID));

        try {
            messagingService.linkUserToConversation(conversationId, userId);
        } catch (ConversationNotFoundException e) {
            e.printStackTrace();
        }
    }
}
