package org.example.datn.Controller;

import java.util.List;
import java.util.Map;

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

    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private DanhMucService danhMucService;
    @Autowired
    private ThuongHieuService thuongHieuService;
    @Autowired
    private ChatLieuService chatLieuService;
    @Autowired
    private LoaiKhoaService loaiKhoaService;
    @Autowired
    private KieuDayService kieuDayService;

    @Autowired
    private SanPhamChiTietService sanPhamChiTietService;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    private KichThuocService kichThuocService;
    @Autowired
    private KhuyenMaiService khuyenMaiService;

    // Hiển thị danh sách sản phẩm
    @GetMapping("/view")
    public String viewSanPham(Model model) {
        List<SanPhamDTO> list = sanPhamService.getAllSanPham();
        model.addAttribute("sanPhams", list);

        model.addAttribute("danhMucs", danhMucService.getAllDanhMuc());
        model.addAttribute("thuongHieus", thuongHieuService.getAllThuongHieu());
        model.addAttribute("chatLieus", chatLieuService.getAllChatLieu());
        model.addAttribute("loaiKhoas", loaiKhoaService.getAllLoaiKhoa());
        model.addAttribute("kieuDays", kieuDayService.getAllKieuDay());

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

    // ✅ Lưu sản phẩm qua AJAX, trả về ID sản phẩm mới
    @PostMapping("/api/save")
    @ResponseBody
    public Map<String, Integer> apiSaveSanPham(
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
            @RequestParam("hinhAnhs") MultipartFile[] hinhAnhs) {

        Integer idSanPham = sanPhamService.addSanPham(ma, ten, idDanhMuc, idChatLieu, idLoaiKhoa, idKieuDay,
                idThuongHieu, moTa, canNang, dungTich, kichThuoc, trangThai, hinhAnhs);

        return Map.of("id", idSanPham); // Trả JSON để JS đọc đúng
    }

    // API lấy chi tiết sản phẩm để đổ vào form (sử dụng AJAX)
    @GetMapping("/api/{id}")
    @ResponseBody
    public SanPhamDTO getSanPhamDTOById(@PathVariable("id") Integer id) {
        return sanPhamService.getSanPhamDTOById(id);
    }

    // Hiển thị form cập nhật sản phẩm (update.html)
    @GetMapping("/edit/{id}")
public String showEditForm(@PathVariable("id") Integer id, Model model) {
    SanPhamDTO sanPhamDTO = sanPhamService.getSanPhamDTOById(id);
    if (sanPhamDTO == null) {
        return "redirect:/san-pham/view";
    }

    model.addAttribute("sanPham", sanPhamDTO);
    model.addAttribute("danhMucs", danhMucService.getAllDanhMuc());
    model.addAttribute("thuongHieus", thuongHieuService.getAllThuongHieu());
    model.addAttribute("chatLieus", chatLieuService.getAllChatLieu());
    model.addAttribute("loaiKhoas", loaiKhoaService.getAllLoaiKhoa());
    model.addAttribute("kieuDays", kieuDayService.getAllKieuDay());

    // ✅ Thêm phần dữ liệu để cập nhật chi tiết sản phẩm
    model.addAttribute("chiTiets", sanPhamChiTietService.getBySanPhamId(id));
    model.addAttribute("mauSacs", mauSacService.getAllMauSac());
    model.addAttribute("kichThuocs", kichThuocService.getAllKichThuoc());
    model.addAttribute("khuyenMais", khuyenMaiService.getAllKhuyenMai());

    return "ViewSanPham/update";
}


    // Cập nhật sản phẩm
    @PostMapping("/update/{id}")
    public String updateSanPham(
            @PathVariable("id") Integer id,
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
            @RequestParam("hinhAnhs") MultipartFile[] hinhAnhs) {
        sanPhamService.updateSanPham(id, ma, ten, idDanhMuc, idChatLieu, idLoaiKhoa, idKieuDay, idThuongHieu,
                moTa, canNang, dungTich, kichThuoc, trangThai, hinhAnhs);
        return "redirect:/san-pham/view";
    }
}
