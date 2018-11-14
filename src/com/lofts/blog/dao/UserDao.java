package com.lofts.blog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lofts.blog.bean.User;
import com.lofts.blog.utils.JdbcUtils;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * 用户表操作类
 *
 * @author Administrator
 */
public class UserDao {

    /**
     * 用户登录
     *
     * @param name
     * @param password
     * @return
     */
    public User login(String name, String password) {
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from user where username=? and password=?";
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setHeadimage(resultSet.getString("headimage"));
                user.setUsername(resultSet.getString("username"));
                user.setNickname(resultSet.getString("nickname"));
                user.setSex(resultSet.getString("sex"));
                user.setBirthday(resultSet.getString("birthday"));
                user.setConstellation(resultSet.getString("constellation"));
                user.setHobby(resultSet.getString("hobby"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
                System.out.print("登录成功");
            } else {
                System.out.print("用户名或密码错误，请重试！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(statement, connection);
        }

        return user;
    }

    /**
     * 用户注册
     *
     * @param user
     */
    public void register(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "insert into user(username,password,registerdate) values (?,?,?);";
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRegisterdate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(statement, connection);
        }
    }

    /**
     * 用户名查询
     *
     * @param userName
     * @return
     */
    public String checkUserName(String userName) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from user where username=?";
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, userName);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.print("该用户名已注册");
                return userName;
            } else {
                System.out.print("该用户可用");
                return "";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(statement, connection);
        }
        System.out.print("用户注册异常");
        return "";
    }

    public static User saveUserInfo(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "update user set headimage=?,nickname=?,sex=?,birthday=?,email=?,address=?,constellation=?,hobby=? where id=?";
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, user.getHeadimage());
            statement.setString(2, user.getNickname());
            statement.setString(3, user.getSex());
            statement.setString(4, user.getBirthday());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getAddress());
            statement.setString(7, user.getConstellation());
            statement.setString(8, user.getHobby());
            statement.setInt(9, user.getId());
            statement.executeUpdate();

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(statement, connection);
        }

        return null;
    }

    /**
     * 查询用户详细信息
     * @param id
     * @return
     */
    public User queryUserInfo(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = new User();
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from user where id=?";
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setHeadimage(resultSet.getString("headimage"));
                user.setUsername(resultSet.getString("username"));
                user.setNickname(resultSet.getString("nickname"));
                user.setSex(resultSet.getString("sex"));
                user.setBirthday(resultSet.getString("birthday"));
                user.setConstellation(resultSet.getString("constellation"));
                user.setHobby(resultSet.getString("hobby"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(statement, connection);
        }
        return user;
    }


}
