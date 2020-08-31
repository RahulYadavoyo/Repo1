package com.oyorooms.sd.discovery.oam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.oyorooms.sd.discovery.oam.entity.PartnerTextEntity;

@Repository
public interface PartnerTextRepository extends JpaRepository<PartnerTextEntity,Long> {


    List<PartnerTextEntity> findByLocaleAndPage(String locale, String page);
    List<PartnerTextEntity> findByLocaleAndPageAndCountryIgnoreCase(String locale, String page, String country);
    PartnerTextEntity findPartnerTextEntityById(Long id);

}
