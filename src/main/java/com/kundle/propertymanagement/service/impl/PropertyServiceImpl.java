package com.kundle.propertymanagement.service.impl;

import com.kundle.propertymanagement.converter.PropertyConverter;
import com.kundle.propertymanagement.dto.PropertyDTO;
import com.kundle.propertymanagement.entity.PropertyEntity;
import com.kundle.propertymanagement.repository.PropertyRepository;
import com.kundle.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyConverter propertyConverter;
    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
//not a good practice to write here if more data is present so converter class is created
// good practice controller -> service -> converter -> service -> repository(if exist) -> controller
//        PropertyEntity pe = new PropertyEntity();
//        pe.setTitle(propertyDTO.getTitle());
//        pe.setAddress(propertyDTO.getAddress());
//        pe.setOwnerEmail(propertyDTO.getOwnerEmail());
//        pe.setOwnerName(propertyDTO.getOwnerName());
//        pe.setPrice(propertyDTO.getPrice());
//        pe.setDescription(propertyDTO.getDescription());

        //Adapter Design Pattern - compatibility conversion (dto to entity and vice versa)
        PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);
        pe = propertyRepository.save(pe);
        propertyDTO = propertyConverter.convertEntityToDTO(pe);
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyEntity> propertyEntityList = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propList = new ArrayList<>();

        for(PropertyEntity pe: propertyEntityList) {
            PropertyDTO dto = propertyConverter.convertEntityToDTO(pe);
            propList.add(dto);
        }
        return propList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalEn = propertyRepository.findById(propertyId);
        if(optionalEn.isPresent()) {
            PropertyEntity pe = optionalEn.get();

            pe.setTitle(propertyDTO.getTitle());
            pe.setAddress(propertyDTO.getAddress());
            pe.setOwnerEmail(propertyDTO.getOwnerEmail());
            pe.setOwnerName(propertyDTO.getOwnerName());
            pe.setPrice(propertyDTO.getPrice());
            pe.setDescription(propertyDTO.getDescription());
            propertyDTO = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe);
        }
        return propertyDTO;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalEn = propertyRepository.findById(propertyId);
        if(optionalEn.isPresent()) {
            PropertyEntity pe = optionalEn.get();
            pe.setDescription(propertyDTO.getDescription());
            propertyDTO = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe);
        }
        return propertyDTO;
    }

    @Override
    public PropertyDTO updatePrice(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalEn = propertyRepository.findById(propertyId);
        if(optionalEn.isPresent()) {
            PropertyEntity pe = optionalEn.get();
            pe.setPrice(propertyDTO.getPrice());
            propertyDTO = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe);
        }
        return propertyDTO;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }
}
/* Hibernate: create table property_entity (id bigint not null, address varchar(255), description varchar(255), property_email varchar(255) not null, owner_name varchar(255), price double, property_title varchar(255) not null, primary key (id))
Hibernate: create sequence hibernate_sequence start with 1 increment by 1  */