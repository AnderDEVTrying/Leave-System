package com.LeaveSystem.DTO.Form;

import com.LeaveSystem.Domain.Form.Leave_Type;

import java.time.LocalDate;

public record FormRequestDTO(Leave_Type type, int days, LocalDate dateFrom, LocalDate dateTo, String motive) {
}
