package raf.teamEpic.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import raf.teamEpic.domain.Client;
import raf.teamEpic.domain.Manager;
import raf.teamEpic.domain.Price;
import raf.teamEpic.domain.User;
import raf.teamEpic.dto.*;
import raf.teamEpic.exception.ErrorCode;
import raf.teamEpic.exception.NotFoundException;
import raf.teamEpic.mapper.ClientMapper;
import raf.teamEpic.mapper.ManagerMapper;
import raf.teamEpic.mapper.PriceMapper;
import raf.teamEpic.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private TokenService tokenService;
    private ClientMapper clientMapper;
    private ManagerMapper managerMapper;
    private PriceMapper priceMapper;
    private UserRepository userRepository;
    private JmsTemplate jmsTemplate;
    @Value("${destination.sendNotification}")
    private String destinationSendNotification;
    private ObjectMapper objectMapper;

    public UserServiceImpl(UserRepository userRepository, ClientMapper clientMapper,
                           ManagerMapper managerMapper, TokenService tokenService, PriceMapper priceMapper, JmsTemplate jmsTemplate, ObjectMapper objectMapper){
        this.userRepository = userRepository;
        this.clientMapper = clientMapper;
        this.managerMapper = managerMapper;
        this.tokenService = tokenService;
        this.priceMapper = priceMapper;
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public ClientDto registerClient(ClientCreateDto ccDto) {
        Client client = clientMapper.clientCreateDtoToClient(ccDto);
        userRepository.save(client);

        Map<String,String> map = new HashMap<>();
        map.put("firstName", client.getFirstName());
        map.put("lastName", client.getLastName());
        List<String> recipients = new ArrayList<>();
        recipients.add(client.getEmail());

        CreateNotificationDto notif = new CreateNotificationDto(client.getId(), recipients, map,"Confirm Registration");

        try {
            jmsTemplate.convertAndSend(destinationSendNotification, objectMapper.writeValueAsString(notif));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return clientMapper.clientToClientDto(client);
    }

    @Override
    public ManagerDto registerManager(ManagerCreateDto mcDto) {
        Manager manager = managerMapper.managerCreateDtoToManager(mcDto);
        userRepository.save(manager);

        Map<String,String> map = new HashMap<>();
        map.put("firstName", manager.getFirstName());
        map.put("lastName", manager.getLastName());
        List<String> recipients = new ArrayList<>();
        recipients.add(manager.getEmail());
        CreateNotificationDto notif = new CreateNotificationDto(manager.getId(), recipients, map,"Confirm Registration");
        try {
            jmsTemplate.convertAndSend(destinationSendNotification, objectMapper.writeValueAsString(notif));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return managerMapper.managerToManagerDto(manager);
    }

    @Override
    public TokenResponseDto login(TokenRequestDto trDto) {
        User user = userRepository
                .findUserByUsernameAndPassword(trDto.getUsername(),trDto.getPassword())
                .orElseThrow(() -> new NotFoundException("Username or password is incorrect", ErrorCode.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND));

        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("type", user.getType());

        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public boolean block(String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isPresent()) {
            if(user.get().getType().equals("ADMIN")){
                return false;
            }else{
                userRepository.setActive(false, user.get().getId());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean unblock(String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        if(user.isPresent()){
            if(user.get().getType().equals("ADMIN")){
                return false;
            }else{
                userRepository.setActive(true, user.get().getId());
                return true;
            }
        }
        return false;
    }

    @Override
    public ClientDto updateClient(ClientUpdateDto cuDto) {
        Client client = clientMapper.clientUpdateDtoToClient(cuDto);
        userRepository.save(client);
        return clientMapper.clientToClientDto(client);
    }

    @Override
    public ManagerDto updateManager(ManagerUpdateDto muDto) {
        Manager manager = managerMapper.managerUpdateDtoToManager(muDto);
        userRepository.save(manager);
        return managerMapper.managerToManagerDto(manager);
    }

    @Override
    public void addReservation(Long id) {
        Optional<User> cl = userRepository.findById(id);
        if(cl.isPresent() && cl.get() instanceof Client){
            Client client = (Client) cl.get();
            client.setTotalDays(client.getTotalDays() + 1);
            userRepository.save(client);
        }
    }

    @Override
    public void removeReservation(Long id) {
        Optional<User> cl = userRepository.findById(id);
        if(cl.isPresent() && cl.get() instanceof Client){
            Client client = (Client) cl.get();
            client.setTotalDays(client.getTotalDays() - 1);
            userRepository.save(client);
        }
    }

    @Override
    public PriceDto calculatePrice(PriceRequestDto prDto) {
        Price price = priceMapper.priceRequestDtoToPrice(prDto);
        return priceMapper.priceToPriceDto(price);
    }

    @Override
    public ClientDto getClient(ClientRequestDto crDto) {
            Optional<User> cl = userRepository.findById(crDto.getId());
            if(cl.isPresent())
                return clientMapper.clientToClientDto((Client) cl.get());
            return null;
    }

    @Override
    public List<ClientDto> listClients() {
        List<User> users = userRepository.findAll();
        List<ClientDto> clientDtos = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Client) {
                ClientDto clientDto = clientMapper.clientToClientDto((Client) user);
                clientDtos.add(clientDto);
            }
        }
        return clientDtos;
    }

}
