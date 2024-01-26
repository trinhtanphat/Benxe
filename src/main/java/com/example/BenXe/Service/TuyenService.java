package com.example.BenXe.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BenXe.Model.Tuyen;
import com.example.BenXe.Repository.ITuyenRepository;

@Service
public class TuyenService {
    @Autowired
    private ITuyenRepository tuyenRepository;

    public List<Tuyen> getAllTuyens() {
        return tuyenRepository.findAll();
    }

    public List<String> getDiemDi() {
        return tuyenRepository.getDiemDi();
    }

    public List<String> getDiemDen() {
        return tuyenRepository.getDiemDen();
    }

    public Tuyen getTuyenById(Long id) {
        Optional<Tuyen> optional = tuyenRepository.findById(id);
        return optional.orElse(null);
    }

    public void save(Tuyen tuyen) {
        tuyenRepository.save(tuyen);
    }
}