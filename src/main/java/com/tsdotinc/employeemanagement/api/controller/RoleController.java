package com.tsdotinc.employeemanagement.api.controller;


	


	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;

import com.tsdotinc.employeemanagement.api.model.Role;
import com.tsdotinc.employeemanagement.api.repository.RoleRepository;

import java.util.List;

	@RestController
	@RequestMapping("/roles")
	public class RoleController {

	    @Autowired
	    private RoleRepository roleRepository;

	    // Get all roles
	    @GetMapping
	    public ResponseEntity<List<Role>> getAllRoles() {
	        ListCrudRepository<Role, Long> roleRepository = null;
			List<Role> roles = roleRepository.findAll();
	        return new ResponseEntity<>(roles, HttpStatus.OK);
	    }

	    // Get a role by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
	        CrudRepository<Role, Long> roleRepository = null;
			return roleRepository.findById(id)
	                .map(role -> new ResponseEntity<>(role, HttpStatus.OK))
	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    // Create a new role
	    @PostMapping
	    public ResponseEntity<Role> createRole(@RequestBody Role role) {
	        System.out.println("Received Role: " + role.getName());
	        Role savedRole = roleRepository.save(role);
	        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
	    }

	    // Update a role
	    @PutMapping("/{id}")
	    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role roleDetails) {
	        CrudRepository<Role, Long> roleRepository = null;
			return roleRepository.findById(id)
	                .map(role -> {
	                    role.setName(roleDetails.getName());
	                    Role updatedRole = roleRepository.save(role);
	                    return new ResponseEntity<>(updatedRole, HttpStatus.OK);
	                })
	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    // Delete a role
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Object> deleteRole(@PathVariable Long id) {
	        CrudRepository<Role, Long> roleRepository = null;
			return roleRepository.findById(id)
	                .map(role -> {
	                    roleRepository.delete(role);
	                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	                })
	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }
	}


