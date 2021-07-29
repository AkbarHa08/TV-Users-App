package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Util {
    
   public static void closeAll(Connection c,PreparedStatement ps,ResultSet rs){
       try {
           if (c!=null) {
               c.close();
           }
           if (ps!=null) {
               c.close();
           }
           if (rs!=null) {
               c.close();
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}