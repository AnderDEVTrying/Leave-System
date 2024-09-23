package com.LeaveSystem.Domain.Form;

import com.LeaveSystem.DTO.Form.FormRequestDTO;
import com.LeaveSystem.Domain.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "forms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Forms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer forms_id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Enumerated(EnumType.STRING)
    private Leave_Type type;
    private int days;
    private LocalDate date_from;
    private LocalDate date_to;
    private String motive;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Forms(FormRequestDTO data) {
        this.type = data.type();
        this.days = data.days();
        this.date_from = data.dateFrom();
        this.motive = data.motive();
        this.status = Status.WAITING;
    }
}
