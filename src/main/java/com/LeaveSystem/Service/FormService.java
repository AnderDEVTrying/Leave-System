package com.LeaveSystem.Service;

import com.LeaveSystem.DTO.Form.RegularResponseDTO;
import com.LeaveSystem.Domain.Form.Forms;
import com.LeaveSystem.Domain.User.User;
import com.LeaveSystem.Repository.FormRepository;
import com.LeaveSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FormService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private FormRepository formRepository;
    public User findByUsername(String name) {
        return repository.findByUsername(name)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<RegularResponseDTO> findRequestByUser(User user){
        return  formRepository.findByUser(user).stream()
                .map(RegularResponseDTO::new).toList();
    }
    public LocalDate newDateTo(Forms form){
        return form.getDate_from().plusDays(form.getDays());
    }
}
