package com.du.reservation0918.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Reservation {
    private Long id;
    private String customerName;     // 고객명
    private Date reservationDate;    // 예약일
    private List<com.du.reservation0918.model.ReservationItem> items; // 예약 항목들
}

