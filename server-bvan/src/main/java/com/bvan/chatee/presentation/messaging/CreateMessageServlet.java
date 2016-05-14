package com.bvan.chatee.presentation.messaging;

import com.bvan.chatee.common.Validator;
import com.bvan.chatee.service.messaging.Message;
import com.bvan.chatee.service.messaging.MessagingService;
import com.bvan.chatee.service.messaging.exception.MessagingException;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.bvan.chatee.common.ChatConstants.Params.*;

/**
 * Servlet serves message creation queries.
 *
 * @author bvanchuhov
 */
@WebServlet(urlPatterns = "/message/create")
public class CreateMessageServlet extends HttpServlet {

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

        String paramSenderId = req.getParameter(PARAM_USER_ID);
        if (!Validator.isInt(paramConversationId)) {
            resp.getWriter().println("ERROR: illegal sender id");
            return;
        }

        int conversationId = Integer.parseInt(paramConversationId);
        int senderId = Integer.parseInt(paramSenderId);
        String text = req.getParameter(PARAM_MESSAGE_TEXT);

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