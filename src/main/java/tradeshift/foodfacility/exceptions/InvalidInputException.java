package tradeshift.foodfacility.exceptions;

public class InvalidInputException extends RuntimeException {

  private static final long serialVersionUID = -1L;

  public InvalidInputException() {
  }

  public InvalidInputException(Throwable cause) {
    initCause(cause);
  }

  public InvalidInputException(String message) {
    super(message);
  }

  public InvalidInputException(String message, Throwable cause) {
    super(message);
    initCause(cause);
  }
}