package raf.teamEpic.controler.viewControlers;

import raf.teamEpic.ClientApp;
import raf.teamEpic.gui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class LogoutControler extends AbstractAction {

    public LogoutControler(){
        putValue(SMALL_ICON, loadIcon("src/main/resources/logout.png"));
        putValue(NAME, "Logout");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ClientApp.getInstance().setToken(null);
        MainFrame.getInstance().landing();
    }

    public Icon loadIcon(String fileName){
        Image icon = null;
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
