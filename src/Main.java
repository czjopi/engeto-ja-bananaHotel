import czjopi.hotel.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {
  /**
   * Creates and returns a BookingManager pre-filled with sample bookings used by the demo.
   *
   * @return a BookingManager containing sample rooms, guests and bookings
   */
  public static BookingManager fillBookings() {
    BookingManager bookingManager = new BookingManager();

    // add rooms
    Room room1 = new Room(1, 1, new BigDecimal("1500.0"), true, false);
    Room room2 = new Room(2, 1, new BigDecimal("2000.0"), true, true);
    Room room3 = new Room(3, 2, new BigDecimal("2800.0"), true, true);
    Room room4 = new Room(4, 3, new BigDecimal("3000.0"), false, false);
    Room room5 = new Room(5, 4, new BigDecimal("4000.0"), true, true);

    // add guests
    Guest guest1 = new Guest("Karel", "Dvořák", LocalDate.of(1990, 5, 15));
    Guest guest2 = new Guest("Karel", "Dvořák", LocalDate.of(1979, 1, 3));
    Guest guest3 = new Guest("Karolína", "Tmavá", LocalDate.of(1985, 8, 20));

    /*
     * 1. Karel Dvořák, narozen 15. 5. 1990, si rezervuje pokoj číslo 3 od 1. 6. 2023 do 7. 6. 2023. Bude to pracovní pobyt.
     */
    bookingManager.addBooking(
        new Booking(
            room3,
            List.of(guest1),
            LocalDate.of(2023, 6, 1),
            LocalDate.of(2023, 6, 7),
            VacationType.BUSINESS));

    /*
     * 2. Jiný pan Karel Dvořák, narozen 3. 1. 1979, si rezervuje pokoj číslo 2 od 18. 7. 2023 do 21. 7. 2023. Bude to rekreační pobyt.
     */
    bookingManager.addBooking(
        new Booking(
            room2,
            List.of(guest2),
            LocalDate.of(2023, 7, 18),
            LocalDate.of(2023, 7, 21),
            VacationType.HOLIDAY));

    /*
     * 3. Fyzioterapeutka Karolína Tmavá si rezervuje pokoj číslo 3 na celý srpen (od 1.8. do 31.8.). Bude to pracovní pobyt a druhým hostem bude její partner pan Karel Dvořák, narozen 1990 (ten z první rezervace).
     */
    bookingManager.addBooking(
        new Booking(
            room3,
            List.of(guest3, guest1),
            LocalDate.of(2023, 8, 1),
            LocalDate.of(2023, 8, 31),
            VacationType.BUSINESS));

    /*
     * 4. Stejná fyzioterapeutka Karolína Tmavá si dále pro své klienty rezervuje pokoj číslo 2 na dvoudenní pobyty v měsíci srpnu 2023. Vytvoř 10 dvoudenních rezervací pro rekreační pobyty.
     *   - První rezervace bude od 1. do 2. 8., druhá rezervace od 3. do 4. 8., třetí od 5. do 6. 8. atd. Poslední rezervace bude od 19. do 20. 8. 2023.
     */
    for (int i = 0; i < 10; i++) {
      Guest guestClient = new Guest("Klient" + (i + 1), "Tmavá", LocalDate.of(1985, 8, 20));
      LocalDate startDate = LocalDate.of(2023, 8, 1).plusDays(i * 2);
      LocalDate endDate = startDate.plusDays(1);
      bookingManager.addBooking(
          new Booking(room2, List.of(guestClient), startDate, endDate, VacationType.HOLIDAY));
    }

    return bookingManager;
  }

  public static void main(String[] args) {
    BookingManager bookingManager = fillBookings();

    List<Booking> bookings = bookingManager.getBookings();

    // total number of bookings
    System.out.println("Total number of bookings: " + bookings.size());
    System.out.println();

    // average number of guests per booking
    System.out.println("Average number of guests per booking: " + bookingManager.getAverageGuests());
    System.out.println();

    // first 8 holiday bookings
    System.out.println("First 8 holiday bookings:");
    List<Booking> topHolidayBookings = bookingManager.getTopNHolidayBookings(8);
    for (Booking booking : topHolidayBookings) {
      System.out.println(booking.getFormattedSummary());
    }
    System.out.println();

    // statistics of bookings by guest count
    bookingManager.printGuestStatistics();
    System.out.println();

    // number of business bookings
    System.out.println(
        "Number of business booking: " + bookingManager.getNumberOfWorkingBookings());
    System.out.println();

    // formatted list of all bookings
    System.out.println("Formatted list of all bookings:");
    for (Booking booking : bookings) {
      System.out.println(booking.getFormattedSummary());
    }
  }
}
