package raf.teamEpic.controler.viewControlers;

import raf.teamEpic.gui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EditAcountControler extends AbstractAction {

    public EditAcountControler(){
        putValue(SMALL_ICON,loadIcon("src/main/resources/user.png"));
        putValue(NAME,"Edit profile");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().editProfile();
    }

    public Icon loadIcon(String fileName){
        Image icon;
        Icon littleicon = null;
        if(fileName != null){
            icon = new ImageIcon(fileName).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            littleicon = new ImageIcon(icon);
        } else {
            System.err.println("Image not found: " + fileName);
        }
        return littleicon;
    }
}
