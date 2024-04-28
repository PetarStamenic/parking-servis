package raf.teamEpic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.teamEpic.dto.*;
import raf.teamEpic.security.CheckSecurity;
import raf.teamEpic.security.CheckClient;
import raf.teamEpic.security.CheckManager;
import raf.teamEpic.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registerClient")
    public ResponseEntity<ClientDto> registerClient(@RequestBody @Valid ClientCreateDto ccDto) {
        return new ResponseEntity<>(userService.registerClient(ccDto), HttpStatus.CREATED);
    }

    @PostMapping("/registerManager")
    public ResponseEntity<ManagerDto> registerManager(@RequestBody @Valid ManagerCreateDto mcDto) {
        return new ResponseEntity<>(userService.registerManager(mcDto), HttpStatus.CREATED);
    }

    @PutMapping("/block/{username}")
    @CheckSecurity(roles = {"A"})
    public ResponseEntity<String> block(@PathVariable(value = "username") String username, @RequestHeader("Authorization")String authorization) {
        if (userService.block(username)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/unblock/{username}")
    @CheckSecurity(roles = {"A"})
    public ResponseEntity<String> unblock(@PathVariable(value = "username") String username, @RequestHeader("Authorization")String authorization) {
        if (userService.unblock(username)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto trDto) {
        return new ResponseEntity<>(userService.login(trDto), HttpStatus.OK);
    }

    @PostMapping("/updateClient")
    @CheckClient
    public ResponseEntity<ClientDto> updateClient(@RequestBody @Valid ClientUpdateDto cuDto) {
        return new ResponseEntity<>(userService.updateClient(cuDto), HttpStatus.OK);
    }

    @PostMapping("/updateManager")
    @CheckManager
    public ResponseEntity<ManagerDto> updateManager(@RequestBody @Valid ManagerUpdateDto muDto) {
        return new ResponseEntity<>(userService.updateManager(muDto), HttpStatus.OK);
    }

    @PostMapping("/getClient")
    public ResponseEntity<ClientDto> getClient(@RequestBody @Valid ClientRequestDto crDto) {
        return new ResponseEntity<>(userService.getClient(crDto), HttpStatus.OK);
    }

    @GetMapping("/listClients")
    public ResponseEntity<List<ClientDto>> listClients() {
        return new ResponseEntity<>(userService.listClients(), HttpStatus.OK);
    }

}
