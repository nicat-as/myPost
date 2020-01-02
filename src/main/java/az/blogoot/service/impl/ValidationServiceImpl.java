package az.blogoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.blogoot.repository.ValidationRepository;
import az.blogoot.service.ValidationService;

/**
 * ValidationServiceImpl
 */

 @Service
public class ValidationServiceImpl implements ValidationService{

    @Autowired 
    private ValidationRepository validationRepository;

    @Override
    public boolean isDuplicate(String email) {
       return validationRepository.isDuplicate(email);
    }

    
}