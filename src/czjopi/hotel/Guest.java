package czjopi.hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Guest {
  // region attributes
  private String name;
  private String surname;
  private LocalDate birthDate;

  // endregion

  public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d.M.yyyy");

  // region constructors
  public Guest(String name, String surname, LocalDate birthDate) {
    this.name = name;
    this.surname = surname;
    this.birthDate = birthDate;
  }

  // endregion

  // region getters and setters
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  // endregion

  public String getDescription() {
    return String.format("%s %s (%s)", name, surname, birthDate.format(DATE_FORMATTER));
  }
}
