package com.tms.web.entities.security.User;

import com.tms.web.entities.library.Author.Author;
import com.tms.web.entities.library.UserLibrary;
import com.tms.web.entities.security.ROLE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@Entity
@Table(name = "security_users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @NotBlank
    @NotNull
    @Length(min = 3,max = 30)
    @Column(unique = true, updatable = false)
    private String username;
    @NotBlank
    @NotEmpty
    @NotNull
    @Length(min = 3,max = 30)
    private String nickName;
    @OneToOne
    private Author author;

    @NotBlank
    @NotEmpty
    @NotNull
    @Length(min = 3)
    private String password;
    @OneToMany(mappedBy = "user",cascade = CascadeType.MERGE,targetEntity = UserLibrary.class)
    private List<UserLibrary> userLibrary;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "security_roles")
    private List<ROLE> roles = new java.util.ArrayList<>();

    private Boolean accountNonExpired;

    private Boolean accountNonLocked;

    private Boolean credentialsNonExpired;

    private Boolean enabled;

    @CreationTimestamp
    @Column(name = "`create`")
    private Date creation;
    @UpdateTimestamp
    @Column(name = "`update`")
    private Date lastUpdate;
    @Version
    private Long version;

    public User() {
        accountNonExpired = true;
        accountNonLocked = true;
        credentialsNonExpired = true;
        enabled = true;
        userLibrary = new ArrayList<>();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
