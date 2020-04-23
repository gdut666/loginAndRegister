package dao.impl;

import User.User;
import dao.UserDao;
import utils.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public User login(User user) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDao.getCon();

            String sql = "select * from user where name=? and password=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,user.getName());
            pstmt.setString(2,user.getPassword());

            rs = pstmt.executeQuery();

            User users = null;

            if (rs.next()) {
                users = new User();
                users.setId(rs.getInt("id"));
                users.setName(rs.getString("name"));
                users.setPassword(rs.getString("password"));
                users.setEmail(rs.getString("email"));
                users.setPhone(rs.getString("phone"));
                return user;
            } else {
                return null;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(conn,pstmt,rs);
        }
        return null;
    }

    @Override
    public boolean register(User user) {
        String sql = "insert into user values(0,?,?,?,?)";
        List<Object> list = new ArrayList<Object>();
        list.add(user.getName());
        list.add(user.getPassword());
        list.add(user.getEmail());
        list.add(user.getPhone());

        boolean flag = BaseDao.addUpdateDelete(sql,list.toArray());
        if (flag == true) {
            return true;
        } else {
            return false;
        }
    }
}
