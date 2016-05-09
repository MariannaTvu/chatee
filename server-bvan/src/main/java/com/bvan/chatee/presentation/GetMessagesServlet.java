package com.bvan.chatee.presentation;

import com.bvan.chatee.service.messaging.Conversation;
import com.bvan.chatee.service.messaging.Message;
import com.bvan.chatee.service.messaging.MessagingService;
import com.bvan.chatee.service.messaging.exception.ConversationNotFoundException;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Maryana on 09.05.2016.
 */
@WebServlet(urlPatterns = "/messages")
public class GetMessagesServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinkUserToConversationServlet.class);
    private static final String PARAM_CONVERSATION_ID = "conversationId";

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

        try {
            Conversation conversation = messagingService.getConversationById(conversationId);
            List<Message> messages = conversation.getMessages();
            String jsonMessages = new Gson().toJson(messages);
            resp.getWriter().println(jsonMessages);
        } catch (ConversationNotFoundException e) {
            LOGGER.debug("conversation " + conversationId + " not found", e);
        }
    }
}
