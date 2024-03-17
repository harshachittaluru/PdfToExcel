package com.mac.harsha.pdfext.controller;

import java.io.File;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mac.harsha.pdfext.dto.ExtractDTO;
import com.mac.harsha.pdfext.service.ExtractPdfService;

@RestController
@RequestMapping("/api/v1/extractor")
@Validated
public class PdfExtractionApplication {

	private final ExtractPdfService extractPdfService = new ExtractPdfService();

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ExtractDTO> classify(@Valid @NotNull @RequestParam("file") final MultipartFile pdfFile) {
        return ResponseEntity.ok().body(ExtractDTO.builder().content(this.extractPdfService.extractContent(pdfFile)).build());
    }
}
