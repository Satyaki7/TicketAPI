package me.satyaki.TicketingApi.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.satyaki.TicketingApi.Model.Seat;
import me.satyaki.TicketingApi.Repository.seatRepo;

@Service
public class SeatService {

    @Autowired
    private seatRepo seatRepository;

    /**
     * Create a new seat
     * 
     * @param seat the seat object to create
     * @return the created seat
     */
    public Seat createSeat(Seat seat) {
        return seatRepository.save(seat);
    }

    /**
     * Get a seat by ID
     * 
     * @param seatId the seat ID
     * @return Optional containing the seat if found
     */
    public Optional<Seat> getSeatById(Long seatId) {
        return seatRepository.findById(seatId);
    }

    /**
     * Get all seats
     * 
     * @return list of all seats
     */
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    /**
     * Update an existing seat
     * 
     * @param seatId      the seat ID
     * @param seatDetails the updated seat details
     * @return the updated seat
     */
    public Optional<Seat> updateSeat(Long seatId, Seat seatDetails) {
        return seatRepository.findById(seatId).map(seat -> {
            if (seatDetails.getEventId() != null) {
                seat.setEventId(seatDetails.getEventId());
            }
            if (seatDetails.getRowNo() != null) {
                seat.setRowNo(seatDetails.getRowNo());
            }
            if (seatDetails.getColNo() != null) {
                seat.setColNo(seatDetails.getColNo());
            }
            if (seatDetails.getIsBooked() != null) {
                seat.setIsBooked(seatDetails.getIsBooked());
            }
            if (seatDetails.getVersion() != null) {
                seat.setVersion(seatDetails.getVersion());
            }
            return seatRepository.save(seat);
        });
    }

    /**
     * Delete a seat by ID
     * 
     * @param seatId the seat ID
     * @return true if deletion was successful
     */
    public boolean deleteSeat(Long seatId) {
        if (seatRepository.existsById(seatId)) {
            seatRepository.deleteById(seatId);
            return true;
        }
        return false;
    }

    /**
     * Check if a seat exists by ID
     * 
     * @param seatId the seat ID
     * @return true if seat exists
     */
    public boolean seatExists(Long seatId) {
        return seatRepository.existsById(seatId);
    }

}
