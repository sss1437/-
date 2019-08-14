
import com.chatRoom.utils.JDBCUtils1;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.sql.*;

/**
 * @Description:
 * @Author: zdd
 * @Date: 2019/8/8
 */
public class JDBCDemo1 {
    @Test
    public void test(){
        //1.加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取数据库连接
            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/jdbc","root","zd971024");
            //3.执行sql语句
            String str = "select*from user";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(str);
            //每一行记录是一个resultSet
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("userName");
                String passWord = resultSet.getString("passWord");
                System.out.println("id： "+id+",用户名："+userName+",密码为："+passWord);
            }
            //4.释放资源
            connection.close();
            statement.close();
            resultSet.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        //1.加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取数据库连接
            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/jdbc", "root", "zd971024");
            //3.执行sql语句
            String sql = "insert into user (userName,passWord)"+"values ('test','456')";
            Statement statement = connection.createStatement();
            int resultRows = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            System.out.println(resultRows);
            connection.close();
            statement.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        //1.加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取数据库连接
            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/jdbc", "root", "zd971024");
            //3.执行sql语句
            String sql = "delete from user where id=1";
            Statement statement = connection.createStatement();
            int resultRows = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            System.out.println(resultRows);
            connection.close();
            statement.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    //SQL注入
    public void test3(){
        //1.加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取数据库连接
            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/jdbc","root","zd971024");
            //3.执行sql语句
            String userName = "zs' or 1=1";
            String passWord = "123";
            String str = "select*from user where username='"+userName+" "+
                    "and password='"+passWord+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(str);
            //每一行记录是一个resultSet
            if (resultSet.next()){
                System.out.println("登录成功");
            }else {
                System.out.println("登录失败");
            }
            //4.释放资源
            connection.close();
            statement.close();
            resultSet.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4(){
        //1.加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取数据库连接
            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/jdbc","root","zd971024");
            //3.执行sql语句
            String sql = "select * from user "+
                    " where  username = ? and password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            String userName = "ls";
            String passWord = "123";
            statement.setString(1,userName);
            statement.setString(2,passWord);
            ResultSet resultSet = statement.executeQuery();
            //每一行记录是一个resultSet
            if (resultSet.next()){
                System.out.println("登录成功");
            }else {
                System.out.println("登录失败");
            }
            //4.释放资源
            connection.close();
            statement.close();
            resultSet.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils1.getConnection();
            String str = "select * from user ";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(str);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                System.out.println("id:"+id+"用户名："+userName+"密码："+password);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils1.closeResources(connection,statement,resultSet);
        }
    }


    @Test
    public void testQuery(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils1.getConnection();
            String sql = "select * from user where id=? and username = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1,2);
            statement.setString(2,"ls");
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                System.out.println("id:"+id+"用户名："+userName+"密码："+password);
            }

        }catch (SQLException e){

        }finally {
            JDBCUtils1.closeResources(connection,statement,resultSet);
        }
    }


    @Test
    public void testInsert(){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtils1.getConnection();
            String sql = "insert into user (username,password)values (?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,"zz");
            statement.setString(2,DigestUtils.md5Hex("123"));
            int infRows = statement.executeUpdate();
            Assert.assertEquals(1,infRows);

        }catch (SQLException e){

        }finally {
            JDBCUtils1.closeResources(connection,statement);
        }
    }


}
