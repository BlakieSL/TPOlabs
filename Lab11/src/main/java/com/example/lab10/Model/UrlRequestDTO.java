package com.example.lab10.Model;

import com.example.lab10.Constraint.*;
import com.example.lab10.Repository.UrlRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UrlRequestDTO {
    @NotNull
    @UrlHttpsDomain
    @UrlUniqueDomain()
    private String targetUrl;
    @NotNull
    @Size(min = 5, max = 20)
    private String name;

    @PasswordSizeDomain()
    @PasswordLowercaseDomain()
    @PasswordUppercaseDomain()
    @PasswordDigitsDomain()
    @PasswordSpecialDomain()
    private String password;
    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
