import com.chatRoom.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description:
 * @Author: zdd
 * @Date: 2019/8/8
 */
public class JDBCDemo2 {
    @Test
    public void testQuery(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from user where id=? and username = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1,2);
            statement.setString(2,"ls");
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                System.out.println("id:"+id+" ,用户名："+userName+" ,密码："+password);
            }

        }catch (SQLException e){

        }finally {
            JDBCUtils.closeResources(connection,statement,resultSet);
        }
    }
}
