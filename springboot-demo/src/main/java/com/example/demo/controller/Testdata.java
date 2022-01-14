/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Testdata {
    // 定义一个方法，用来得到一个"新的"连接对象
    public static Connection getConnection()
    {
        Connection conn = null;
        String driverName = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:ora9i";
        String userName = "scott";
        String passWord = "tiger";
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url,userName,passWord );
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }


    public static void closeConn(Connection conn)
    {
        try {
            if(conn != null)
            {
                conn.close();
            }
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void closeState(Statement state)
    {
        try {
            if(state != null)
            {
                state.close();
            }
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void closeRs(ResultSet rs)
    {
        try {
            if(rs != null)
            {
                rs.close();
            }
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}






import java.sql.ResultSet;
        import java.sql.Statement;
        import java.util.ArrayList;


        import com.tianyitime.notebook.support.userPO.UserPO;
        import com.tianyitime.notebook.support.util.DBTools;




public class UserDAO {


    // 新增user
    public void saveUserInfo(UserPO upo)
    {
        Connection conn = null;
        Statement state = null;
        try {
            conn = DBTools.getConnection();
            state = conn.createStatement();
            String sql = "insert into notebook_user values ("+getMaxId()+",'"+upo.getYhm()+"','"+upo.getEmail()+"','"+upo.getContent()+"')";
//System.out.println(sql);
            state.executeUpdate(sql);


        } catch (Exception ex) {
// TODO Auto-generated catch block
            ex.printStackTrace();
        }
        finally
        {
            DBTools.closeState(state);
            DBTools.closeConn(conn);
        }
    }


    //得到一个数据库中当前Id的最大值
    private int getMaxId()
    {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        int maxId = 0;
        try {
            conn = DBTools.getConnection();
            state = conn.createStatement();
            String sql = "select max(id) maxId from notebook_user";
            rs = state.executeQuery(sql);
//从resultset对象中将数据取出
            if(rs.next())
            {
                maxId = rs.getInt("maxId");
            }
        } catch (Exception ex) {
// TODO Auto-generated catch block
            ex.printStackTrace();
        }


        return ++maxId;
    }


    // 得到所有的记录
    public ArrayList getUserInfo()
    {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        UserPO upo = null;
        ArrayList al = new ArrayList();
        try {
            conn = DBTools.getConnection();
            state = conn.createStatement();
            String sql = "select * from notebook_user";
            rs = state.executeQuery(sql);
//从resultset对象中将数据取出


            while(rs.next())
            {
                upo = new UserPO();
                int id = rs.getInt("id");
                String yhm = rs.getString("yhm");
                String email = rs.getString("email");
                String content = rs.getString("content");


                upo.setId(id);
                upo.setYhm(yhm);
                upo.setEmail(email);
                upo.setContent(content);


//将改对象放入已经创建好的集合类对象ArrauyList
                al.add(upo);
            }
        } catch (Exception ex) {
// TODO Auto-generated catch block
            ex.printStackTrace();
        }
        finally
        {
            DBTools.closeRs(rs);
            DBTools.closeState(state);
            DBTools.closeConn(conn);
        }
        return al;
    }


    // 删除一条user记录
    public void deleteUserInfo(int id)
    {
        Connection conn = null;
        Statement state = null;
        try {
            conn = DBTools.getConnection();
            state = conn.createStatement();
            String sql = "delete from notebook_user where id="+id;
//System.out.println(sql);
            state.executeUpdate(sql);


        } catch (Exception ex) {
// TODO Auto-generated catch block
            ex.printStackTrace();
        }
        finally
        {
            DBTools.closeState(state);
            DBTools.closeConn(conn);
        }
    }


    // 根据给定的信息得到记录
    public ArrayList getUserInfoByInfo(String name,String email,String content)
    {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        UserPO upo = null;
        ArrayList al = new ArrayList();
        try {
            conn = DBTools.getConnection();
            state = conn.createStatement();
            String sql = "select * from notebook_user where 1=1 ";
            if(!"".equals(name) && name != null)
            {
                sql += " and yhm like '%"+name+"%'";
            }
            if(!"".equals(email) && email != null)
            {
                sql += " and email = '"+email+"'";
            }
            if(!"".equals(content) && content != null)
            {
                sql += " and content like '%"+content+"%'";
            }
            sql+=" order by id desc";
            rs = state.executeQuery(sql);
//从resultset对象中将数据取出


            while(rs.next())
            {
                upo = new UserPO();
                int id = rs.getInt("id");
                String yhm = rs.getString("yhm");
                String femail = rs.getString("email");
                String fcontent = rs.getString("content");


                upo.setId(id);
                upo.setYhm(yhm);
                upo.setEmail(femail);
                upo.setContent(fcontent);


//将改对象放入已经创建好的集合类对象ArrauyList
                al.add(upo);
            }
        } catch (Exception ex) {
// TODO Auto-generated catch block
            ex.printStackTrace();
        }
        finally
        {
            DBTools.closeRs(rs);
            DBTools.closeState(state);
            DBTools.closeConn(conn);
        }
        return al;
    }


}*/
