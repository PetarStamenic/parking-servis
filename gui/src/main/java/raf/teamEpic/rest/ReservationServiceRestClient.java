package raf.teamEpic.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.web.util.UriComponentsBuilder;
import raf.teamEpic.ClientApp;
import raf.teamEpic.domain.User;
import raf.teamEpic.rest.dto.CarListingDto;
import raf.teamEpic.rest.dto.RentalDto;
import raf.teamEpic.rest.dto.RentalListingDto;
import raf.teamEpic.rest.dto.TerminRequestDTO;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Date;

public class ReservationServiceRestClient {

    public static final String URL = "http://localhost:8082/api/reservation";

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public CarListingDto getAvailable(String city, String companyName, Date startEnd, Date endDate) throws IOException, URISyntaxException {

        TerminRequestDTO filterDTO = new TerminRequestDTO(city, companyName, startEnd, endDate);
        String uri = UriComponentsBuilder.fromUriString(URL+"/listing")
                /*
                .queryParam("city", encodeUtf8(city))
                .queryParam("companyName", encodeUtf8(companyName))
                .queryParam("startEnd", encodeUtf8((startEnd)))
                .queryParam("endDate", encodeUtf8(endDate))
                */
                .build()
                .toUriString();
        System.out.println(uri);
        String s = objectMapper.writeValueAsString(filterDTO);
        System.out.println(s);
        RequestBody requestBody = RequestBody.create(JSON, objectMapper.writeValueAsString(filterDTO));

        Request request = new Request.Builder()
                .url(uri)
                .header("Authorization", "Bearer" + ClientApp.getInstance().getToken())
                .post(requestBody)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        System.out.println(response.code());

        if(response.code() >= 200 && response.code() <= 300){
            String json = null;
            try {
                json = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                return objectMapper.readValue(json, CarListingDto.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        throw new RuntimeException();
    }



    private static String encodeUtf8(String val) throws UnsupportedEncodingException {
        return URLEncoder.encode("UTF-8");
    }

    private User getUser(String token) throws JsonProcessingException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(token.split("[.]")[1]));
        User userMapper = objectMapper.readValue(payload, User.class);
        return userMapper;
    }

    public RentalDto makeReservation(String carType, String company, Date startDate, Long carId) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Date date = new Date(startDate.getTime());
        RentalDto reservationDto = new RentalDto(carType, company, date, getUser(ClientApp.getInstance().getToken()).getId(), carId);
        RequestBody requestBody = RequestBody.create(JSON, objectMapper.writeValueAsString(reservationDto));
        Request request = new Request.Builder()
                .url(URL)
                .header("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .post(requestBody)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        System.out.println(response.code());

        if(response.code() >= 200 && response.code() <= 300){
            String json = response.body().string();
            return objectMapper.readValue(json, RentalDto.class);
        }

        throw new RuntimeException();

    }


    public RentalListingDto getReservations() throws IOException{

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        System.out.println(URL + "/" + getUser(ClientApp.getInstance().getToken()).getId());

        Request request = new Request.Builder()
                .url(URL + "/" + getUser(ClientApp.getInstance().getToken()).getId())
                .header("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .get()
                .build();


        Call call = client.newCall(request);

        Response response = call.execute();

        System.out.println(response.code());

        if(response.code() >= 200 && response.code() <= 300){
            String json = response.body().string();
            return objectMapper.readValue(json, RentalListingDto.class);
        }

        throw new RuntimeException();

    }
}
