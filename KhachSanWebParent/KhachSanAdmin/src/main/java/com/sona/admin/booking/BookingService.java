package com.sona.admin.booking;

import com.sona.admin.room.RoomNotFoundException;
import com.sona.admin.serviceHotel.ServiceHotelNotFoundException;
import com.sona.common.entity.Booking;
import com.sona.common.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    public List<Booking> getByKeyword(String keyword) {
        return bookingRepository.findByKeyword(keyword);
    }

    public List<Booking> listAll() {
        return (List<Booking>) bookingRepository.findAll();
    }

    public int getTotalBooking() {
        return listAll().size();
    }
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    public void delete(Integer id) throws BookingNotFoundException {
        Long countById = bookingRepository.countById(id);
        if (countById == null || countById == 0) {
            throw new BookingNotFoundException("Không tìm thấy đơn đặt phòng " + id);
        }
        bookingRepository.deleteById(id);
    }
    public Booking get(Integer id) throws BookingNotFoundException {
        try {
            return bookingRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new BookingNotFoundException("Không tìm thấy đơn đặt phòng " + id);
        }
    }
}
