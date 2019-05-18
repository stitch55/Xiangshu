package com.project.xiangshu.repository;

import com.project.xiangshu.entities.Focus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FocusRepository extends JpaRepository<Focus,Integer> {
    //根据当前用户的id 查询当前用户关注的所有用户
    @Query(value = "select id,focused_id,focusing_id,relationship from tbl_focus where focusing_id=?",nativeQuery = true)
    List<Focus> getFocusUserById(@Param("focusing_id")Integer id);

    //根据当前用户的id 查询所有关注了当前用户的用户基本信息
    @Query(value = "select id,focused_id,focusing_id,relationship from tbl_focus where focused_id=?",nativeQuery = true)
    List<Focus> getFocusedUserById(@Param("focused_id")Integer id);
    //查找当前用户之间是否存在关注关系
    @Query(value = "select id,focused_id,focusing_id,relationship from tbl_focus where focusing_id=?1 and focused_id =?2",nativeQuery = true)
    Focus getRelationship(@Param("focused_id")Integer focusingId,@Param("focusing_id")Integer focusedId);
    //添加用户关系

    //修改当前两个用户之间的关系


}
