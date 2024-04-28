package raf.teamEpic.controler.viewControlers;

import raf.teamEpic.rest.UserServiceRestClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class BanController extends AbstractAction {

    private UserServiceRestClient userService;
    private String username;

    public BanController(String username) {
        this.userService = new UserServiceRestClient();
        this.username = username;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            userService.banUser(username);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
