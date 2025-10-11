import czjopi.hotel.Booking;
import czjopi.hotel.Guest;
import czjopi.hotel.Room;
import czjopi.hotel.VacationType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class Main {
  public static void main(String[] args) {
    // add guests
    Guest guest1 = new Guest("Adéla", "Malíková", LocalDate.of(1993, 3, 13));
    Guest guest2 = new Guest("Jan", "Dvořáček", LocalDate.of(1995, 5, 5));
    guest2.setBirthDate(LocalDate.of(1995, 4, 5));

    System.out.printf("%s %s (%s)\n", guest2.getName(), guest2.getSurname(), guest2.getBirthDate());

    // add rooms
    Room room1 = new Room(1, 1, new BigDecimal("1000.0"), true, true);
    Room room2 = new Room(2, 1, new BigDecimal("1000.0"), true, true);
    Room room3 = new Room(3, 3, new BigDecimal("2400.0"), false, true);

    // add bookings
    Booking booking1 =
        new Booking(
            room1,
            List.of(guest1),
            LocalDate.of(2021, 7, 19),
            LocalDate.of(2021, 7, 21),
            VacationType.BUSINESS);
    Booking booking2 =
        new Booking(
            room3,
            List.of(guest1, guest2),
            LocalDate.of(2021, 9, 1),
            LocalDate.of(2021, 9, 14),
            VacationType.HOLIDAY);

    List<Booking> bookings = List.of(booking1, booking2);

    // prepare date formatter
    DateTimeFormatter localFormatter =
        DateTimeFormatter.ofPattern("d.M.yyyy").withLocale(Locale.getDefault());

    // print bookings
    for (Booking booking : bookings) {
      System.out.printf(
          "Booking for room %d from %s to %s for %d guests:\n",
          booking.getRoom().getRoomNumber(),
          booking.getStartDate().format(localFormatter),
          booking.getEndDate().format(localFormatter),
          booking.getGuests().size());
      System.out.println("  Reservation type: " + booking.getVacationType());
      System.out.println("  Price per night: " + booking.getRoom().getPricePerNight() + " CZK");
      System.out.println("  Guests:");
      for (Guest guest : booking.getGuests()) {
        String birthDateFormatted = guest.getBirthDate().format(localFormatter);
        System.out.printf(
            "    - %s %s (%s)\n", guest.getName(), guest.getSurname(), birthDateFormatted);
      }
    }
  }
}
