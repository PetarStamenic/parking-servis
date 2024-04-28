package raf.teamEpic.gui.views;

import lombok.Getter;
import raf.teamEpic.controler.viewControlers.ConfirmEditController;
import raf.teamEpic.domain.UserType;
import raf.teamEpic.gui.MainFrame;

import javax.swing.*;
import java.awt.*;

@Getter
public class ProfileView {
    private JPanel view;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel confirmPasswordLabel;
    private JPasswordField confirmPasswordField;
    private JLabel firstNameLabel;
    private JTextField firstNameField;
    private JLabel lastNameLabel;
    private JTextField lastNameField;
    private JLabel emailLabel;
    private JTextField emailfield;
    private JLabel phoneLabel;
    private JTextField phoneNumberfield;
    private JLabel socialSecurityLabel;
    private JTextField socialSecurityField;
    private JLabel passportNumberLabel;
    private JTextField passportNumberField;
    private JButton register;
    private JButton back;
    private UserType userType;
    private JLabel jobNameLabel;
    private JTextField jobNameField;

    public ProfileView(UserType type){
        userType = type;
        switch (type) {
            case CLIENT -> initClient();
            case MANAGER -> initManager();
        }
    }

    private void initClient(){
        view = new JPanel();
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        confirmPasswordLabel = new JLabel("Confirm password:");
        firstNameLabel = new JLabel("First name:");
        lastNameLabel = new JLabel("Last name:");
        emailLabel = new JLabel("Email:");
        phoneLabel = new JLabel("Phone number:");
        socialSecurityLabel = new JLabel("Social security number:");
        passportNumberLabel = new JLabel("Passport number:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        emailfield = new JTextField();
        phoneNumberfield = new JTextField();
        socialSecurityField = new JTextField();
        passportNumberField = new JTextField();

        back = new JButton("Back");
        back.addActionListener((e)-> MainFrame.getInstance().login());
        register = new JButton("Save");
        register.addActionListener(new ConfirmEditController(userType));
        addGui();
    }

    private void initManager(){
        view = new JPanel();
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        confirmPasswordLabel = new JLabel("Confirm password:");
        firstNameLabel = new JLabel("First name:");
        lastNameLabel = new JLabel("Last name:");
        emailLabel = new JLabel("Email:");
        phoneLabel = new JLabel("Phone number:");
        socialSecurityLabel = new JLabel("Social security number:");
        jobNameLabel = new JLabel("Job name:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        emailfield = new JTextField();
        phoneNumberfield = new JTextField();
        socialSecurityField = new JTextField();
        jobNameField = new JTextField();

        back = new JButton("Back");
        back.addActionListener((e)-> MainFrame.getInstance().login());
        register = new JButton("Save");
        register.addActionListener(new ConfirmEditController(userType));
        addGui();
    }


    private void addGui(){
        view.setLayout(new GridLayout(0,2));

        view.add(usernameLabel);
        view.add(usernameField);

        view.add(passwordLabel);
        view.add(passwordField);

        view.add(confirmPasswordLabel);
        view.add(confirmPasswordField);

        view.add(firstNameLabel);
        view.add(firstNameField);

        view.add(lastNameLabel);
        view.add(lastNameField);

        view.add(emailLabel);
        view.add(emailfield);

        view.add(phoneLabel);
        view.add(phoneNumberfield);

        view.add(socialSecurityLabel);
        view.add(socialSecurityField);

        if(userType == UserType.CLIENT){
            view.add(passportNumberLabel);
            view.add(passportNumberField);
        }

        if(userType == UserType.MANAGER){
            view.add(jobNameLabel);
            view.add(jobNameField);
        }

        view.add(back);
        view.add(register);


    }
    public JPanel getView() {
        return view;
    }
}
