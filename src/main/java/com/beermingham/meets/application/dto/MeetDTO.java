package com.beermingham.meets.application.dto;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

public class MeetDTO {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    private List<String> guestIds;

    public MeetDTO(LocalDate date, List<String> guestIds) {
        this.date = date;
        this.guestIds = guestIds;
    }

    public MeetDTO() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<String> getGuestIds() {
        return guestIds;
    }

    public void setGuestIds(List<String> guestIds) {
        this.guestIds = guestIds;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
