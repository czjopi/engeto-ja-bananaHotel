package czjopi.hotel;

import java.util.ArrayList;
import java.util.List;

/**
 * The BookingManager class is responsible for managing a list of bookings. It provides methods to
 * add, retrieve, and clear bookings, as well as to retrieve an immutable copy of the booking list.
 */
public class BookingManager {
  private List<Booking> bookingList = new ArrayList<>();

  /**
   * Adds a booking to the booking list.
   *
   * @param booking the booking to add
   * @throws IllegalArgumentException if the booking is null
   */
  public void addBooking(Booking booking) {
    if (booking == null) {
      throw new IllegalArgumentException("Booking cannot be null");
    }
    bookingList.add(booking);
  }

  /**
   * Retrieves a booking from the booking list by its index.
   *
   * @param index the index of the booking to retrieve
   * @return the booking at the specified index
   * @throws IndexOutOfBoundsException if the index is out of range
   */
  public Booking getBooking(int index) {
    if (index < 0 || index >= bookingList.size()) {
      throw new IndexOutOfBoundsException("Invalid booking index");
    }
    return bookingList.get(index);
  }

  /**
   * Returns an immutable copy of the booking list.
   *
   * @return an unmodifiable list of bookings
   */
  public List<Booking> getBookings() {
    return List.copyOf(bookingList);
  }

  /** Clears all bookings from the booking list. */
  public void clearBookings() {
    bookingList.clear();
  }

  // 5. Count of Working Bookings

  /**
   * Returns the number of bookings with VacationType BUSINESS.
   *
   * @return the count of BUSINESS bookings
   */
  public int getNumberOfWorkingBookings() {
    int count = 0;
    for (Booking booking : bookingList) {
      if (booking.getVacationType() == VacationType.BUSINESS) {
        count++;
      }
    }
    return count;
  }

  // 6. Average number of guests per booking

  /**
   * Returns the average number of guests per booking.
   *
   * @return the average number of guests, or 0 if there are no bookings
   */
  public double getAverageGuests() {
    if (bookingList.isEmpty()) {
      return 0.0;
    }
    int totalGuests = 0;
    for (Booking booking : bookingList) {
      totalGuests += booking.getGuestCount();
    }
    return (double) totalGuests / bookingList.size();
  }
}
