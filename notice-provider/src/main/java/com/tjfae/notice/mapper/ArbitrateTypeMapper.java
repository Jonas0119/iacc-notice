// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.mapper;

import com.tjfae.notice.response.ArbitrateEvidenceTypeSearchResp;
import com.tjfae.notice.request.SearchArbitrateEvidenceTypeReq;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Delete;
import java.util.List;
import org.apache.ibatis.annotations.Select;
import com.tjfae.notice.entity.ArbitrateType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArbitrateTypeMapper
{
    @Select({ "select * from arbitrate_type where name = #{name}" })
    ArbitrateType findByName(final String p0);
    
    @Select({ "select * from arbitrate_type where name like '%${name}%'" })
    List<ArbitrateType> search(final String p0);
    
    @Delete({ "delete from arbitrate_type where id=#{id}" })
    int deleteById(final Long p0);
    
    @Select({ "select * from arbitrate_type where id = #{id}" })
    ArbitrateType findById(final Long p0);
    
    @Insert({ "insert into arbitrate_type (name, creator, create_time, last_modifier, last_modify_time)values(#{name}, #{creator}, #{create_time}, #{last_modifier}, #{last_modify_time})" })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(final ArbitrateType p0);
    
    @Update({ "update arbitrate_type set name=#{name}, arbitrate_type_name=#{arbitrate_type_name}, last_modifier=#{last_modifier}, last_modify_time=#{last_modify_time}" })
    int update(final ArbitrateType p0);
    
    @Insert({ "<script>insert into arbitrate_type (id, name, creator, create_time, last_modifier, last_modify_time) values <foreach collection='list' item='c' separator=','>(#{c.id},#{c.name},#{c.creator},#{c.last_modifier},#{c.last_modify_time},)</foreach></script>" })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertBatch(final List<ArbitrateType> p0);
    
    @Update({ "<script><foreach collection='list' item='c' separator=';'>update arbitrate_type<set>name=#{c.name}, last_modifier=#{c.last_modifier}, last_modify_time=#{c.last_modify_time} </set> where id=#{c.id}</foreach>;</script>" })
    int updateBatch(final List<ArbitrateType> p0);
    
    @Select({ "select * from arbitrate_type;" })
    List<ArbitrateType> getAll();
    
    @Select({ "<script>select a.id, a.name as arbitrate_type_name, (select group_concat(name) as evidence_type_names from evidence_type <if test=\"evidence_type_name != null and evidence_type_name != ''\">where name=#{evidence_type_name} </if>group by arbitrate_type_name) as evidence_type_names from arbitrate_type a <if test=\"arbitrate_type_name != null and arbitrate_type_name != ''\"> where name=#{arbitrate_type_name}</if></script>" })
    List<ArbitrateEvidenceTypeSearchResp> searchArbitrateEvidenceList(final SearchArbitrateEvidenceTypeReq p0);
}
