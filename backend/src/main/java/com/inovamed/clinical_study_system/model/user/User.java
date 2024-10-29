package com.inovamed.clinical_study_system.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.PublicKey;
import java.util.Collection;
import java.util.List;

@Entity(name = "tb_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private UserRoles roles;
    @Lob
    private PublicKey publicKey;

    public User(String email, String password, UserRoles roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.roles == UserRoles.PATIENT) {
            return List.of(new SimpleGrantedAuthority("ROLE_PATIENT"));
        } else if (this.roles == UserRoles.DOCTOR) {
            return List.of(new SimpleGrantedAuthority("ROLE_DOCTOR"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_STUDY_REPRESENTATIVE"));
        }
    }

    @Override
    public String getUsername() {
        return "";
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
