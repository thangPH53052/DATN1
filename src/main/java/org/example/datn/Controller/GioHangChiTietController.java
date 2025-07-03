package org.example.datn.Controller;

import java.util.List;

import org.example.datn.Service.GioHangChiTietService;
import org.example.datn.dto.GioHangChiTietDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gio-hang")
public class GioHangChiTietController {

    @Autowired
    private GioHangChiTietService service;

    // ✅ Trả về giao diện View.html
    @GetMapping("/ban-hang/view")
    public String hienThiGiaoDienBanHang() {
        return "ViewBanHang/View"; // Tương ứng templates/ViewBanHang/View.html
    }

    // ✅ Các API trả về JSON nên thêm @ResponseBody
    @GetMapping("/{khachHangId}")
    @ResponseBody
    public ResponseEntity<List<GioHangChiTietDTO>> getGioHang(@PathVariable Integer khachHangId) {
        return ResponseEntity.ok(service.getGioHangByKhachHang(khachHangId));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Void> themVaoGio(@RequestBody GioHangChiTietDTO dto) {
        service.themVaoGio(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> capNhat(@PathVariable Integer id, @RequestParam Integer soLuong) {
        service.capNhatSoLuong(id, soLuong);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> xoa(@PathVariable Integer id) {
        service.xoaGioHang(id);
        return ResponseEntity.ok().build();
    }
}
