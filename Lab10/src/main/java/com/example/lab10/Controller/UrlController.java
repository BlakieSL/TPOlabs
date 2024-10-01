package com.example.lab10.Controller;

import com.example.lab10.Model.Url;
import com.example.lab10.Model.UrlRequestDTO;
import com.example.lab10.Model.UrlResponseDTO;
import com.example.lab10.Service.UrlService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.annotations.NotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(
produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
public class UrlController {
    private final UrlService urlService;
    private final ObjectMapper objectMapper;
    public UrlController(UrlService urlService, ObjectMapper objectMapper) {
        this.urlService = urlService;
        this.objectMapper = objectMapper;
    }
    private UrlRequestDTO applyPatch(UrlResponseDTO dto, JsonMergePatch patch) throws JsonProcessingException, JsonPatchException {
        JsonNode urlNode = objectMapper.valueToTree(dto);
        JsonNode patchNode = patch.apply(urlNode);
        return objectMapper.treeToValue(patchNode, UrlRequestDTO.class);
    }

    @Tag(name = "GET", description = "get link information")
    @GetMapping("/api/links/{urlId}")
    public ResponseEntity<UrlResponseDTO> getUrl(@PathVariable String urlId){
        return urlService.getUrlById(urlId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Tag(name = "GET", description = "redirect")
    @GetMapping("/red/{urlId}")
    public ResponseEntity<?> getRedirect(@PathVariable String urlId) {
        return urlService.getUrlById(urlId)
                .map(urlDTO -> {
                    urlService.plusVisit(urlId);
                    return ResponseEntity.status(302).location(URI.create(urlDTO.getTargetUrl())).build();
                })
                .orElse(ResponseEntity.notFound().build());

    }

    @Tag(name = "POST", description = "new link")
    @PostMapping("/api/links/")
    public ResponseEntity<UrlResponseDTO> saveUrl(@RequestBody UrlRequestDTO urlRequestDTO){

        UrlResponseDTO urlResponseDTO = urlService.saveUrl(urlRequestDTO);
        URI savedUrlLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{urlId}")
                .buildAndExpand(urlResponseDTO.getUrlId())
                .toUri();
        return ResponseEntity.created(savedUrlLocation).body(urlResponseDTO);
    }

    @Tag(name = "PATCH", description = "update")
    @PatchMapping("/api/links/{urlId}")
    public ResponseEntity<?> updateUrl(@PathVariable String urlId, @RequestBody JsonMergePatch patch){
        try{
            UrlResponseDTO urlResponseDTO = urlService.getUrlById(urlId).orElseThrow();

            UrlRequestDTO urlRequestDTOpatched = applyPatch(urlResponseDTO, patch);

            if(urlRequestDTOpatched.getPassword() == null
                    || !urlService.checkCorrectPassword(urlId,urlRequestDTOpatched.getPassword())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("wrong password");
            }

            urlService.updateUrl(urlRequestDTOpatched, urlId);
        }catch (NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.noContent().build();
    }

    @Tag(name = "DELETE", description = "Remove")
    @DeleteMapping("/api/links/{urlId}")
    ResponseEntity<?> deleteUrl(@PathVariable String urlId, @RequestHeader("pass") String password) {
        if (password == null || !urlService.checkCorrectPassword(urlId, password)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("wrong password");
        }
        urlService.deleteUrl(urlId);
        return ResponseEntity.noContent().build();
    }
}
