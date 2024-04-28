package raf.teamEpic.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import raf.teamEpic.ClientApp;
import raf.teamEpic.domain.Client;
import raf.teamEpic.domain.Manager;
import raf.teamEpic.domain.User;
import raf.teamEpic.rest.dto.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class UserServiceRestClient {

    public static final String URL = "http://localhost:8083/api/user";
    /// http://localhost:8080/api

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public String login(String username, String password) throws IOException {

        TokenRequestDto tokenRequestDto = new TokenRequestDto(username, password);

        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(tokenRequestDto));

        Request request = new Request.Builder()
                .url(URL + "/login")
                .post(body)
                .build();

        Call call = client.newCall(request);
        System.out.println(request);
        Response response = ((Call) call).execute();
        System.out.println(response.code());
        if (response.code() == 200) {
            String json = response.body().string();
            TokenResponseDto dto = objectMapper.readValue(json, TokenResponseDto.class);

            return dto.getToken();
        }

        throw new RuntimeException("Invalid username or password");
    }

    public ClientDto registerClient(ClientCreateDto clientCreateDto) throws IOException {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(clientCreateDto));

        Request request = new Request.Builder()
                .url(URL + "/registerClient")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() == 201) {
            String json = response.body().string();
            return objectMapper.readValue(json, ClientDto.class);

        }

        throw new RuntimeException("Something went wrong");
    }

    public ManagerDto registerManager(ManagerCreateDto managerCreateDto) throws IOException {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(managerCreateDto));

        Request request = new Request.Builder()
                .url(URL + "/registerManager")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());

        if (response.code() == 201) {
            String json = response.body().string();
            return objectMapper.readValue(json, ManagerDto.class);
        }
        throw new RuntimeException("Something went wrong");
    }

    private User getUser(String token) throws JsonProcessingException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(token.split("[.]")[1]));
        User userMapper = objectMapper.readValue(payload, User.class);
        return userMapper;
    }

    public ClientDto updateClient(ClientUpdateDto clientUpdateDto) throws IOException {
        clientUpdateDto.setId(getUser(ClientApp.getInstance().getToken()).getId());
        clientUpdateDto.setActive(getUser(ClientApp.getInstance().getToken()).isActive());
        clientUpdateDto.setTotalDays(((Client)getUser(ClientApp.getInstance().getToken())).getTotalDays());
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(clientUpdateDto));

        Request request = new Request.Builder()
                .url(URL + "/updateClient")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());

        if (response.code() == 202) {
            String json = response.body().string();
            return objectMapper.readValue(json, ClientDto.class);
        }
        throw new RuntimeException("Something went wrong");
    }

    public ManagerDto updateManager(ManagerUpdateDto managerUpdateDto) throws IOException {
        managerUpdateDto.setId(getUser(ClientApp.getInstance().getToken()).getId());
        managerUpdateDto.setActive(getUser(ClientApp.getInstance().getToken()).isActive());
        managerUpdateDto.setHireDate(((Manager)getUser(ClientApp.getInstance().getToken())).getHireDate());
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(managerUpdateDto));

        Request request = new Request.Builder()
                .url(URL + "/updateManager")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());

        if (response.code() == 202) {
            String json = response.body().string();
            return objectMapper.readValue(json, ManagerDto.class);
        }
        throw new RuntimeException("Something went wrong");
    }

    public List<ClientDto> listClients() throws IOException {

        Request request = new Request.Builder()
                .url(URL + "/listClients")
                .get()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());

        List<ClientDto> clients = new ArrayList<>();
        if (response.code() == 203) {
            String json = response.body().string();
            return objectMapper.readValue(json, new TypeReference<List<ClientDto>>() {});
        }
        throw new RuntimeException("Something went wrong");
    }

    public void banUser(String username) throws IOException {
        Request request = new Request.Builder()
                .url(URL + "/banUser/" + username)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());

        if (response.code() == 204) {
            return;
        }
        throw new RuntimeException("Something went wrong");
    }

    public void unbanUser(String username) throws IOException {
        Request request = new Request.Builder()
                .url(URL + "/unbanUser/" + username)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());

        if (response.code() == 205) {
            return;
        }
        throw new RuntimeException("Something went wrong");
    }

}