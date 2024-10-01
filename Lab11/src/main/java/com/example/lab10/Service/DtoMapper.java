package com.example.lab10.Service;

import com.example.lab10.Model.Url;
import com.example.lab10.Model.UrlRequestDTO;
import com.example.lab10.Model.UrlResponseDTO;
import com.example.lab10.Repository.UrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class DtoMapper {
    public UrlResponseDTO map(Url url){
        UrlResponseDTO urlDTO = new UrlResponseDTO();
        urlDTO.setUrlId(url.getUrlId());
        urlDTO.setName(url.getName());
        urlDTO.setTargetUrl(url.getTargetUrl());
        urlDTO.setRedirectUrl(url.getRedirectUrl());
        urlDTO.setVisits(url.getVisits());
        return urlDTO;
    }
    public Url map(UrlRequestDTO urlDTO){
        Url url = new Url();
        url.setName(urlDTO.getName());
        url.setTargetUrl(urlDTO.getTargetUrl());
        url.setPassword(urlDTO.getPassword());
        return url;
    }
}
