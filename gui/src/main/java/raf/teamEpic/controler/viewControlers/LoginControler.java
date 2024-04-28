package raf.teamEpic.controler.viewControlers;

import raf.teamEpic.ClientApp;
import raf.teamEpic.gui.MainFrame;
import raf.teamEpic.rest.UserServiceRestClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Arrays;

public class LoginControler extends AbstractAction {

    private String username;
    private String password;
    private UserServiceRestClient userService;

    public LoginControler() {
        this.userService = new UserServiceRestClient();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        username = MainFrame.getInstance().getLandingView().getUsernameField().getText();
        password = String.valueOf(MainFrame.getInstance().getLandingView().getPasswordField().getPassword());
        System.out.println(username+" "+password);
        try {
            String token = userService.login(username, password);
            ClientApp.getInstance().setToken(token);
            System.out.println(token);
            MainFrame.getInstance().loginAsClient();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if(username.equals(password)) {
            if (username.equalsIgnoreCase("client"))
                MainFrame.getInstance().loginAsClient();
            if (username.equalsIgnoreCase("manager"))
                MainFrame.getInstance().loginAsManager();
            if (username.equalsIgnoreCase("admin"))
                MainFrame.getInstance().loginAsAdmin();
        }
    }
}
