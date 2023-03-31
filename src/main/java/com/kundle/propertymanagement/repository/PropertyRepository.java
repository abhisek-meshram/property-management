package com.kundle.propertymanagement.repository;

import com.kundle.propertymanagement.entity.PropertyEntity;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<PropertyEntity,Long> {
    void deleteById(Long propertyId);
    //CrudRepository<entityClass,PrimaryKeyDataType>
}
