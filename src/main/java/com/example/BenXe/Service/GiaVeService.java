package com.example.BenXe.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BenXe.Model.GiaVe;
import com.example.BenXe.Model.LoaiXe;
import com.example.BenXe.Model.Tuyen;
import com.example.BenXe.Repository.IGiaVeRepository;

@Service
public class GiaVeService {
    @Autowired
    private IGiaVeRepository giaVeRepository;
    public List<GiaVe> getAllGiaVes(){
        return giaVeRepository.findAll();
    }
    public GiaVe getGiaVeById(Long id){
        Optional<GiaVe> optional =giaVeRepository.findById(id);
        return optional.orElse(null);
    }
    public void save( GiaVe giaVe){
        giaVeRepository.save(giaVe);
    }
    // public GiaVe FindIdByMaLXMaTuyen(Long maLX,Long maTuyen){
    //     return giaVeRepository.FindGiaVeIdByMaLXMaTuyen(maLX,maTuyen);
    // }
}
