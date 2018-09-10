package com.github.phillipkruger.quoteservice;

import com.github.phillipkruger.quoteservice.adapter.LocalDateTimeAdapter;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Quote POJO
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Data @AllArgsConstructor @NoArgsConstructor
@XmlRootElement @XmlAccessorType(XmlAccessType.FIELD)
public class Quote implements Serializable {
    private static final long serialVersionUID = 6079456709398475L;
    @XmlAttribute
    private String text;
    @XmlAttribute
    private String author;
    @XmlAttribute @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime time;
}
