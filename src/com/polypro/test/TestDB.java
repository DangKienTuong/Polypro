/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polypro.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Tường Ngao Tạng
 */
public class TestDB {

    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Polypro";
            Connection con = DriverManager.getConnection(url, "sa", "songlong");
            Statement st = con.createStatement();
            //Thêm mới 5 nhân viên
            st.executeUpdate("INSERT INTO NhanVien (MaNV, MatKhau, HoTen, VaiTro) VALUES ('NV03', '456', 'Tran Thi C', 0)");
            st.executeUpdate("INSERT INTO NhanVien (MaNV, MatKhau, HoTen, VaiTro) VALUES ('NV04', '654', 'Tran Thi D', 0)");
            st.executeUpdate("INSERT INTO NhanVien (MaNV, MatKhau, HoTen, VaiTro) VALUES ('NV05', '789', 'Phan Van Tu', 1)");
            st.executeUpdate("INSERT INTO NhanVien (MaNV, MatKhau, HoTen, VaiTro) VALUES ('NV06', '987', 'Doan Van Hau', 1)");
            st.executeUpdate("INSERT INTO NhanVien (MaNV, MatKhau, HoTen, VaiTro) VALUES ('NV07', '971', 'Ronaldo', 0)");
            //Cập nhật nhân viên theo mã
            st.executeUpdate("UPDATE NhanVien SET MatKhau='2411', HoTen='Pham Tran Thanh Nhan', VaiTro=0 WHERE MaNV='NV01'");
            //Xóa 1 nhân viên theo mã
            st.executeUpdate("DELETE FROM NhanVien WHERE MaNV='NV03'");
            //Truy vấn tất cả
            Statement statement = con.createStatement();
            String query = "SELECT * FROM NhanVien";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String maNV = resultSet.getString("MaNV");
                String matKhau = resultSet.getString("MatKhau");
                String hoTen = resultSet.getString("HoTen");
                boolean isAdmin = resultSet.getBoolean("VaiTro");
                String vaitro = null;
                if (isAdmin) {
                    vaitro = "Admin";
                } else {
                    vaitro = "Nhan vien";
                }
                System.out.println("\tMã nhân viên: " + maNV
                        + ", mật khẩu: " + matKhau
                        + ", họ tên: " + hoTen
                        + ", vai tro: " + vaitro
                );
                //Gọi thủ tục lưu sp_ThongKeNguoiHoc() và xuất thông tin thống kê ra màn hình
                String Query = "EXECUTE  sp_ThongKeNguoiHoc";
                ResultSet resultset = statement.executeQuery(Query);
                while (resultset.next()) {
                    String nam = resultset.getString("Nam");
                    int soLuong = resultset.getInt("SoLuong");
                    String dauTien = resultset.getString("DauTien");
                    String cuoicung = resultset.getString("CuoiCung");
                    System.out.println("\tNăm: " + nam
                            + ", số lượng: " + soLuong
                            + ", đầu tiên: " + dauTien
                            + ", cuối cùng: " + cuoicung
                    );
                }
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
