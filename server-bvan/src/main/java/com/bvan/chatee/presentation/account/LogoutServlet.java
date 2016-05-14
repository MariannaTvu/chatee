package com.bvan.chatee.presentation.account;

import com.bvan.chatee.service.account.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.bvan.chatee.common.ChatConstants.Params.PARAM_SENDER_USERNAME;

/**
 * Created by Maryana on 10.05.2016.
 */
@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    private AccountService accountService = AccountService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    private void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter(PARAM_SENDER_USERNAME);
        accountService.logout(username);
        resp.getWriter().println(username + " is logged out");
    }
}
