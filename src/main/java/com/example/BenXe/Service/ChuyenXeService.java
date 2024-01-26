package com.example.BenXe.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BenXe.Model.ChuyenXe;
import com.example.BenXe.Repository.IChuyenXeRepository;

@Service
public class ChuyenXeService {
    @Autowired
    private IChuyenXeRepository chuyenXeRepository;
    public List<ChuyenXe> getAllChuyenXes(){
        return chuyenXeRepository.findAll();
    }
    public ChuyenXe getChuyenXeById(Long id){
        Optional<ChuyenXe> optional = chuyenXeRepository.findById(id);
        return optional.orElse(null);
    }
    public List<ChuyenXe> getChuyenXeByNgayChay(LocalDate ngayChay){
        return chuyenXeRepository.findAllbyNgayChay(ngayChay);
    }
    public void save( ChuyenXe chuyenXe){
        chuyenXeRepository.save(chuyenXe);
    }
}
