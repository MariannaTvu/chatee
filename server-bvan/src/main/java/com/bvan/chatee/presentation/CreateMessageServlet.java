package com.bvan.chatee.presentation;

import com.google.gson.Gson;
import com.bvan.chatee.common.Validator;
import com.bvan.chatee.service.messaging.Message;
import com.bvan.chatee.service.messaging.MessagingService;
import com.bvan.chatee.service.messaging.exception.MessagingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet serves message creation queries.
 *
 * @author bvanchuhov
 */
@WebServlet(urlPatterns = "/message/create")
public class CreateMessageServlet extends HttpServlet {

    private static final String PARAM_CONVERSATION_ID = "conversationId";
    private static final String PARAM_SENDER_ID = "senderId";
    private static final String PARAM_TEXT = "text";

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
        String paramConversationId = req.getParameter(PARAM_CONVERSATION_ID);
        if (!Validator.isInt(paramConversationId)) {
            resp.getWriter().println("ERROR: illegal conversation id");
            return;
        }

        String paramSenderId = req.getParameter(PARAM_SENDER_ID);
        if (!Validator.isInt(paramConversationId)) {
            resp.getWriter().println("ERROR: illegal sender id");
            return;
        }

        int conversationId = Integer.parseInt(paramConversationId);
        int senderId = Integer.parseInt(paramSenderId);
        String text = req.getParameter(PARAM_TEXT);

        try {
            Message message = messagingService.createMessage(conversationId, senderId, text);

            String jsonMessage = new Gson().toJson(message);
            resp.getWriter().println(jsonMessage);
        } catch (MessagingException e) {
            resp.getWriter().println("INTERNAL SERVER ERROR");
            return;
        }
    }
}
