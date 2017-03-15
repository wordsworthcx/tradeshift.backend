package tradeshift.foodfacility.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Component;
import tradeshift.foodfacility.constants.Constants;
import tradeshift.foodfacility.exceptions.DependencyException;
import tradeshift.foodfacility.exceptions.ErrorMessages;
import tradeshift.foodfacility.exceptions.InvalidInputException;

import lombok.extern.slf4j.Slf4j;

/**
 * A utility class used to read from a http request and decode json text.
 * @author xuch.
 */
@Slf4j
@Component
public class RestUtility {
    public String getRestfulResponse(String url) throws DependencyException {
        try {
            log.info("Sending http request [{}].", url);
            Scanner s = new Scanner(new URL(url).openStream()).useDelimiter(Constants.Common.INPUT_BOUNDARY);
            return s.hasNext() ? s.next() : null;
        } catch (MalformedURLException e) {
            log.error(ErrorMessages.INVALID_URL, url);
            throw new InvalidInputException(MessageFormatter.format(ErrorMessages.INVALID_URL, url).getMessage(), e);
        } catch (IOException e) {
            log.error(ErrorMessages.HTTP_REQUEST_FAILED, url);
            throw new DependencyException(MessageFormatter.format(ErrorMessages.HTTP_REQUEST_FAILED, url).getMessage(), e);
        }
    }
}
