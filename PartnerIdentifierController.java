package com.oyorooms.sd.discovery.oam.controller;

import co.elastic.apm.api.Traced;
import com.oyorooms.sd.discovery.oam.annotation.AuthorizeOam;
import com.oyorooms.sd.discovery.oam.entity.PartnerTextEntity;
import com.oyorooms.sd.discovery.oam.repositories.PartnerTextRepository;
import com.oyorooms.sd.discovery.oam.services.PartnerIdentifierService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping(value = "/discovery/api/v1/oam", produces = MediaType.APPLICATION_JSON_VALUE)
public class PartnerIdentifierController {

    @Autowired
    private PartnerIdentifierService partnerIdentifierService;

    @Traced
    //@AuthorizeOam
    @RequestMapping(value="/identifier/{locale}/{page}",method = RequestMethod.PUT)
    public PartnerTextEntity putIdentifier(@RequestBody PartnerTextEntity partnerTextEntity, @PathVariable("locale") String locale, @PathVariable("page") String page, @RequestParam(required = false) String country){
        return partnerIdentifierService.putIdentifierDb(partnerTextEntity,locale,page,country);
    }
}
