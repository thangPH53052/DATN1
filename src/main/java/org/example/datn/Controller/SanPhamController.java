package org.example.datn.Controller;

import java.util.List;
import org.example.datn.Service.*;
import org.example.datn.dto.SanPhamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/san-pham")
public class SanPhamController {

    @Autowired private SanPhamService sanPhamService;
    @Autowired private DanhMucService danhMucService;
    @Autowired private ThuongHieuService thuongHieuService;
    @Autowired private ChatLieuService chatLieuService;
    @Autowired private LoaiKhoaService loaiKhoaService;
    @Autowired private KieuDayService kieuDayService;

    // Hiển thị danh sách sản phẩm
    @GetMapping("/view")
    public String viewSanPham(Model model) {
        List<SanPhamDTO> list = sanPhamService.getAllSanPham();
        model.addAttribute("sanPhams", list);
        return "ViewSanPham/index";
    }

    // Hiển thị form thêm sản phẩm
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("danhMucs", danhMucService.getAllDanhMuc());
        model.addAttribute("thuongHieus", thuongHieuService.getAllThuongHieu());
        model.addAttribute("chatLieus", chatLieuService.getAllChatLieu());
        model.addAttribute("loaiKhoas", loaiKhoaService.getAllLoaiKhoa());
        model.addAttribute("kieuDays", kieuDayService.getAllKieuDay());
        return "ViewSanPham/add";
    }

    // Lưu sản phẩm
    @PostMapping("/save")
    public String saveSanPham(
            @RequestParam("ma") String ma,
            @RequestParam("ten") String ten,
            @RequestParam("idDanhMuc") Integer idDanhMuc,
            @RequestParam("idChatLieu") Integer idChatLieu,
            @RequestParam("idLoaiKhoa") Integer idLoaiKhoa,
            @RequestParam("idKieuDay") Integer idKieuDay,
            @RequestParam("idThuongHieu") Integer idThuongHieu,
            @RequestParam(value = "moTa", required = false) String moTa,
            @RequestParam(value = "canNang", required = false) Float canNang,
            @RequestParam(value = "dungTich", required = false) Float dungTich,
            @RequestParam(value = "kichThuoc", required = false) String kichThuoc,
            @RequestParam("trangThai") Boolean trangThai,
            @RequestParam("hinhAnhs") MultipartFile[] hinhAnhs
    ) {
        sanPhamService.addSanPham(ma, ten, idDanhMuc, idChatLieu, idLoaiKhoa, idKieuDay, idThuongHieu,
                moTa, canNang, dungTich, kichThuoc, trangThai, hinhAnhs);

        return "redirect:/san-pham/view";
    }
}
