package com.example.BenXe.Service;

import com.example.BenXe.Model.LoaiTK;
import com.example.BenXe.Repository.ILoaiTKRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiTKService {
    @Autowired
    private ILoaiTKRepository loaiTKRepository;
    public List<LoaiTK> getAllTaiKhoan(){
        return loaiTKRepository.findAll();
    }
    public LoaiTK getLoaiTkById(Long id){
        Optional<LoaiTK> optional =loaiTKRepository.findById(id);
        return optional.orElse(null);
    }
}
