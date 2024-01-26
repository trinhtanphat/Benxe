package com.example.BenXe.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BenXe.Model.BaiDauXe;
import com.example.BenXe.Repository.IBaiDauXeRepository;

@Service
public class BaiDauXeService {
    @Autowired
    private IBaiDauXeRepository baiDauXeRepository;
    public List<BaiDauXe> getAllBaiDauXes(){
        return baiDauXeRepository.findAll();
    }
    public BaiDauXe getBaiDauXeById(Long id){
        Optional<BaiDauXe> optional = baiDauXeRepository.findById(id);
        return optional.orElse(null);
    }
    public void save(BaiDauXe baiDauXe){
        baiDauXeRepository.save(baiDauXe);
    }
}
