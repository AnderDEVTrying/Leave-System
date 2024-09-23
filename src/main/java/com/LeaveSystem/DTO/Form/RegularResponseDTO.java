package com.LeaveSystem.DTO.Form;

import com.LeaveSystem.Domain.Form.Forms;
import com.LeaveSystem.Domain.Form.Leave_Type;
import com.LeaveSystem.Domain.Form.Status;

import java.time.LocalDate;

public record RegularResponseDTO(Leave_Type type, LocalDate dateFrom, LocalDate dateTo, String motive
        , Status status) {
    public RegularResponseDTO(Forms form){
        this( form.getType(), form.getDate_from(),form.getDate_to()
                , form.getMotive(), form.getStatus());
    }
}