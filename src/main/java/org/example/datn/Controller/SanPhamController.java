package org.example.datn.Controller;

import java.util.List;

import org.example.datn.Service.SanPhamService;
import org.example.datn.dto.SanPhamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SanPhamController {

    private final SanPhamService sanPhamService;

    @Autowired
    public SanPhamController(SanPhamService sanPhamService) {
        this.sanPhamService = sanPhamService;
    }

    @GetMapping("/san-pham/view")
    public String viewSanPham(Model model) {
        List<SanPhamDTO> list = sanPhamService.getAllSanPham();
        model.addAttribute("sanPhams", list);
        return "ViewSanPham/index"; 
    }
}