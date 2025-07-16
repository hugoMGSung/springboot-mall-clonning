package com.hugo83.webmall.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String> {

    @SuppressWarnings("null")
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userId = "";
        if (authentication != null) {
            userId = authentication.getName();
        }

        // return Optional.ofNullable(userId); // null이면 Optional.empty()
        return Optional.of(userId);  
    }

}
