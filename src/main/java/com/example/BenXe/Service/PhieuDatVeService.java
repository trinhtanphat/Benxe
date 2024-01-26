package com.example.BenXe.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BenXe.Model.PhieuDatVe;
import com.example.BenXe.Repository.IPhieuDatVeRepository;

@Service
public class PhieuDatVeService {
    @Autowired
    private IPhieuDatVeRepository phieuDatVeRepository;
    public List<PhieuDatVe> getAllPhieuDatVes(){
        return phieuDatVeRepository.findAll();
    }
    public PhieuDatVe getPhieuDatVeById(Long id){
        Optional<PhieuDatVe> optional = phieuDatVeRepository.findById(id);
        return optional.orElse(null);
    }
    public void save(PhieuDatVe phieuDatVe){
        phieuDatVeRepository.save(phieuDatVe);
    }
}
