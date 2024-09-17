package com.LeaveSystem.Repository;

import com.LeaveSystem.Domain.Forms.Forms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Forms, Integer> {
}
