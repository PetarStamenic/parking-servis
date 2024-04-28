package raf.teamEpic.controler.viewControlers;

import raf.teamEpic.domain.UserType;
import raf.teamEpic.gui.MainFrame;
import raf.teamEpic.gui.views.RegisterView;
import raf.teamEpic.rest.UserServiceRestClient;
import raf.teamEpic.rest.dto.ClientCreateDto;
import raf.teamEpic.rest.dto.ManagerCreateDto;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Arrays;

public class ConfirmRegistrationControler extends AbstractAction {
    
    private UserServiceRestClient userService;
    private UserType type;

    public ConfirmRegistrationControler(UserType type) {
        this.userService = new UserServiceRestClient();
        this.type = type;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Arrays.equals(MainFrame.getInstance().getRegisterView().getPasswordField().getPassword(), MainFrame.getInstance().getRegisterView().getConfirmPasswordField().getPassword())) {
            switch (type) {
                case CLIENT: {
                    ClientCreateDto ccDto = new ClientCreateDto();
                    ccDto.setUsername(MainFrame.getInstance().getRegisterView().getUsernameField().getText());
                    ccDto.setPassword(String.valueOf(MainFrame.getInstance().getRegisterView().getPasswordField().getPassword()));
                    ccDto.setFirstName(MainFrame.getInstance().getRegisterView().getFirstNameField().getText());
                    ccDto.setLastName(MainFrame.getInstance().getRegisterView().getLastNameField().getText());
                    ccDto.setEmail(MainFrame.getInstance().getRegisterView().getEmailfield().getText());
                    ccDto.setPhone(MainFrame.getInstance().getRegisterView().getPhoneNumberfield().getText());
                    ccDto.setSocialSecurityNumber(MainFrame.getInstance().getRegisterView().getSocialSecurityField().getText());
                    ccDto.setPassportNumber(MainFrame.getInstance().getRegisterView().getPassportNumberField().getText());
                    try {
                        userService.registerClient(ccDto);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    MainFrame.getInstance().loginAsClient();
                    break;
                }
                case MANAGER: {
                    ManagerCreateDto mcDto = new ManagerCreateDto();
                    mcDto.setUsername(MainFrame.getInstance().getRegisterView().getUsernameField().getText());
                    mcDto.setPassword(String.valueOf(MainFrame.getInstance().getRegisterView().getPasswordField().getPassword()));
                    mcDto.setFirstName(MainFrame.getInstance().getRegisterView().getFirstNameField().getText());
                    mcDto.setLastName(MainFrame.getInstance().getRegisterView().getLastNameField().getText());
                    mcDto.setEmail(MainFrame.getInstance().getRegisterView().getEmailfield().getText());
                    mcDto.setPhone(MainFrame.getInstance().getRegisterView().getPhoneNumberfield().getText());
                    mcDto.setSocialSecurityNumber(MainFrame.getInstance().getRegisterView().getSocialSecurityField().getText());
                    mcDto.setJobName(MainFrame.getInstance().getRegisterView().getJobNameField().getText());
                    try {
                        userService.registerManager(mcDto);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    MainFrame.getInstance().loginAsManager();
                    break;
                }
            }
        }
    }
}
