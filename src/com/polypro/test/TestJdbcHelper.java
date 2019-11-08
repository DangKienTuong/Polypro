/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polypro.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Tường Ngao Tạng
 */
public class TestJdbcHelper {

    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Polypro";
            Connection con = DriverManager.getConnection(url, "sa", "songlong");
            Statement st = con.createStatement();

            //Thêm mới 5 người học
            st.executeUpdate("INSERT INTO NguoiHoc (MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV, NgayDK) VALUES ('NH02', 'Tran Thi B', '07/03/1999', 1, '0965935612', 'mjhjmh@gmail.com', '', 'TeoNV', '10/9/2019')");
            st.executeUpdate("INSERT INTO NguoiHoc (MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV, NgayDK) VALUES ('NH03', 'Chau Chi Cuong', '09/02/1997', 0, '0723654125', 'nbfgd@gmail.com', '', 'TeoNV', '02/10/2019')");
            st.executeUpdate("INSERT INTO NguoiHoc (MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV, NgayDK) VALUES ('NH04', 'Van Dijk', '05/06/1992', 0, '0362531459', 'mklnio@gmail.com', '', 'TeoNV', '04/12/2019')");
            st.executeUpdate("INSERT INTO NguoiHoc (MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV, NgayDK) VALUES ('NH05', 'Veira', '12/11/1989', 1, '0963253641', 'vhg151@gmail.com', '', 'TeoNV', '07/06/2019')");
            st.executeUpdate("INSERT INTO NguoiHoc (MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV, NgayDK) VALUES ('NH06', 'Rudd Gullit', '07/03/1999', 1, '0936712569', 'fsd5684@gmail.com', '', 'TeoNV', '10/05/2019')");
            //Cập nhật người học theo mã
            st.executeUpdate("UPDATE NguoiHoc SET HoTen='Rui Costa', NgaySinh='06/01/1968', GioiTinh=1, DienThoai='0789632154', Email='hfgh@gmail.com', GhiChu='', MaNV='TeoNV', NgayDK='01/06/2019' WHERE MaNH='NH02'");
            //Xóa 1 người học theo mã
            st.executeUpdate("DELETE FROM NguoiHoc WHERE MaNH='NH02'");

            //Truy vấn tất cả
            Statement statement = con.createStatement();
            String query = "SELECT * FROM NguoiHoc";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String maNH = resultSet.getString("MaNh");
                String hoTen = resultSet.getString("HoTen");
                String ngaySinh = resultSet.getString("NgaySinh");
                boolean isMan = resultSet.getBoolean("GioiTinh");
                String gioiTinh = null;
                if (isMan) {
                    gioiTinh = "Nam";
                } else {
                    gioiTinh = "Nữ";
                }
                String dienThoai = resultSet.getString("DienThoai");
                String email = resultSet.getString("Email");
                String ghiChu = resultSet.getString("GhiChu");
                String ngayDK = resultSet.getString("MaNV");
                System.out.println("\tMã người học: " + maNH
                        + ", ngày sinh: " + ngaySinh
                        + ", họ tên: " + hoTen
                        + ", giới tính: " + gioiTinh
                        + ", điện thoại: " + dienThoai
                        + ", email: " + email
                        + ", ghi chú: " + ghiChu
                        + ", ngày đăng ký: " + ngayDK
                );

                //Truy vấn tất cả
                Statement statement1 = con.createStatement();
                String query1 = "SELECT * FROM NguoiHoc WHERE MaNH='NH02'";
                ResultSet resultSet1 = statement1.executeQuery(query1);
                while (resultSet1.next()) {
                    String maNH1 = resultSet1.getString("MaNh");
                    String hoTen1 = resultSet1.getString("HoTen");
                    String ngaySinh1 = resultSet1.getString("NgaySinh");
                    boolean isMan1 = resultSet1.getBoolean("GioiTinh");
                    String gioiTinh1 = null;
                    if (isMan1) {
                        gioiTinh1 = "Nam";
                    } else {
                        gioiTinh1 = "Nữ";
                    }
                    String dienThoai1 = resultSet1.getString("DienThoai");
                    String email1 = resultSet1.getString("Email");
                    String ghiChu1 = resultSet1.getString("GhiChu");
                    String ngayDK1 = resultSet1.getString("MaNV");
                    System.out.println("\tMã người học: " + maNH1
                            + ", ngày sinh: " + ngaySinh1
                            + ", họ tên: " + hoTen1
                            + ", giới tính: " + gioiTinh1
                            + ", điện thoại: " + dienThoai1
                            + ", email: " + email1
                            + ", ghi chú: " + ghiChu1
                            + ", ngày đăng ký: " + ngayDK1
                    );
                }
                //Gọi thủ tục lưu sp_ThongKeNguoiHoc() và xuất thông tin thống kê ra màn hình
                String Query = "EXECUTE   sp_ThongKeNguoiHoc()";
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
