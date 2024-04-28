package raf.teamEpic.service;

import raf.teamEpic.dto.*;

import java.util.List;

public interface UserService {

    ClientDto registerClient(ClientCreateDto ccDto);

    ManagerDto registerManager(ManagerCreateDto mcDto);

    TokenResponseDto login(TokenRequestDto trDto);

    boolean block(String username);

    boolean unblock(String username);

    ClientDto updateClient(ClientUpdateDto cuDto);

    ManagerDto updateManager(ManagerUpdateDto muDto);

    void addReservation(Long id);

    void removeReservation(Long id);

    PriceDto calculatePrice(PriceRequestDto prDto);

    ClientDto getClient(ClientRequestDto crDto);

    List<ClientDto> listClients();

}
