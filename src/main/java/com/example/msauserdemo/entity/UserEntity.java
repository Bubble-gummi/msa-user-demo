package com.example.msauserdemo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Entity
@Table(name = "members")
@Data
@NoArgsConstructor
public class UserEntity implements Serializable {

    @Id
    private String email;

    @Column(name = "username")
    private String userName;
    private String password;
    private String roles;
    private boolean enabled; // 이메일 인증 여부



    @Builder
    public UserEntity(String email, String userName, String password, String hp, List<Role> roles, boolean enabled) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.roles = roles != null ? roles.toString() : List.of(Role.ROLE_FAN).toString(); // 기본값
        this.enabled = enabled;
    }

    public enum Role {
        ROLE_STAR, ROLE_FAN;
    }





}
