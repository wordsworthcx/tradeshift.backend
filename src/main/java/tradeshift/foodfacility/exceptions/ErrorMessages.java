package tradeshift.foodfacility.exceptions;

public interface ErrorMessages {
    String HTTP_REQUEST_FAILED = "Http request of [{}] failed, please check the validation and connection.";
    String INVALID_JSON = "The text [{}] is not valid JSON";
    String INVALID_URL = "The url [{}] is not valid";
    String READ_FILE_ERROR = "Exception happend when read file [{}], please check the file URI and encode";
    String INVALID_INPUT = "Input [{}] is invalid";
}
