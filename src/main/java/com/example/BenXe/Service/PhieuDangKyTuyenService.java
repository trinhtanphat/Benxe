package com.example.BenXe.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BenXe.Model.PhieuDangKyTuyen;
import com.example.BenXe.Repository.IPhieuDangKyTuyenRepository;

@Service
public class PhieuDangKyTuyenService {
    @Autowired
    private IPhieuDangKyTuyenRepository phieuDangKyTuyenRepository;
    public List<PhieuDangKyTuyen> getAllPhieuDangKyTuyens(){
        return phieuDangKyTuyenRepository.findAll();
    }
    public PhieuDangKyTuyen getPhieuDanhKyTuyenById(Long id){
        Optional<PhieuDangKyTuyen> optional =phieuDangKyTuyenRepository.findById(id);
        return optional.orElse(null);
    }
    public void save( PhieuDangKyTuyen phieuDangKyTuyen){
        phieuDangKyTuyenRepository.save(phieuDangKyTuyen);
        
    }
}
