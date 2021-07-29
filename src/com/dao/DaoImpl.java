package com.dao;

import com.model.TvUsers;
import com.model.Users;
import com.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoImpl implements Dao {

    @Override
    public Users checkUser(String username, String password) {
        Users u = new Users();
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id,name,surname,username,password from tvusersapp.users\n"
                + "where username='" + username + "' and password='" + password + "'";
        c = DbHelper.GetConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    u.setId(rs.getInt("id"));
                    u.setName(rs.getString("name"));
                    u.setSurname(rs.getString("surname"));
                    u.setUsername(rs.getString("username"));
                    u.setPassword(rs.getString("password"));
                } else {
                    u = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Util.closeAll(c, ps, rs);
            }
        } else {
            System.err.println("Not Connection!");
        }

        return u;
    }

    @Override
    public List<TvUsers> getAllUsers() {
        List<TvUsers> allUsers = new ArrayList<TvUsers>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "Select id,fullname,mobile,login,password,address From tvusersapp.tvusers";
        c = DbHelper.GetConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    TvUsers tvusers = new TvUsers();
                    tvusers.setId(rs.getInt("id"));
                    tvusers.setFullname(rs.getString("fullname"));
                    tvusers.setMobile(rs.getString("mobile"));
                    tvusers.setLogin(rs.getString("login"));
                    tvusers.setPassword(rs.getString("password"));
                    tvusers.setAddress(rs.getString("address"));
                    allUsers.add(tvusers);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Util.closeAll(c, ps, rs);
            }
        } else {
            System.err.println("Not Connection!");
        }
        return allUsers;
    }

    @Override
    public List<TvUsers> searchUsers(String id, String fullname, String mobile, String login, String password, String address) {
        List<TvUsers> users = new ArrayList<TvUsers>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id,fullname,mobile,login,password,address from tvusersapp.tvusers\n"
                + "where id like('%" + id + "%') and fullname like('%" + fullname + "%') and mobile like('%" + mobile + "%') and login like('%" + login + "%')\n"
                + "and password like('%" + password + "%') and address like('%" + address + "%')";
        c = DbHelper.GetConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    TvUsers tvusers = new TvUsers();
                    tvusers.setId(rs.getInt("id"));
                    tvusers.setFullname(rs.getString("fullname"));
                    tvusers.setMobile(rs.getString("mobile"));
                    tvusers.setLogin(rs.getString("login"));
                    tvusers.setPassword(rs.getString("password"));
                    tvusers.setAddress(rs.getString("address"));
                    users.add(tvusers);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Util.closeAll(c, ps, rs);
            }
        } else {
            System.err.println("Not Connection!");
        }
        return users;
    }

    @Override
    public boolean addUser(TvUsers users) {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "insert into tvusersapp.tvusers(fullname,mobile,login,password,address)\n"
                + "values('" + users.getFullname() + "','" + users.getMobile() + "','" + users.getLogin() + "','" + users.getPassword() + "','" + users.getAddress() + "');";
        c = DbHelper.GetConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(sql);
                ps.execute();
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Util.closeAll(c, ps, null);
            }
        } else {
            System.err.println("Not Connection!");
        }
        return result;
    }

    @Override
    public boolean deleteUser(int selectedId) {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "delete from tvusersapp.tvusers\n"
                + "where id = " + selectedId + ";";
        c = DbHelper.GetConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(sql);
                ps.execute();
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Util.closeAll(c, ps, null);
            }
        } else {
            System.err.println("Not Connection!");
        }

        return result;
    }

    @Override
    public boolean editUser(TvUsers users) {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update tvusersapp.tvusers\n" +
"set fullname='"+users.getFullname()+"', mobile='"+users.getMobile()+"', login='"+users.getLogin()+"', password='"+users.getPassword()+"', address='"+users.getAddress()+"'\n" +
"where id="+users.getId()+";";
        c = DbHelper.GetConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(sql);
                ps.execute();
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Util.closeAll(c, ps, null);
            }
        } else {
            System.err.println("Not Connection!");
        }

        return result;
    }

}
