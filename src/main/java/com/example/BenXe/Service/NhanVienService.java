package com.example.BenXe.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.BenXe.Model.NhanVien;
import com.example.BenXe.Repository.INhanVienRepository;

@Service
public class NhanVienService {
    @Autowired
    private INhanVienRepository nhanVienRepository;

    public void updateNhanVien(Long id, NhanVien nv) {
        Optional<NhanVien> optional = nhanVienRepository.findById(id);
        optional.get().setDiaChi(nv.getDiaChi());
        optional.get().setEmail(nv.getEmail());
        optional.get().setNamSinh(nv.getNamSinh());
        optional.get().setSDT(nv.getSDT());
        optional.get().setTenNV(nv.getTenNV());
        optional.get().setChucVu(nv.getChucVu());
        optional.get().setPhongBan(nv.getPhongBan());

        nhanVienRepository.save(optional.get());
    }

    public List<NhanVien> getAllNhanVien() {
        return nhanVienRepository.findAll();
    }

    public List<NhanVien> getAllNhanVien(Integer pageNo,
                                         Integer pageSize,
                                         String sortBy) {
        return nhanVienRepository.findAllNhanViens(pageNo, pageSize, sortBy);
    }

    public NhanVien getNhanVienById(Long id) {
        Optional<NhanVien> optional = nhanVienRepository.findById(id);
        return optional.orElse(null);
    }

    public NhanVien getNhanVienByMaNV(Long maNV) {
        List<NhanVien> list = nhanVienRepository.findAll();
        for (NhanVien item : list) {
            if (item.getMaNV() == maNV) {
                return item;
            }
        }
        return null;
    }

    public void deleteNhanVienByMaNV(Long maNV) {
        nhanVienRepository.deleteById(maNV);
    }

    public void save(NhanVien nhanVien) {
        nhanVienRepository.save(nhanVien);
    }

//    @PreAuthorize("hasAnyAuthority('USER') or hasAnyAuthority('ADMIN')")
    public List<NhanVien> searchNhanVien(String keyword){
        return nhanVienRepository.searchNhanVien(keyword);
    }

    public void deleteNhanVienById(Long maNV) {
        // Kiểm tra xem nhân viên có tồn tại trong cơ sở dữ liệu không
        Optional<NhanVien> optionalNhanVien = nhanVienRepository.findById(maNV);
        if (optionalNhanVien.isPresent()) {
            // Xóa nhân viên nếu tồn tại
            NhanVien nhanVien = optionalNhanVien.get();
            nhanVienRepository.delete(nhanVien);
        }
    }
}