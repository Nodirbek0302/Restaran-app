package com.cosinus.restoranapp.utils;

import com.cosinus.restoranapp.exceptions.RestException;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import com.cosinus.restoranapp.model.Users;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class CommonUtils {

    public static Users getCurrentUserFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal().equals("anonymousUser"))
            throw RestException.restThrow("OKa yopiq yul", HttpStatus.UNAUTHORIZED);

        return (Users) authentication.getPrincipal();
    }
}
