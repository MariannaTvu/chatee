package ua.kiev.prog;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class GetListServlet extends HttpServlet {

    private MessageList msgList = MessageList.INSTANCE;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fromStr = req.getParameter("from");
        int from = Integer.parseInt(fromStr);

        String json = msgList.toJSON(from);
        if (json != null) {
            OutputStream os = resp.getOutputStream();
            os.write(json.getBytes());
        }
    }
}
