// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.mapper;

import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Delete;
import java.util.List;
import org.apache.ibatis.annotations.Select;
import com.tjfae.notice.entity.EvidenceType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EvidenceTypeMapper
{
    @Select({ "select * from evidence_type where name = #{name}" })
    EvidenceType findByName(final String p0);
    
    @Select({ "select * from evidence_type where name like '%${name}%'" })
    List<EvidenceType> search(final String p0);
    
    @Select({ "select * from evidence_type" })
    List<EvidenceType> getAll();
    
    @Delete({ "delete from evidence_type where id=#{id}" })
    int deleteById(final Long p0);
    
    @Select({ "select * from evidence_type where id = #{id}" })
    EvidenceType findById(final Long p0);
    
    @Insert({ "insert into evidence_type (name, arbitrate_type_name, creator, create_time, last_modifier, last_modify_time)values(#{name}, #{arbitrate_type_name}, #{creator}, #{create_time}, #{last_modifier}, #{last_modify_time})" })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(final EvidenceType p0);
    
    @Update({ "update evidence_type set name=#{name}, arbitrate_type_name=#{arbitrate_type_name}, last_modifier=#{last_modifier}, last_modify_time=#{last_modify_time}" })
    int update(final EvidenceType p0);
    
    @Insert({ "<script>insert into evidence_type (id, name,arbitrate_type_name, creator, create_time, last_modifier, last_modify_time) values <foreach collection='list' item='c' separator=','>(#{c.id},#{c.name},#{c.arbitrate_type_name},#{c.creator},#{c.last_modifier},#{c.last_modify_time},)</foreach></script>" })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertBatch(final List<EvidenceType> p0);
    
    @Update({ "<script><foreach collection='list' item='c' separator=';'>update evidence_type<set>name=#{c.name}, arbitrate_type_name=#{arbitrate_type_name}, last_modifier=#{c.last_modifier}, last_modify_time=#{c.last_modify_time} </set> where id=#{c.id}</foreach>;</script>" })
    int updateBatch(final List<EvidenceType> p0);
    
    @Select({ "select * from evidence_type where name in (${name})" })
    List<EvidenceType> getByName(final String p0);
}
