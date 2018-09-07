package com.github.phillipkruger.quoteservice;

import java.io.Serializable;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Quote POJO
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Data @AllArgsConstructor @NoArgsConstructor
public class Quote implements Serializable {
    private static final long serialVersionUID = 6079456709398475L;
    
    private String text;
    private String author;
    private LocalTime time;
}
