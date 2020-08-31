package com.oyorooms.sd.discovery.oam.services;

import com.oyorooms.sd.discovery.oam.entity.PartnerTextEntity;
import com.oyorooms.sd.discovery.oam.repositories.PartnerTextRepository;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.oyorooms.sd.discovery.common.constants.DiscoveryConstants.GENERIC_PAGES;
import static com.oyorooms.sd.discovery.common.constants.DiscoveryConstants.UNIVERSAL_STRING;

@Service
public class PartnerIdentifierService {

    @Autowired
    private PartnerTextRepository partnerTextRepository;

    public  PartnerTextEntity putIdentifierDb(PartnerTextEntity partnerTextEntity, String locale, String page,String country){
        HashMap<String,Long> result=new HashMap<>();

           partnerTextRepository.findByLocaleAndPageAndCountryIgnoreCase(locale,page,country).forEach(d->{
               result.put(d.getIdentifier(),d.getId());
           });
           if(result.size()==0){
               partnerTextRepository.findByLocaleAndPage(locale,page).forEach(d->{
                   result.put(d.getIdentifier(),d.getId());
               });
           }

           String identifier=partnerTextEntity.getIdentifier();
           String content=partnerTextEntity.getContent();
           if(result.containsKey(identifier)){
                partnerTextEntity = partnerTextRepository.findPartnerTextEntityById(result.get(identifier));
                partnerTextEntity.setContent(content);
                partnerTextRepository.save(partnerTextEntity);
                return partnerTextEntity;
           }

               PartnerTextEntity pt1 = new PartnerTextEntity();
               setParameters(pt1,content,page,country,locale,identifier);
               partnerTextRepository.save(pt1);
            return pt1;

        }
  private void setParameters(PartnerTextEntity pt1,String content, String page,String country,String locale,String identifier)
  {
      pt1.setCountry(country);
      pt1.setIdentifier(identifier);
      pt1.setLocale(locale);
      pt1.setPage(page);
      pt1.setContent(content);
  }
}
