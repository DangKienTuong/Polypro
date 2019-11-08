/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polypro.test;

import com.polypro.helper.JdbcHelper;
import com.polypro.model.NguoiHoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tường Ngao Tạng
 */
public class TestDAO {

    public static void main(String[] args) {
        //Thêm mới 5 người học
        String sql = "INSERT INTO NguoiHoc (MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql, "NH10", "Tran Thi B", "07/03/99", false, "0965935612", "tranthib@gmail.com", "", "TeoNV");
        JdbcHelper.executeUpdate(sql, "NH11", "nguyen vu hao", "08/05/95", true, "032870105", "nguyenvuhao@gmail.com", "", "TeoNV");
        JdbcHelper.executeUpdate(sql, "NH12", "Ton luong", "07/10/98", false, "032760196", "tonluong@gmail.com", "", "TeoNV");
        JdbcHelper.executeUpdate(sql, "NH13", "ho sy lap", "04/06/99", true, "0336785699", "hosylap@gmail.com", "", "TeoNV");
        JdbcHelper.executeUpdate(sql, "NH14", "hao khoi", "01/01/97", false, "0326597827", "haokhoi113@gmail.com", "", "TeoNV");

        //Cập nhật thông tin người học theo mã
        String sql1 = "UPDATE NguoiHoc SET HoTen=?, NgaySinh=?, GioiTinh=?, DienThoai=?, Email=?, GhiChu=?, MaNV=? WHERE MaNH=?";
        JdbcHelper.executeUpdate(sql1, "hao khoi", "01/01/97", false, "0326597827", "haokhoi113@gmail.com", "", "TeoNV", "NH01");

        //Xóa người học theo mã
        String sql2 = "DELETE FROM NguoiHoc WHERE MaNH=?";
        JdbcHelper.executeUpdate(sql, "NH02");

    }

    //Truy vấn tất cả người học
    public List<NguoiHoc> select() {
        String sql = "SELECT * FROM NguoiHoc";
        return select(sql);
    }

    //Truy vấn người học theo mã
    public NguoiHoc findById(String manh) {
        String sql = "SELECT * FROM NguoiHoc WHERE MaNH=?";
        List<NguoiHoc> list = select(sql, manh);
        return list.size() > 0 ? list.get(0) : null;
    }

    //Gọi thủ tục luu sp_ThongKeNguoiHoc()
    public List<NguoiHoc> procedure() {
        String sql = "EXECUTE  sp_ThongKeNguoiHoc";
        return select(sql);
    }

    private List<NguoiHoc> select(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    NguoiHoc model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private NguoiHoc readFromResultSet(ResultSet rs) throws SQLException {
        NguoiHoc model = new NguoiHoc();
        model.setMaNH(rs.getString("MaNH"));
        model.setHoTen(rs.getString("HoTen"));
        model.setNgaySinh(rs.getDate("NgaySinh"));
        model.setGioiTinh(rs.getBoolean("GioiTinh"));
        model.setDienThoai(rs.getString("DienThoai"));
        model.setEmail(rs.getString("Email"));
        model.setGhiChu(rs.getString("GhiChu"));
        model.setMaNV(rs.getString("MaNV"));
        model.setNgayDK(rs.getDate("NgayDK"));
        return model;
    }

}
