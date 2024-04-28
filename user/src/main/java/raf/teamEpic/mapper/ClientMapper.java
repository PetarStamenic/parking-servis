package raf.teamEpic.mapper;

import org.springframework.stereotype.Component;
import raf.teamEpic.config.Configuration;
import raf.teamEpic.domain.Client;
import raf.teamEpic.dto.ClientCreateDto;
import raf.teamEpic.dto.ClientDto;
import raf.teamEpic.dto.ClientUpdateDto;

@Component
public class ClientMapper {

    public ClientDto clientToClientDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setUsername(client.getUsername());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setEmail(client.getEmail());
        clientDto.setPassportNumber(client.getPassportNumber());
        clientDto.setSocialSecurityNumber(client.getSocialSecurityNumber());
        if (client.getTotalDays() > Configuration.PLATINUM)
            clientDto.setRank("PLATINUM");
        else if (client.getTotalDays() > Configuration.GOLD)
            clientDto.setRank("GOLD");
        else
            clientDto.setRank("SILVER");

        return clientDto;
    }

    public Client clientCreateDtoToClient(ClientCreateDto ccDto) {
        Client client = new Client();
        client.setUsername(ccDto.getUsername());
        client.setPassword(ccDto.getPassword());
        client.setFirstName(ccDto.getFirstName());
        client.setLastName(ccDto.getLastName());
        client.setEmail(ccDto.getEmail());
        client.setPhone(ccDto.getPhone());
        client.setSocialSecurityNumber(ccDto.getSocialSecurityNumber());
        client.setDateOfBirth(ccDto.getDateOfBirth());
        client.setActive(true);
        client.setPassportNumber(ccDto.getPassportNumber());
        client.setTotalDays(0);

        return client;
    }

    public Client clientUpdateDtoToClient(ClientUpdateDto cuDto) {
        Client client = new Client();
        client.setId(cuDto.getId());
        client.setUsername(cuDto.getUsername());
        client.setPassword(cuDto.getPassword());
        client.setFirstName(cuDto.getFirstName());
        client.setLastName(cuDto.getLastName());
        client.setEmail(cuDto.getEmail());
        client.setPhone(cuDto.getPhone());
        client.setSocialSecurityNumber(cuDto.getSocialSecurityNumber());
        client.setDateOfBirth(cuDto.getDateOfBirth());
        client.setActive(true);
        client.setPassportNumber(cuDto.getPassportNumber());

        return client;
    }

}
