package com.easyWay.Student_Management_System.Security;

import com.easyWay.Student_Management_System.Entity.Users;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {

    public Optional<String> getLoggedInUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            return Optional.of(((UserDetails) principal).getUsername()); // Username is typically the email.
        } else if (principal instanceof String) {
            return Optional.of(principal.toString());
        }

        return Optional.empty();
    }

    /**
     * Get the roles/authorities of the logged-in user.
     *
     * @return List of roles or an empty list if not available.
     */
    public List<String> getLoggedInUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return List.of();
        }

        return authentication.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .toList();
    }

    public String getLoggedInUserSchoolCode() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return "";
        }

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof Users)) {
          return principal.toString();
        }

        return "";
    }

}
