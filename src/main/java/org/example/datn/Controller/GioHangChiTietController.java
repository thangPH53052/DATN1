package org.example.datn.Controller;

import java.util.List;

import org.example.datn.Service.GioHangChiTietService;
import org.example.datn.dto.GioHangChiTietDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gio-hang")
public class GioHangChiTietController {
    @Autowired
    private GioHangChiTietService service;

    @GetMapping("/{khachHangId}")
    public ResponseEntity<List<GioHangChiTietDTO>> getGioHang(@PathVariable Integer khachHangId) {
        return ResponseEntity.ok(service.getGioHangByKhachHang(khachHangId));
    }

    @PostMapping
    public ResponseEntity<Void> themVaoGio(@RequestBody GioHangChiTietDTO dto) {
        service.themVaoGio(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> capNhat(@PathVariable Integer id, @RequestParam Integer soLuong) {
        service.capNhatSoLuong(id, soLuong);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> xoa(@PathVariable Integer id) {
        service.xoaGioHang(id);
        return ResponseEntity.ok().build();
    }
}
