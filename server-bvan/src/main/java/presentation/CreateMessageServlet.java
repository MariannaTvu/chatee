package presentation;

import com.google.gson.Gson;
import common.Validator;
import service.messaging.Message;
import service.messaging.MessagingService;
import service.messaging.exception.MessagingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet serves message creation queries.
 *
 * @author bvanchuhov
 */
public class CreateMessageServlet extends HttpServlet {

    private static final String PARAM_CONVERSATION_ID = "conversationId";
    private static final String PARAM_SENDER_ID = "senderId";
    private static final String PARAM_TEXT = "text";

    private MessagingService messagingService = MessagingService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramConversationId = req.getParameter(PARAM_CONVERSATION_ID);
        if (!Validator.isInt(paramConversationId)) {
            //TODO: send error message to client
            return;
        }

        String paramSenderId = req.getParameter(PARAM_SENDER_ID);
        if (!Validator.isInt(paramConversationId)) {
            //TODO: send error message to client
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
            //TODO: send error message to client
            return;
        }
    }
}
