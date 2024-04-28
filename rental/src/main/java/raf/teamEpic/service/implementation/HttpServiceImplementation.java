package raf.teamEpic.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raf.teamEpic.dto.price.PriceDTO;
import raf.teamEpic.dto.price.PriceRequestDTO;
import raf.teamEpic.service.HttpService;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;

@Service
public class HttpServiceImplementation implements HttpService {

    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public int getDoscount(PriceRequestDTO dto) throws IOException {
        URL url = new URL("http://localhost:8081/user/price");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("GET");
        OutputStream outputStream = connection.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
        outputStreamWriter.write(objectMapper.writeValueAsString(dto));
        outputStreamWriter.flush();
        outputStreamWriter.close();
        outputStream.close();
        connection.connect();

        String result;
        if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int res = bufferedInputStream.read();
            while (res != -1) {
                byteArrayOutputStream.write((byte) res);
                res = bufferedInputStream.read();
            }
            result = byteArrayOutputStream.toString();
            PriceDTO priceDTO = objectMapper.readValue(result, PriceDTO.class);
            return priceDTO.getPrice();

        }else {
            throw new RuntimeException();
        }
    }
}
