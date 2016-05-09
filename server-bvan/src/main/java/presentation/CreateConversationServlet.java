package presentation;

import com.google.gson.Gson;
import service.messaging.Conversation;
import service.messaging.MessagingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet serves conversation creation queries.
 *
 * @author bvanchuhov
 */
public class CreateConversationServlet extends HttpServlet {

    private MessagingService messagingService = MessagingService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Conversation conversation = messagingService.createConversation();

        String jsonConversation = new Gson().toJson(conversation);
        resp.getWriter().println(jsonConversation);
    }
}
