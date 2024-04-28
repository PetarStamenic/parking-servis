package raf.teamEpic.controler.viewControlers;

import raf.teamEpic.domain.UserType;
import raf.teamEpic.gui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RegisterControler extends AbstractAction {

    UserType type;

    public RegisterControler(UserType type) {
        this.type = type;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().registerUser(type);
    }
}
