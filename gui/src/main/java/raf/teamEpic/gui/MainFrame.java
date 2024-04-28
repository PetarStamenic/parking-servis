package raf.teamEpic.gui;


import lombok.Getter;
import raf.teamEpic.domain.UserType;
import raf.teamEpic.gui.views.*;

import javax.swing.*;
import java.awt.*;
@Getter

public class MainFrame extends JFrame {

    private static MainFrame instance;
    private LandingView landingView;
    private RegisterView registerView;
    private MainView mainView;
    private ProfileView profileView;
    private UserType userType;

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
            instance.init();
        }
        return instance;
    }

    private void init() {
        setup();
        landing();
        setVisible(true);
    }

    private void setup(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("TeamEpic");
    }

    private void screenSize1(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        setSize(screenWidth/5, screenHeight/5);
        setLocationRelativeTo(null);
    }

    private void screenSize2(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        setSize(screenWidth/4, screenHeight*3/5);
        setLocationRelativeTo(null);
    }

    private void screenSize3(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        setSize(screenWidth*4/5, screenHeight*4/5);
        setLocationRelativeTo(null);
    }


    public void landing(){
        getContentPane().removeAll();
        screenSize1();
        userType = null;
        if(landingView == null)
            landingView = new LandingView();
        add(landingView.getView());
        repaint();
    }

    public void registerUser(UserType type){
        getContentPane().removeAll();
        screenSize2();
        registerView = new RegisterView(type);
        add(registerView.getView());
    }

    public void login(){
        getContentPane().removeAll();
        screenSize3();
        mainView = new MainView(userType);
        setContentPane(mainView.getView());
    }
    public void loginAsGuest(){
        userType = UserType.GUEST;
        login();
    }

    public void loginAsClient(){
        userType = UserType.CLIENT;
        login();
    }

    public void loginAsAdmin(){
        userType = UserType.ADMIN;
        login();
    }

    public void loginAsManager(){
        userType = UserType.MANAGER;
        login();
    }

    public void editProfile(){
        if(userType == UserType.GUEST) {
            landing();
            return;
        }
        getContentPane().removeAll();
        screenSize2();
        profileView = new ProfileView(userType);
        add(profileView.getView());
    }

    public MainView getMainView() {
        return mainView;
    }

}