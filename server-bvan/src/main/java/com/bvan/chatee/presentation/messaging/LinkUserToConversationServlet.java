package com.bvan.chatee.presentation.messaging;

import com.bvan.chatee.service.messaging.Conversation;
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

import static com.bvan.chatee.common.ChatConstants.Params.PARAM_CONVERSATION_ID;
import static com.bvan.chatee.common.ChatConstants.Params.PARAM_USER_ID;

/**
 * @author bvanchuhov
 */
@WebServlet(urlPatterns = "/conv/link")
public class LinkUserToConversationServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkUserToConversationServlet.class);

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
        int userId = Integer.parseInt(req.getParameter(PARAM_USER_ID));

        try {
            messagingService.linkUserToConversation(conversationId, userId);

            Conversation conversation = messagingService.getConversationById(conversationId);
            String jsonConversation = new Gson().toJson(conversation);
            resp.getWriter().println(jsonConversation);
        } catch (ConversationNotFoundException e) {
            LOGGER.debug("conversation " + conversationId + " not found", e);
        }
    }
}
