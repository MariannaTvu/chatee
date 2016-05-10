package com.bvan.chatee.presentation;

import com.bvan.chatee.service.account.AccountService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Maryana on 10.05.2016.
 */
@WebServlet(urlPatterns = "/accounts")
public class GetAccountsServlet extends HttpServlet {
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
        Map accounts = accountService.getAccounts();
        String jsonAccount = new Gson().toJson(accounts);
        resp.getWriter().println(jsonAccount);
    }
}
