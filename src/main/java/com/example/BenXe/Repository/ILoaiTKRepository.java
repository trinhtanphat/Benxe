package com.example.BenXe.Repository;

import com.example.BenXe.Model.LoaiTK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoaiTKRepository extends JpaRepository<LoaiTK, Long> {
}