package com.tsdotinc.employeemanagement.api.repository;



	
	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.stereotype.Repository;

import com.tsdotinc.employeemanagement.api.model.Role;

	@Repository
	public interface RoleRepository extends JpaRepository<Role, Long> {
	    Role findByName(String name);
	}


