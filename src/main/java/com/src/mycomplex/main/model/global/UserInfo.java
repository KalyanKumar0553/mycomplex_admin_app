package com.src.mycomplex.main.model.global;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	
	@Column(unique=true)
	private String email;
	
	@Column(unique=true)
	private String username;
    
	private String password;
    
	private String salt;
    
	private String roles;
	
	private String bio;
	
	@Column(unique=true)
	private String mobile;
	
	private String profession;
    
	private String homeTown;
    
	private String profilePic;
	
    private Date dob;
    
    private Long createdBy;
    
    private Date createdOn;
    
    private Long lastModifiedBy;
    
    private Date lastModifiedOn;

    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private Set<UserInterests> userInterests;
	
	
	@Column
	private boolean enabled;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles==null ? List.of(new SimpleGrantedAuthority("USER")) : List.of(roles.split(",")).stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}