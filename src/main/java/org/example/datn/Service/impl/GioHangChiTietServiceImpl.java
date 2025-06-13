package org.example.datn.Service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.example.datn.Entity.GioHangChiTiet;
import org.example.datn.Repository.GioHangChiTietRepository;
import org.example.datn.Service.GioHangChiTietService;
import org.example.datn.dto.GioHangChiTietDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GioHangChiTietServiceImpl implements GioHangChiTietService {
    @Autowired
    private GioHangChiTietRepository repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<GioHangChiTietDTO> getGioHangByKhachHang(Integer khachHangId) {
        return repo.findByKhachHangIdAndTrangThai(khachHangId, true)
                .stream()
                .map(item -> mapper.map(item, GioHangChiTietDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void themVaoGio(GioHangChiTietDTO dto) {
        GioHangChiTiet entity = mapper.map(dto, GioHangChiTiet.class);
        entity.setNgayThem(LocalDateTime.now());
        entity.setTrangThai(true);
        repo.save(entity);
    }

    @Override
    public void capNhatSoLuong(Integer id, Integer soLuongMoi) {
        GioHangChiTiet item = repo.findById(id).orElseThrow();
        item.setSoLuong(soLuongMoi);
        repo.save(item);
    }

    @Override
    public void xoaGioHang(Integer id) {
        repo.deleteById(id);
    }
}
