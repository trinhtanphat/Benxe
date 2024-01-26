package com.example.BenXe.Validator;

import com.example.BenXe.Repository.ITaiKhoanRepository;

import com.example.BenXe.Validator.An.ValidUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidUsernameValidator implements ConstraintValidator<ValidUsername, String> {
    @Autowired
    private ITaiKhoanRepository taiKhoanRepository;

    @Override
    public boolean isValid(String Username, ConstraintValidatorContext Context) {
        return taiKhoanRepository.findByUsername(Username) ==null;
    }
}
