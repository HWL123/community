package com.project.community.community.mapper;


import com.project.community.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into user(id,name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{id},#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified},#{avatar_url})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findbyToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findbyId(@Param("id") Integer creator);

    @Select("select * from user where account_id = #{id}")

    User findbyAccountId(@Param("id") String id);

    @Update("update user set name = #{name},avatar_url=#{avatar_url}, gmt_modified=#{gmt_modified},token=#{token} where account_id = #{account_id}" )
    void updateToken(User user);
}
