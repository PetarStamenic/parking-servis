package raf.teamEpic.mapper;

import org.springframework.stereotype.Component;
import raf.teamEpic.domain.Manager;
import raf.teamEpic.dto.ManagerCreateDto;
import raf.teamEpic.dto.ManagerDto;
import raf.teamEpic.dto.ManagerUpdateDto;

@Component
public class ManagerMapper {

    public ManagerDto managerToManagerDto(Manager manager) {
        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(manager.getId());
        managerDto.setUsername(manager.getUsername());
        managerDto.setFirstName(manager.getFirstName());
        managerDto.setLastName(manager.getLastName());
        managerDto.setEmail(manager.getEmail());
        managerDto.setJobName(manager.getJobName());

        return managerDto;
    }

    public Manager managerCreateDtoToManager(ManagerCreateDto mcDto) {
        Manager manager = new Manager();
        manager.setUsername(mcDto.getUsername());
        manager.setPassword(mcDto.getPassword());
        manager.setFirstName(mcDto.getFirstName());
        manager.setLastName(mcDto.getLastName());
        manager.setEmail(mcDto.getEmail());
        manager.setPhone(mcDto.getPhone());
        manager.setSocialSecurityNumber(mcDto.getSocialSecurityNumber());
        manager.setDateOfBirth(mcDto.getDateOfBirth());
        manager.setActive(true);
        manager.setJobName(mcDto.getJobName());
        manager.setHireDate(mcDto.getHireDate());
        
        return manager;
    }

    public Manager managerUpdateDtoToManager(ManagerUpdateDto muDto) {
        Manager manager = new Manager();
        manager.setUsername(muDto.getUsername());
        manager.setPassword(muDto.getPassword());
        manager.setFirstName(muDto.getFirstName());
        manager.setLastName(muDto.getLastName());
        manager.setEmail(muDto.getEmail());
        manager.setPhone(muDto.getPhone());
        manager.setSocialSecurityNumber(muDto.getSocialSecurityNumber());
        manager.setDateOfBirth(muDto.getDateOfBirth());
        manager.setJobName(muDto.getJobName());
        manager.setHireDate(muDto.getHireDate());

        return manager;
    }
}
