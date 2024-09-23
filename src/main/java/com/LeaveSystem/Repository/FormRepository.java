package com.LeaveSystem.Repository;

import com.LeaveSystem.Domain.Form.Forms;
import com.LeaveSystem.Domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormRepository extends JpaRepository<Forms, Integer> {
    List<Forms> findByUser(User user);
}
