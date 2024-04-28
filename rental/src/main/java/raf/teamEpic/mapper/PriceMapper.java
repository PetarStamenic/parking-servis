package raf.teamEpic.mapper;

import org.springframework.stereotype.Component;
import raf.teamEpic.dto.price.PriceRequestDTO;

@Component
public class PriceMapper {

    public PriceRequestDTO generatePrice(Long id){
        PriceRequestDTO dto = new PriceRequestDTO();
        dto.setId(id);

        return dto;
    }
}
