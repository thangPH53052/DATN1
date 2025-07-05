package org.example.datn.Controller;

import java.util.List;

import org.example.datn.Service.GioHangChiTietService;
import org.example.datn.dto.GioHangChiTietDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ban-hang")
public class GioHangChiTietController {

    @Autowired
    private GioHangChiTietService service;

    // ✅ Các API vẫn dùng @ResponseBody
    @GetMapping("/api/gio-hang/{khachHangId}")
    @ResponseBody
    public ResponseEntity<List<GioHangChiTietDTO>> getGioHang(@PathVariable Integer khachHangId) {
        return ResponseEntity.ok(service.getGioHangByKhachHang(khachHangId));
    }

    @PostMapping("/api/gio-hang")
    @ResponseBody
    public ResponseEntity<Void> themVaoGio(@RequestBody GioHangChiTietDTO dto) {
        service.themVaoGio(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/gio-hang/{id}")
    @ResponseBody
    public ResponseEntity<Void> capNhat(@PathVariable Integer id, @RequestParam Integer soLuong) {
        service.capNhatSoLuong(id, soLuong);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/gio-hang/{id}")
    @ResponseBody
    public ResponseEntity<Void> xoa(@PathVariable Integer id) {
        service.xoaGioHang(id);
        return ResponseEntity.ok().build();
    }
}
