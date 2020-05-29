package com.example.paymentrecorder.processor;

import java.util.List;

/**
 * Interface for defining basic operations required to achieve Payments Recorder functionality
 */
public interface Processor<T> {

    /**
     * Retrieve all the payment templates configured in the system by user / default
     * @return List of all the templates to be parsed
     */
    public List<T> getParsingTemplates();
}
