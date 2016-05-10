package com.bvan.chatee.service.account;

import com.bvan.chatee.service.account.exception.AccountNotFoundException;

import java.util.*;

/**
 * Account com.bvan.chatee.service intended for manipulations with user accounts.
 *
 * @author bvanchuhov
 */
public enum AccountService {
    INSTANCE;
    /**
     * Synchronized map id -> account.
     */
    private Map<Integer, Account> idToAccountMap = Collections.synchronizedMap(new HashMap<Integer, Account>());
    private List<Account> loggedIn = Collections.synchronizedList(new ArrayList<Account>());

    public Map<Integer, Account> getAccounts() {
        return idToAccountMap;
    }

    public List<Account> getLoggedIn() {
        return loggedIn;
    }

    public Account checkUsername(String username) {
        for (Account user : idToAccountMap.values()) {
            if (username.equals(user.getLogin())) {
                return user;
            }
        }
        return null;
    }
    /**
     * Create account. Method is tread safe.
     *
     * @param username is the login
     * @return created account.
     */
    public Account createAccount(String username) {
        Account account = new Account(username);
        idToAccountMap.put(account.getId(), account);
        return account;
    }

    /**
     * Return account by specified id.
     *
     * @param accountId account id.
     * @return account by specified id.
     * @throws AccountNotFoundException if account not found.
     */
    public Account getAccountById(int accountId) throws AccountNotFoundException {
        Account account = idToAccountMap.get(accountId);
        if (account == null) {
            throw new AccountNotFoundException(accountId);
        }
        return account;
    }

    public Account login(String username, String password) {
        for (Account user : idToAccountMap.values()) {
            if (username.equals(user.getLogin()) && password.equals(user.getPassword()) && !loggedIn.contains(user)) {
                user.setStatus(true);
                loggedIn.add(user);
                return user;
            }
        }
        return null;
    }

    public Account logout(String username) {
        for (Account user : idToAccountMap.values()) {
            if (username.equals(user.getLogin())) {
                user.setStatus(false);
                loggedIn.remove(user);
            }
        }
        return null;
    }
}
