package raf.teamEpic.mapper;

import org.springframework.stereotype.Component;
import raf.teamEpic.config.Configuration;
import raf.teamEpic.domain.Client;
import raf.teamEpic.domain.Price;
import raf.teamEpic.dto.PriceRequestDto;
import raf.teamEpic.dto.PriceDto;
import raf.teamEpic.repository.UserRepository;

@Component
public class PriceMapper {

    UserRepository userRepository;

    public PriceDto priceToPriceDto(Price price) {
        PriceDto priceDto = new PriceDto();
        priceDto.setPrice(price.getPrice());
        return priceDto;
    }

    public Price priceRequestDtoToPrice(PriceRequestDto prDto) {
        Price price = new Price();
        if (userRepository.findById(prDto.getId()).isPresent()) {
            Client client = (Client) userRepository.findById(prDto.getId()).get();
            if (client.getTotalDays() > Configuration.PLATINUM)
                price.setPrice(Configuration.starPlatinum);
            else if (client.getTotalDays() > Configuration.GOLD)
                price.setPrice(Configuration.goldDiscount);
            else
                price.setPrice(Configuration.silverDiscount);

            return price;
        }
        return null;
    }
}
