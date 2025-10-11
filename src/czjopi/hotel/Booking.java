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
}
