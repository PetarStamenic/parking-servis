package raf.teamEpic.service;

import raf.teamEpic.dto.company.CompanyDTO;
import raf.teamEpic.dto.company.CreateCompanyDTO;
import raf.teamEpic.dto.rating.RatingCreateDTO;
import raf.teamEpic.dto.rating.RatingDTO;
import raf.teamEpic.dto.rating.RatingDeleteDTO;
import raf.teamEpic.dto.rental.*;
import raf.teamEpic.dto.termin.TerminDTO;
import raf.teamEpic.dto.termin.TerminRequestDTO;

public interface RentalService {
    TerminDTO carListing(TerminRequestDTO dto);
    RentalListingDTO rentalListing(Long id, String auth);
    RentalDTO makeRental (GUIRentalDTO dto);
    RentalDeleteDTO deleteRental(RentalDTO dto);
    RatingDTO createRating(RatingCreateDTO dto);
    RatingDTO updateRating(RatingCreateDTO dto);
    RatingDeleteDTO deleteRating(RatingDTO dto);
    CompanyDTO createCompany(CreateCompanyDTO dto);
    CompanyDTO updateCompany(CreateCompanyDTO dto);
}
