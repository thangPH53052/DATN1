package org.example.datn.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.example.datn.Entity.HinhAnhSanPham;
import org.example.datn.Entity.SanPham;
import org.example.datn.Repository.SanPhamRepository;
import org.example.datn.dto.SanPhamDTO;
import org.springframework.stereotype.Service;

@Service
public class SanPhamService {
    private final SanPhamRepository sanPhamRepository;

    public SanPhamService(SanPhamRepository sanPhamRepository) {
        this.sanPhamRepository = sanPhamRepository;
    }

    public List<SanPhamDTO> getAllSanPham() {
        return sanPhamRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private SanPhamDTO mapToDTO(SanPham sp) {
        SanPhamDTO dto = new SanPhamDTO();
        dto.setId(sp.getId());
        dto.setMa(sp.getMa());
        dto.setTen(sp.getTen());
        dto.setTrangThai(sp.getTrangThai());
        dto.setTenThuongHieu(sp.getThuongHieu() != null ? sp.getThuongHieu().getTen() : null);
        dto.setTenDanhMuc(sp.getDanhMuc() != null ? sp.getDanhMuc().getTen() : null);
        dto.setTenChatLieu(sp.getChatLieu() != null ? sp.getChatLieu().getTen() : null);
        dto.setTenLoaiKhoa(sp.getLoaiKhoa() != null ? sp.getLoaiKhoa().getTen() : null);
        dto.setTenKieuDay(sp.getKieuDay() != null ? sp.getKieuDay().getTen() : null);

        if (sp.getHinhAnhList() != null) {
            List<String> urls = sp.getHinhAnhList().stream()
                    .map(HinhAnhSanPham::getUrl) // ví dụ: anh1.png
                    .collect(Collectors.toList());
            dto.setHinhAnhUrls(urls);
        }

        return dto;
    }
}
