package com.sp.fc.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "sp_user_authority")
@IdClass(SpAuthority.class)
public class SpAuthority implements GrantedAuthority {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Id
    private String authority;

    @Override
    public String getAuthority() {
        return null;
    }
}
