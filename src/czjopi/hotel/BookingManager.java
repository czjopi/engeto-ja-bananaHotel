package czjopi.hotel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  // 7. Return first N holiday bookings
  /**
   * Returns the first N bookings with VacationType HOLIDAY based on the number of guests.
   *
   * @param n the number of first bookings to return
   * @return a list of the top N HOLIDAY bookings
   * @throws IllegalArgumentException if n is less than or equal to 0
   */
  public List<Booking> getTopNHolidayBookings(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException("N must be greater than 0");
    }

    List<Booking> holidayBookings = new ArrayList<>();

    for (Booking booking : bookingList) {
      if (booking.getVacationType() == VacationType.HOLIDAY) {
        holidayBookings.add(booking);
      }

      if (holidayBookings.size() == n) {
        break;
      }
    }

    return holidayBookings;
  }

  // 8. Statistics of booking by guest count
  /**
   * Returns a mapping of guest counts to the number of bookings with that guest count. - total
   * bookings with 1 guest - total bookings with 2 guests - total bookings with more than 2 guests
   *
   * @return a map where the key is the guest count and the value is the number of bookings
   */
  private Map<Integer, Integer> getBookingStatisticsByGuestCount() {
    Map<Integer, Integer> stats = new HashMap<>();
    for (Booking booking : bookingList) {
      switch (booking.getGuestCount()) {
        case 1 -> stats.put(1, stats.getOrDefault(1, 0) + 1);
        case 2 -> stats.put(2, stats.getOrDefault(2, 0) + 1);
        default -> stats.put(3, stats.getOrDefault(3, 0) + 1); // 3 represents more than 2 guests
      }
    }
    return stats;
  }

  /** Prints the booking statistics by guest count to the console. */
  public void printGuestStatistics() {
    Map<Integer, Integer> stats = getBookingStatisticsByGuestCount();
    System.out.println("Bookings with 1 guest: " + stats.getOrDefault(1, 0));
    System.out.println("Bookings with 2 guests: " + stats.getOrDefault(2, 0));
    System.out.println("Bookings with more than 2 guests: " + stats.getOrDefault(3, 0));
  }
}
