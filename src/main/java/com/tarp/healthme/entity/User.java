package com.tarp.healthme.entity;

import javax.persistence.*; //jpa (java persistence api) related functionality like @entity, @table, etc.

import org.springframework.security.core.GrantedAuthority; //spring security for user roles.
import org.springframework.security.core.authority.SimpleGrantedAuthority; //spring security for user roles.
import org.springframework.security.core.userdetails.UserDetails; //spring security for user details.

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

//table used for storing users.
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email")) //setting email as unique constraint.
public class User implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //sets id to autoincrement in db.
	private Long id;

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String role;

	public User() {}

	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public User(String firstName, String lastName, String email, String password, String role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	//predefined function from spring security user detail to get user roles.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { 
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+ this.role));
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	//converts user object to string variable for jsp
	@Override
	public String toString() {
		return "User [getId()=" + getId() + ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName()
		+ ", getName()=" + getName() + ", getEmail()=" + getEmail() + ", getPassword()=" + getPassword()
		+ ", getRole()=" + getRole() + ", getAuthorities()=" + getAuthorities() + ", getUsername()="
		+ getUsername() + ", isAccountNonExpired()=" + isAccountNonExpired() + ", isAccountNonLocked()="
		+ isAccountNonLocked() + ", isCredentialsNonExpired()=" + isCredentialsNonExpired() + ", isEnabled()="
		+ isEnabled() + "]";
	}
}