package com.example.BenXe.Service;

import com.example.BenXe.Model.KhachHang;
import com.example.BenXe.Repository.IKhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhachHangService {
    @Autowired
    private IKhachHangRepository khachHangRepository;
    public List<KhachHang> getAllKhachHang(){
        return khachHangRepository.findAll();
    }
    public KhachHang getKhachHangById(Long id){
        Optional<KhachHang> optional =khachHangRepository.findById(id);
        return optional.orElse(null);
    }
    public void save( KhachHang khachHang){
        khachHangRepository.save(khachHang);
    }
}
