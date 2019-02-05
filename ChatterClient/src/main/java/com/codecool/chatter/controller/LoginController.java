package com.codecool.chatter.controller;

import com.codecool.chatter.view.LoginView;

import java.net.Socket;

public class LoginController {

    private Socket connection;
    private LoginView loginView;

    public LoginController() {
        this.loginView = new LoginView();
    }


    public LoginController(Socket connection) {
        this();
        this.connection = connection;
    }


    //TODO
    public void run() {

    }


    public LoginView getLoginView() {
        return loginView;
    }
}
