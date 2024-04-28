package raf.teamEpic.gui;
import raf.teamEpic.gui.models.ToggleSelectionModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ColapsablePane extends JPanel {
    private JPanel colapsingPanel;
    private JButton colapseButton;
    private JList<String> list;
    private boolean colapsed = false;
    private String name;
    private String[] listStrings;

    public ColapsablePane(String title){
        name = title;
        colapseButton = new JButton(name,getImageIcon("src/main/resources/downArrows.png"));
        colapseButton.addActionListener(e -> {
            colapse();
        });
        colapseButton.setMaximumSize(new Dimension(5000,30));
        colapsingPanel = new JPanel();
        colapsingPanel.setMinimumSize(this.getSize());

        list = new JList<>();
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setMinimumSize(new Dimension(this.getWidth()-10,10));

        colapsingPanel.setLayout(new BorderLayout());
        colapsingPanel.add(list,BorderLayout.CENTER);
        colapsingPanel.setVisible(false);

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(colapseButton);
        add(colapsingPanel);
    }

    public void colapse(){
        if(colapsed){
            colapseButton.setIcon(getImageIcon("src/main/resources/downArrows.png"));
            colapsingPanel.setVisible(false);
        }else {
            colapseButton.setIcon(getImageIcon("src/main/resources/upArrows.png"));
            colapsingPanel.setVisible(true);
        }
        colapsed = !colapsed;
        list.clearSelection();
    }


    private ImageIcon getImageIcon(String path){
        ImageIcon imageIcon = new ImageIcon(path);
        Image newimg = imageIcon.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    public void changeList(String[] newArr){
        listStrings = newArr;
        list = new JList(listStrings);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setFixedCellHeight(20);
        list.setSelectionModel(new ToggleSelectionModel());

        colapsingPanel.remove(list);
        colapsingPanel.add(list);
        //colapsingPanel.setMaximumSize(new Dimension(this.getWidth(), list.getHeight()+35));
    }

    public ArrayList<String> getSelected(){
        ArrayList<String> returnlist = new ArrayList<>();
        for(String index : list.getSelectedValuesList()) {
            returnlist.add(index);
        }
        if(returnlist.isEmpty())
            returnlist.add("*");
        return returnlist;
    }

    public int getMaxSize(){
        return listStrings.length*list.getFixedCellHeight()+35;
    }

    @Override
    public String getName() {
        return name;
    }
}
