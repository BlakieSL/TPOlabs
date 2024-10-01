package com.example.lab10.Service;

import com.example.lab10.Model.Url;
import com.example.lab10.Model.UrlRequestDTO;
import com.example.lab10.Model.UrlResponseDTO;
import com.example.lab10.Repository.UrlRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class UrlService {
    private final UrlRepository urlRepository;
    private final DtoMapper mapper;
    public UrlService(UrlRepository urlRepository, DtoMapper mapper) {
        this.urlRepository = urlRepository;
        this.mapper = mapper;
    }
    private String createUrl(){
        return RandomStringUtils.random(10,true,false);
    }
    public void plusVisit(String urlId) {
        urlRepository.findUrlByUrlId(urlId).ifPresent(url -> {
            url.setVisits(url.getVisits() + 1);
            urlRepository.save(url);
        });
    }
    public boolean checkCorrectPassword(String urlId, String password){
        return urlRepository.findUrlByUrlId(urlId).map(url -> url.getPassword().equals(password)).orElse(false);
    }

    public UrlResponseDTO saveUrl(UrlRequestDTO urlDTO) {
        Url url = mapper.map(urlDTO);
        url.setUrlId(createUrl());
        url.setRedirectUrl( "http://localhost:8080/red/" + url.getUrlId());
        url.setVisits(0);
        Url savedUrl = urlRepository.save(url);
        return mapper.map(savedUrl);
    }
    public Iterable<UrlResponseDTO> getUrls(){
        return StreamSupport.stream(urlRepository.findAll().spliterator(),false)
                .map(mapper::map).toList();
    }
    public Optional<UrlResponseDTO> getUrlById(String urlId) {
        return urlRepository.findUrlByUrlId(urlId).map(mapper::map);
    }
    public Optional<UrlResponseDTO> updateUrl(UrlRequestDTO urlDTO, String urlId){
        Optional<Url> urlOpt = urlRepository.findUrlByUrlId(urlId);
        if (urlOpt.isPresent()) {
            Url url = urlOpt.get();
            if (urlDTO.getName() != null) {
                url.setName(urlDTO.getName());
            }
            if (urlDTO.getTargetUrl() != null) {
                url.setTargetUrl(urlDTO.getTargetUrl());
            }
            Url updatedUrl = urlRepository.save(url);
            return Optional.of(mapper.map(updatedUrl));
        } else {
            return Optional.empty();
        }
    }
    @Transactional
    public void deleteUrl(String urlId){
        urlRepository.deleteUrlByUrlId(urlId);
    }
}
