package czjopi.hotel;

import java.time.LocalDate;
import java.util.List;

public class Booking {
  // region attributes
  private Room room;
  private List<Guest> guests;
  private LocalDate startDate;
  private LocalDate endDate;
  private VacationType vacationType;

  // endregion

  // region constructors
  public Booking(
      Room room,
      List<Guest> guests,
      LocalDate startDate,
      LocalDate endDate,
      VacationType vacationType) {
    setRoom(room);
    setGuests(guests);
    setStartDate(startDate);
    setEndDate(endDate);
    setVacationType(vacationType);
  }

  public Booking(Room room, List<Guest> guests, VacationType vacationType) {
    this(room, guests, LocalDate.now(), LocalDate.now().plusDays(6), vacationType);
  }

  // endregion

  // region getters and setters
  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    if (room == null) throw new IllegalArgumentException("Room cannot be null");
    this.room = room;
  }

  public List<Guest> getGuests() {
    return List.copyOf(guests);
  }

  public void setGuests(List<Guest> guests) {
    if (guests == null || guests.isEmpty())
      throw new IllegalArgumentException("Guests list cannot be null or empty");
    if (guests.size() > room.getCapacity())
      throw new IllegalArgumentException("Number of guests exceeds room capacity");
    this.guests = List.copyOf(guests);
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    if (startDate == null) {
      throw new IllegalArgumentException("Start date has not been set");
    }
    if (endDate != null && startDate.isAfter(endDate)) {
      throw new IllegalArgumentException("Start date cannot be after end date");
    }
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    if (endDate == null) {
      throw new IllegalArgumentException("End date has not been set");
    }
    if (startDate != null && endDate.isBefore(startDate)) {
      throw new IllegalArgumentException("End date cannot be before start date");
    }
    this.endDate = endDate;
  }

  public VacationType getVacationType() {
    return vacationType;
  }

  public void setVacationType(VacationType vacationType) {
    this.vacationType = vacationType;
  }

  // endregion

  public String getDescription() {
    StringBuilder sb = new StringBuilder();
    sb.append(
        String.format(
            "Booking for room %d from %s to %s for %d guests:\n",
            room.getRoomNumber(),
            startDate.format(Guest.DATE_FORMATTER),
            endDate.format(Guest.DATE_FORMATTER),
            guests.size()));
    sb.append(String.format("  Reservation type: %s\n", vacationType));
    sb.append(String.format("  Price per night: %s CZK\n", room.getPricePerNight()));
    sb.append("  Guests:\n");
    for (Guest guest : guests) {
      sb.append(String.format("    - %s\n", guest.getDescription()));
    }
    return sb.toString();
  }
}
