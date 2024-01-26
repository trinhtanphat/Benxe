package com.example.BenXe.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BenXe.Model.ChuXe;
import com.example.BenXe.Repository.IChuXeRepository;

@Service
public class ChuXeService {
    @Autowired
    private IChuXeRepository chuXeRepository;
    public List<ChuXe> getAllChuXes(){
        return chuXeRepository.findAll();
    }
    public ChuXe getChuXeById(Long id){
        Optional<ChuXe> optional = chuXeRepository.findById(id);
        return optional.orElse(null);
    }
    public void save( ChuXe chuXe){
        chuXeRepository.save(chuXe);
    }
}
