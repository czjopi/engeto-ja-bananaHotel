package czjopi.hotel;

import java.math.BigDecimal;

public class Room {
  // region attributes
  private int roomNumber;
  private int capacity;
  private BigDecimal pricePerNight;
  private boolean withBalcony;
  private boolean withSeaView;

  // endregion

  // region constructors
  public Room(
      int roomNumber,
      int capacity,
      BigDecimal pricePerNight,
      boolean withBalcony,
      boolean withSeaView) {
    setRoomNumber(roomNumber);
    setCapacity(capacity);
    setPricePerNight(pricePerNight);
    setWithBalcony(withBalcony);
    setWithSeaView(withSeaView);
  }

  // endregion

  // region getters and setters
  public int getRoomNumber() {
    return roomNumber;
  }

  public void setRoomNumber(int roomNumber) {
    if (roomNumber < 1) throw new IllegalArgumentException("Room number must be at least 1");
    this.roomNumber = roomNumber;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    if (capacity < 1) throw new IllegalArgumentException("Room capacity must be at least 1");
    this.capacity = capacity;
  }

  public BigDecimal getPricePerNight() {
    return pricePerNight;
  }

  public void setPricePerNight(BigDecimal pricePerNight) {
    if (pricePerNight == null || pricePerNight.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Room price must be non-negative and not null");
    }
    this.pricePerNight = pricePerNight;
  }

  public boolean isWithBalcony() {
    return withBalcony;
  }

  public void setWithBalcony(boolean withBalcony) {
    this.withBalcony = withBalcony;
  }

  public boolean isWithSeaView() {
    return withSeaView;
  }

  public void setWithSeaView(boolean withSeaView) {
    this.withSeaView = withSeaView;
  }
  // endregion
}
