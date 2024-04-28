package raf.teamEpic.service;

import org.springframework.retry.annotation.Retryable;
import raf.teamEpic.dto.price.PriceRequestDTO;

import java.io.IOException;

public interface HttpService {
    @Retryable(value = RuntimeException.class)
    int getDoscount(PriceRequestDTO dto) throws IOException;
}
