// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import com.tjfae.notice.entity.ArbitralInstitution;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArbitralInstitutionMapper
{
    @Delete({ "delete from arbitral_institution where id=#{id}" })
    int deleteById(final Long p0);
    
    @Select({ "select * from arbitral_institution where id = #{id}" })
    ArbitralInstitution findById(final Long p0);
    
    @Select({ "select * from arbitral_institution where name = #{name}" })
    ArbitralInstitution findByName(final String p0);
    
    @Insert({ "insert into arbitral_institution (name, creator, create_time, last_modifier, last_modify_time,remark)values(#{name}, #{creator}, #{create_time}, #{last_modifier}, #{last_modify_time}, #{remark})" })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(final ArbitralInstitution p0);
    
    @Update({ "update arbitral_institution set name=#{name}, last_modifier=#{last_modifier}, last_modify_time=#{last_modify_time}, remark=#{remark}" })
    int update(final ArbitralInstitution p0);
    
    @Insert({ "<script>insert into arbitral_institution (id, name, creator, create_time, last_modifier, last_modify_time, remark) values <foreach collection='list' item='c' separator=','>(#{c.id},#{c.name},#{c.creator},#{c.last_modifier},#{c.last_modify_time},#{c.remark})</foreach></script>" })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertBatch(final List<ArbitralInstitution> p0);
    
    @Update({ "<script><foreach collection='list' item='c' separator=';'>update arbitral_institution<set>name=#{c.name}, last_modifier=#{c.last_modifier}, last_modify_time=#{c.last_modify_time}, remark=#{c.remark}, </set> where id=#{c.id}</foreach>;</script>" })
    int updateBatch(final List<ArbitralInstitution> p0);
    
    @Select({ "select * from arbitral_institution where name like '%${name}%'" })
    List<ArbitralInstitution> search(final String p0);
    
    @Select({ "select * from arbitral_institution;" })
    List<ArbitralInstitution> getArbitralInstitution();
}
