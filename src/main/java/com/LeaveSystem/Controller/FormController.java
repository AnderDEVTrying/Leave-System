package com.LeaveSystem.Controller;

import com.LeaveSystem.DTO.Form.AdminResponseDTO;
import com.LeaveSystem.DTO.Form.FormRequestDTO;
import com.LeaveSystem.Domain.Form.Forms;
import com.LeaveSystem.Domain.Form.Status;
import com.LeaveSystem.Domain.User.User;
import com.LeaveSystem.Domain.User.UserRole;
import com.LeaveSystem.Repository.FormRepository;
import com.LeaveSystem.Service.FormService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            form.setStatus(Status.WAITING);

            repository.save(form);
            return ResponseEntity.ok("Request Submitted");
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.badRequest().body("Error while creating form" + exception.getMessage());
        }

    }

    @GetMapping("/requests")
    public List<? extends Record> getAllRequests(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();

        User user = service.findByUsername(username);

        if (user.getRole().equals(UserRole.ADMIN)) {
            return repository.findAll().stream()
                    .map(AdminResponseDTO::new).toList();
        } else return service.findRequestByUser(user);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<?> requestStatus(@PathVariable Integer id, @RequestBody Status situation,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        try {
            String username = userDetails.getUsername();
            User user = service.findByUsername(username);

            if (!user.getRole().equals(UserRole.ADMIN)) {
                return ResponseEntity.status(403).body("Access Denied: Only admins can update the status");
            }

            Forms form = repository.findById(id).orElseThrow(() -> new RuntimeException("Request not found"));

            form.setStatus(situation);
            repository.save(form);

            return ResponseEntity.ok("The request was " + situation + " with success");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("Error while updating the request: " + exception.getMessage());
        }
    }

}
