package com.LeaveSystem.Service;

import com.LeaveSystem.Domain.Forms.Forms;
import com.LeaveSystem.Domain.User.User;
import com.LeaveSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FormService {
    @Autowired
    private UserRepository repository;
    public User findByUsername(String name) {
        return repository.findByUsername(name)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    public LocalDate newDateTo(Forms form){
        return form.getDate_from().plusDays(form.getDays());
    }
}
