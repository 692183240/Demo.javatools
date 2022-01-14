/*package com.example.demo.controller;

public class Testdatadao {
}*/
package com.example.demo.config;
/*import com.kris.entry.User;*/
import org.apache.catalina.User;
import org.apache.ibatis.annotations.*;

/**
 * Created by Kris on 2019/3/20.
 */
@Mapper
public interface Testdatadao {

    /**
     * 新增用户
     * @param user
     */
    @Insert("insert into t_user(id,name,age) values (#{id},#{name},#{age})")
    boolean add(User user);

    /**
     * 删除用户
     * @param id
     */
    @Delete("delete from t_user where id = #{id} ")
    boolean delete(Integer id);

    /**
     * 根据用户 ID 修改用户
     * @param user
     */
    @Update("update t_user set name = #{name},age = #{age} where id = #{id} ")
    boolean update(User user);

    /**
     * 根据 ID 查找用户
     * @param id
     * @return
     */
    @Select("select * from t_user where id = #{id}")
    User select(Integer id);
}
