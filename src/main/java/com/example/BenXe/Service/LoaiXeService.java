package com.example.BenXe.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BenXe.Model.LoaiXe;
import com.example.BenXe.Repository.ILoaiXeRepository;

@Service
public class LoaiXeService {
    @Autowired
    private ILoaiXeRepository loaiXeRepository;
    public List<LoaiXe> getAllLoaiXes(){
        return loaiXeRepository.findAll();
    }
    public LoaiXe getLoaiXeById(Long id){
        Optional<LoaiXe> optional =loaiXeRepository.findById(id);
        return optional.orElse(null);
    }
}
