package com.careerdevs.nasaapi.repos;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.careerdevs.nasaapi.models.ERole;
import com.careerdevs.nasaapi.models.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
