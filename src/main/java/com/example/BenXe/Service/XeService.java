package com.example.BenXe.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BenXe.Model.Xe;
import com.example.BenXe.Repository.IXeRepository;

@Service
public class XeService {
    @Autowired
    private IXeRepository xeRepository;

    public List<Xe> getAllXes() {
        return xeRepository.findAll();
    }

    public Xe getXeById(Long id) {
        Optional<Xe> optional = xeRepository.findById(id);
        return optional.orElse(null);
    }

    public void save(Xe xe) {
        xeRepository.save(xe);
    }

    public List<Xe> getXeByChuXeId(Long id) {
        List<Xe> list = xeRepository.findAll();
        for (Xe xe: list) {
            if (xe.getChuXe().getMaCX() != id) {
                list.remove(xe);
            }
        }
        return list;
    }
}