package com.LeaveSystem.DTO.Form;


import com.LeaveSystem.Domain.Form.Forms;
import com.LeaveSystem.Domain.Form.Leave_Type;
import com.LeaveSystem.Domain.Form.Status;

import java.time.LocalDate;

public record AdminResponseDTO(Integer id,String username, Leave_Type type, LocalDate dateFrom, LocalDate dateTo, String motive
        , Status status) {
    public AdminResponseDTO(Forms form){
        this(form.getForms_id(),form.getUser().getUsername(), form.getType(), form.getDate_from(),form.getDate_to()
                , form.getMotive(), form.getStatus());
    }
}
