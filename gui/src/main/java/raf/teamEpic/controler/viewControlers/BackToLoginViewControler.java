package raf.teamEpic.controler.viewControlers;

import raf.teamEpic.gui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BackToLoginViewControler extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().landing();
    }
}
