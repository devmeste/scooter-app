package com.arqui.integrador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arqui.integrador.model.Role;
import com.arqui.integrador.model.UserAuth;

@Repository
public interface IAuthRepository extends JpaRepository<UserAuth, Long>{
	
	Optional<UserAuth> findByUsername(String username);
	
	@Query("SELECT r FROM Role r WHERE r.role = :role")
	Optional<Role> findRoleByRoleName(@Param("role") String role);
	
}
