package com.project.task.manager.domain.entities;

//import java.util.Collection;
//import java.util.List;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import com.project.task.manager.domain.status.Role;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;
// implements UserDetails

@Getter
@Setter
@Entity 
@ToString (exclude = {"password"})
@Builder
@Table (name = "user_table")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = "id")
public class User {
	@Id 
	@Column(
			name = "id",
			updatable = false,
			unique = true)
	@SequenceGenerator(
			name= "user_id_sequence",
			sequenceName = "user_id_sequence",
			allocationSize = 1)
	@GeneratedValue(
			strategy = GenerationType.IDENTITY,
			generator = "user_id_sequence")
	private Long id;
	
	
	@Column(name = "name", nullable = false)
	private String name;
	
	
	@Column(name = "surname", nullable = false)
	private String surname;
	
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	
	@Column(name = "password", nullable = false)
	private String password;
	
	

	@CollectionTable(
			name = "user_role",
			joinColumns = @JoinColumn(
					name = "user_id")
			)
	@Enumerated(EnumType.STRING)
	private Role role;


//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return List.of(new SimpleGrantedAuthority(role.name()));
//	}
//
//	public Collection getAuthorities() {
//		return List.of(role.name());
//	}
//
////	@Override
//	public String getUsername() {
//		return email;
//	}
//
////	@Override
//	public String getPassword() {
//		return password;
//	}
//
//	
////	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
////    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
////    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
////    @Override
//    public boolean isEnabled() {
//    	return true;
//    }


}
