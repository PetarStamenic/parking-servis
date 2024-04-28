package raf.teamEpic.mapper;

import org.springframework.stereotype.Component;
import raf.teamEpic.dto.rating.RatingCreateDTO;
import raf.teamEpic.dto.rating.RatingDTO;
import raf.teamEpic.dto.rating.RatingDeleteDTO;
import raf.teamEpic.models.Rating;

@Component
public class RatingMapper {

    public Rating ratingCreateDTOtoRating(RatingCreateDTO dto){
        Rating rating = new Rating();
        rating.setClientId(dto.getClientId());
        rating.setRating(dto.getRating());
        rating.setComment(dto.getComment());
        rating.setCompany(dto.getCompany());
        return rating;
    }

    public RatingDTO ratingToRatingDTO(Rating rating){
        RatingDTO dto = new RatingDTO();
        dto.setId(rating.getId());
        dto.setRating(rating.getRating());
        dto.setComment(rating.getComment());
        dto.setClientId(rating.getClientId());
        return dto;
    }

    public RatingDeleteDTO ratingToRatingDeleteDto(boolean status){
        RatingDeleteDTO dto = new RatingDeleteDTO();
        dto.setStatus(status);
        return dto;
    }
}

