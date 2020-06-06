package com.project.community.community.mapper;


import com.project.community.community.dto.QuestionDTO;
import com.project.community.community.model.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,comment_count,like_count,tag) values(#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{comment_count},#{like_count},#{tag})")
    void create(Question question);
    @Select("select * from question limit #{offsize},#{size}")
    List<Question> list(@Param(value = "offsize") Integer offsize, @Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator = #{id} limit #{offsize},#{size}")
    List<Question> listByUserid(@Param(value = "id") Integer id, @Param(value = "offsize") Integer offsize, @Param(value = "size") Integer size);

    @Select("select count(1) from question where creator = #{id}")

    Integer countByUserid(@Param(value = "id") Integer id);

    @Select("select * from question where id = #{id}")
    Question findById(@Param(value = "id") Integer id);

    @Update("update question set title = #{title},description=#{description},tag=#{tag},gmt_modified = #{gmt_modified} where id = #{id}")
    void update(Question question);
}
