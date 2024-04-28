package raf.teamEpic.controler;

import raf.teamEpic.gui.ColapsablePane;
import raf.teamEpic.gui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class FilterControler extends AbstractAction {

    String querry;
    @Override
    public void actionPerformed(ActionEvent e) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM TableName WHERE ");
        String joiner;
        if(MainFrame.getInstance().getMainView().getHasEvery()){
            joiner = "AND ";
        }else {
            joiner = "OR ";
        }
        for(ColapsablePane colapsablePane : MainFrame.getInstance().getMainView().getCpanes()){
            ArrayList<String> selected = colapsablePane.getSelected();
            if(selected.get(0).equals("*")){
                continue;
            }else {
                for(String str: selected){
                    stringBuilder.append(colapsablePane.getName()+"="+"\""+str+"\"");
                    stringBuilder.append(" ");
                    stringBuilder.append(joiner);
                }
            }
        }
        stringBuilder.delete(stringBuilder.length()-joiner.length()-1,stringBuilder.length());
        stringBuilder.append(";");
        querry = stringBuilder.toString();
        System.out.println(querry);
    }
}
