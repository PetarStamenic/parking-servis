package raf.teamEpic.gui.views;

import lombok.Getter;
import raf.teamEpic.controler.FilterControler;
import raf.teamEpic.controler.viewControlers.*;
import raf.teamEpic.domain.UserType;
import raf.teamEpic.gui.ColapsablePane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Getter
public class MainView{

    private JTable jTable;
    private JToolBar jToolBar;
    private JMenu userMenu;
    private JScrollPane tableScrollPane;
    private JScrollPane leftScrollPane;

    private JSplitPane splitPaneLeftRight;
    private JPanel colapsablePannels;
    private JRadioButton hasEvery;
    private ArrayList<ColapsablePane> cpanes;
    private JButton filterBtn;
    private JButton checkoutBtn;
    private JButton viewReservations;
    private JPanel mainPanel;
    private JPanel view;
    private JPanel btns;
    private JTabbedPane adminMainPane;
    private JScrollPane usersScrollPane;
    private JTable userTable;
    private JTextField usernameSearchAdmin;
    private JLabel usernameLabel;
    private JButton banUser;
    private JButton unbanUser;
    private JButton changeDiscounts;
    private JButton logout;
    private JButton editProfile;


    //temp
    private String[][] data = { {"1","Toyota","Tacoma","5","12000","pink","785","y"},
                                {"2","Honda","CR-V","5","111000","black","785","y"},
                                {"3","Toyota","Camry","5","15300","black","785","n"},
                                {"4","Toyota","RAV4","2","12490","black","785","y"},
                                {"5","Ram","Pickup","2","198540","orange","785","n"},
                                {"6","Smart","Not so smart","1","12640","blue","70","y"},};
    private String[] column = {"ID","Manufacturer","Model","seats","km","color","price","free"};

    private String[][] users=   {{"1","k","petar","stamenic","y"},
                                {"2","r","Jelena","mijatovic","y"}};

    private String[] usercols = {"ID","username","first name","last name","active"};



    public MainView(UserType userType){
        switch (userType){
            case GUEST:{
                init();
                break;
            }
            case CLIENT:{
                init();
                addCheckoutButton();
                break;
            }
            case ADMIN:{
                initAdmin();
                break;
            }
            case MANAGER:{
                init();
                addViewReservations();
                break;
            }
        }

    }

    private void init(){
        initGui();
        createColapsablePanes();
        addTable();
        addMainView();
        addToolbar();
    }

    private void initAdmin(){
        view = new JPanel();
        addTable();
        userTable = new JTable(users,usercols);
        userTable.setPreferredScrollableViewportSize(new Dimension(500, 300));
        userTable.setFillsViewportHeight(true);
        JPanel userTablePanel = new JPanel();
        userTablePanel.setLayout(new BoxLayout(userTablePanel,BoxLayout.Y_AXIS));
        JPanel temp = new JPanel();
        temp.setLayout(new BoxLayout(temp,BoxLayout.X_AXIS));
        usernameLabel = new JLabel("Enter username you want to find:");
        usernameSearchAdmin = new JTextField();
        usernameSearchAdmin.setMinimumSize(new Dimension(100,20));
        usernameSearchAdmin.setMaximumSize(new Dimension(100,20));
        filterBtn = new JButton("Filter");
        temp.add(usernameLabel);
        temp.add(usernameSearchAdmin);
        temp.add(filterBtn);
        usersScrollPane = new JScrollPane(userTable);
        userTablePanel.add(usersScrollPane);
        userTablePanel.add(temp);

        adminMainPane = new JTabbedPane();
        adminMainPane.add("Users",userTablePanel);
        adminMainPane.add("Cars",tableScrollPane);

        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left,BoxLayout.Y_AXIS));
        logout = new JButton("Logout");
        logout.addActionListener(new BackToLoginViewControler());
        banUser = new JButton("Ban user");
        banUser.addActionListener(new BanController((String) userTable.getValueAt(userTable.getSelectedRow(), 1)));
        unbanUser = new JButton("Unban user");
        unbanUser.addActionListener(new UnbanController((String) userTable.getValueAt(userTable.getSelectedRow(), 1)));

        left.add(banUser);
        left.add(unbanUser);
        leftScrollPane = new JScrollPane(left);

        splitPaneLeftRight = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,leftScrollPane,adminMainPane);
        view.setLayout(new BorderLayout());
        view.add(splitPaneLeftRight);
        addToolbar();
    }

    private void initGui(){
        btns = new JPanel();
        view = new JPanel();
        cpanes = new ArrayList<>();
        mainPanel = new JPanel();
        colapsablePannels = new JPanel();
        filterBtn = new JButton("Apply filter");
        filterBtn.addActionListener(new FilterControler());
        hasEvery = new JRadioButton("Has Every");
        logout = new JButton("Logout");
        logout.addActionListener(new BackToLoginViewControler());
        editProfile = new JButton("Edit Profile");
        editProfile.addActionListener(new EditAcountControler());
    }
    private void createColapsablePanes(){
        ArrayList<String> values = new ArrayList<>();
        for(int i = 0; i<column.length; i++){
            values.clear();
            cpanes.add(new ColapsablePane(column[i]));
            for(int j=0;j<data.length;j++){
                if(!values.contains(data[j][i]))
                    values.add(data[j][i]);
            }
            String[] arrs = new String[values.size()];
            arrs = values.toArray(arrs);
            cpanes.get(i).changeList(arrs);
        }
        addColapsablePanes();
    }
    private void addColapsablePanes(){
        colapsablePannels.setLayout(new BoxLayout(colapsablePannels, BoxLayout.Y_AXIS));
        colapsablePannels.add(hasEvery);
        for(ColapsablePane cp : cpanes){
            colapsablePannels.add(new JToolBar.Separator());
            colapsablePannels.add(cp);
        }
        leftScrollPane = new JScrollPane(colapsablePannels);
    }

    private void addTable(){
        jTable = new JTable(data,column);
        jTable.setPreferredScrollableViewportSize(new Dimension(500, 300));
        jTable.setFillsViewportHeight(true);
        tableScrollPane = new JScrollPane(jTable);
    }

    private void addMainView(){
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.add(filterBtn);
        mainPanel.add(tableScrollPane);

        splitPaneLeftRight = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftScrollPane, mainPanel);
        splitPaneLeftRight.setDividerLocation(300);

        view.setLayout(new BorderLayout());
        view.add(splitPaneLeftRight);
    }

    private void addCheckoutButton(){
        checkoutBtn = new JButton("Checkout");
        mainPanel.add(checkoutBtn);
        mainPanel.repaint();
    }


    private void addViewReservations(){
        checkoutBtn = new JButton("View Reservations");
        mainPanel.add(checkoutBtn);
        mainPanel.repaint();
    }

    private void addToolbar(){
        jToolBar = new JToolBar(SwingConstants.HORIZONTAL);
        jToolBar.setFloatable(false);

        jToolBar.add(new EditAcountControler());
        jToolBar.addSeparator();
        jToolBar.add(new LogoutControler());

        view.add(jToolBar,BorderLayout.NORTH);
        view.repaint();
    }


    public ArrayList<ColapsablePane> getCpanes() {
        return cpanes;
    }

    public JPanel getView() {
        return view;
    }

    public boolean getHasEvery() {
        return hasEvery.isSelected();
    }
}
