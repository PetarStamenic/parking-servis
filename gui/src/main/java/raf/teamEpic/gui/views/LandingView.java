package raf.teamEpic.gui.views;

import lombok.Getter;
import raf.teamEpic.controler.viewControlers.ContinueAsGuestControler;
import raf.teamEpic.controler.viewControlers.LoginControler;
import raf.teamEpic.controler.viewControlers.RegisterControler;
import raf.teamEpic.domain.UserType;

import javax.swing.*;
import java.awt.*;

@Getter
public class LandingView {

    private JPanel view;
    private JPanel loginPanel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JButton registerMButton;
    private JButton continueAsGuestButton;


    public LandingView(){
        init();
    }

    private void init(){
        view = new JPanel();
        loginPanel = new JPanel();
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        loginButton = new JButton("Login");
        registerButton = new JButton("Register Client");
        registerMButton = new JButton("Register Manager");
        continueAsGuestButton = new JButton("Guest");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        addControlers();
        addGui();
    }

    private void addGui(){
        view.setLayout(new BoxLayout(view,BoxLayout.Y_AXIS));
        loginPanel.setLayout(new GridLayout(0,2));

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);
        loginPanel.add(continueAsGuestButton);
        loginPanel.add(registerMButton);

        view.add(loginPanel);
        view.setVisible(true);
    }

    private void addControlers(){
        loginButton.addActionListener(new LoginControler());
        registerButton.addActionListener(new RegisterControler(UserType.CLIENT));
        registerMButton.addActionListener(new RegisterControler(UserType.MANAGER));
        continueAsGuestButton.addActionListener(new ContinueAsGuestControler());
    }

    public JPanel getView() {
        return view;
    }
}
