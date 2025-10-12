import czjopi.hotel.Booking;
import czjopi.hotel.Guest;
import czjopi.hotel.Room;
import czjopi.hotel.VacationType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    Booking booking3 = new Booking(room2, List.of(guest2), VacationType.HOLIDAY);

    List<Booking> bookings = List.of(booking1, booking2, booking3);

    // print bookings
    for (Booking booking : bookings) {
      System.out.println(booking.getDescription());
    }
  }
}
