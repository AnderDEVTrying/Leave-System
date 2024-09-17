package com.LeaveSystem.Controller;

import com.LeaveSystem.DTO.Form.FormRequestDTO;
import com.LeaveSystem.Domain.Forms.Forms;
import com.LeaveSystem.Domain.User.User;
import com.LeaveSystem.Repository.FormRepository;
import com.LeaveSystem.Service.FormService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("forms")
public class FormController {
    @Autowired
    private FormRepository repository;
    @Autowired
    private FormService service;

    @PostMapping("/create")
    public ResponseEntity createRequest(@RequestBody @Valid FormRequestDTO data) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String userName = userDetails.getUsername();

            User user = service.findByUsername(userName);
            Forms form = new Forms(data);
            form.setUser(user);
            form.setDate_to(service.newDateTo(form));

            repository.save(form);
            return ResponseEntity.ok("Request Submitted");
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.badRequest().body("Error while creating form" + exception.getMessage());
        }

    }
}
