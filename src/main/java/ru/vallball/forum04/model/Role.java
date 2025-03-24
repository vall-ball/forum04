package ru.vallball.forum04.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    @JsonProperty("ADMIN") ROLE_ADMIN, @JsonProperty("MODERATOR") ROLE_MODERATOR, @JsonProperty("USER") ROLE_USER,
    @JsonProperty("BANNED_USER") ROLE_BANNED_USER, @JsonProperty("GUEST") ROLE_GUEST;

    @Override
    public String getAuthority() {
        return name();
    }
}
