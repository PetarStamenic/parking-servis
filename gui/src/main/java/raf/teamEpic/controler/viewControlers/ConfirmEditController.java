package raf.teamEpic.controler.viewControlers;

import raf.teamEpic.domain.UserType;
import raf.teamEpic.gui.MainFrame;
import raf.teamEpic.rest.UserServiceRestClient;
import raf.teamEpic.rest.dto.ClientUpdateDto;
import raf.teamEpic.rest.dto.ManagerUpdateDto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ConfirmEditController extends AbstractAction {

    private UserServiceRestClient userService;
    private UserType type;

    public ConfirmEditController(UserType type) {
        this.userService = new UserServiceRestClient();
        this.type = type;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (type) {
            case CLIENT -> {
                ClientUpdateDto cuDto = new ClientUpdateDto();
                cuDto.setUsername(MainFrame.getInstance().getProfileView().getUsernameField().getText());
                cuDto.setPassword(String.valueOf(MainFrame.getInstance().getProfileView().getPasswordField().getPassword()));
                cuDto.setFirstName(MainFrame.getInstance().getProfileView().getFirstNameField().getText());
                cuDto.setLastName(MainFrame.getInstance().getProfileView().getLastNameField().getText());
                cuDto.setEmail(MainFrame.getInstance().getProfileView().getEmailfield().getText());
                cuDto.setPhone(MainFrame.getInstance().getProfileView().getPhoneNumberfield().getText());
                cuDto.setSocialSecurityNumber(MainFrame.getInstance().getProfileView().getSocialSecurityField().getText());
                cuDto.setPassportNumber(MainFrame.getInstance().getProfileView().getPassportNumberField().getText());
                try {
                    userService.updateClient(cuDto);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            case MANAGER -> {
                ManagerUpdateDto muDto = new ManagerUpdateDto();
                muDto.setUsername(MainFrame.getInstance().getRegisterView().getUsernameField().getText());
                muDto.setPassword(String.valueOf(MainFrame.getInstance().getRegisterView().getPasswordField().getPassword()));
                muDto.setFirstName(MainFrame.getInstance().getRegisterView().getFirstNameField().getText());
                muDto.setLastName(MainFrame.getInstance().getRegisterView().getLastNameField().getText());
                muDto.setEmail(MainFrame.getInstance().getRegisterView().getEmailfield().getText());
                muDto.setPhone(MainFrame.getInstance().getRegisterView().getPhoneNumberfield().getText());
                muDto.setSocialSecurityNumber(MainFrame.getInstance().getRegisterView().getSocialSecurityField().getText());
                muDto.setJobName(MainFrame.getInstance().getRegisterView().getJobNameField().getText());
                try {
                    userService.updateManager(muDto);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
