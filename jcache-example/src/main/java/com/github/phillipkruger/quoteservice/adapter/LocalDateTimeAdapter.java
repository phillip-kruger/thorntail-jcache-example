package com.github.phillipkruger.quoteservice.adapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import lombok.extern.java.Log;

/**
 * Transform a LocalDate to String
 * @author Phillip Kruger (phillip.kruger@momentum.co.za)
 */
@Log
public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    @Override
    public LocalDateTime unmarshal(String date) throws Exception {
        return LocalDateTime.parse(date);
    }

    @Override
    public String marshal(LocalDateTime localDateTime) throws Exception {
        return localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }

}