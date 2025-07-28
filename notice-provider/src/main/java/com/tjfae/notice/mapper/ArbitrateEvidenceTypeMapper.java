// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.mapper;

import com.tjfae.notice.entity.EvidenceType;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Insert;
import com.tjfae.notice.entity.ArbitrateEvidenceType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import com.tjfae.notice.response.ArbitrateEvidenceTypeSearchResp;
import java.util.List;
import com.tjfae.notice.request.SearchArbitrateEvidenceTypeReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArbitrateEvidenceTypeMapper
{
    @Select({ "<script>select arbitrate_type_name, group_concat(evidence_type_name) as evidence_type_names from arbitrate_evidence_type where id > 0 <if test=\"evidence_type_name != null and evidence_type_name != ''\"> and evidence_type_name like '%${evidence_type_name}%'</if><if test=\"arbitrate_type_name != null and arbitrate_type_name != ''\"> and arbitrate_type_name like '%${arbitrate_type_name}%'</if>group by arbitrate_type_name</script> " })
    List<ArbitrateEvidenceTypeSearchResp> searchArbitrateEvidenceType(final SearchArbitrateEvidenceTypeReq p0);
    
    @Delete({ "delete from arbitrate_evidence_type where id=#{id}" })
    int deleteById(final Long p0);
    
    @Select({ "select * from arbitrate_evidence_type where id = #{id}" })
    ArbitrateEvidenceType findById(final Long p0);
    
    @Select({ "select * from arbitrate_evidence_type where name = #{name}" })
    ArbitrateEvidenceType findByName(final String p0);
    
    @Insert({ "insert into arbitrate_evidence_type (arbitrate_type_name, evidence_type_name, creator, create_time, last_modifier, last_modify_time, required)values(#{arbitrate_type_name}, #{evidence_type_name}, #{creator}, #{create_time}, #{last_modifier}, #{last_modify_time}, #{required})" })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(final ArbitrateEvidenceType p0);
    
    @Update({ "update arbitrate_evidence_type set arbitrate_type_name=#{arbitrate_type_name}, evidence_type_name=#{evidence_type_name}, last_modifier=#{last_modifier}, last_modify_time=#{last_modify_time},required=#{required}" })
    int update(final ArbitrateEvidenceType p0);
    
    @Insert({ "<script>insert into arbitrate_evidence_type (id, arbitrate_type_name, evidence_type_name, creator, create_time, last_modifier, last_modify_time, required) values <foreach collection='list' item='c' separator=','>(#{c.id},#{c.arbitrate_type_name},#{c.evidence_type_name},#{c.creator},#{c.create_time},#{c.last_modifier},#{c.last_modify_time},#{c.required})</foreach></script>" })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertBatch(final List<ArbitrateEvidenceType> p0);
    
    @Update({ "<script><foreach collection='list' item='c' separator=';'>update arbitrate_evidence_type<set>arbitrate_type_name=#{c.arbitrate_type_name}, evidence_type_name=#{c.evidence_type_name},last_modifier=#{c.last_modifier}, last_modify_time=#{c.last_modify_time},required=#{c.required} </set> where id=#{c.id}</foreach>;</script>" })
    int updateBatch(final List<ArbitrateEvidenceType> p0);
    
    @Select({ "select * from evidence_type where name in (select evidence_type_name from arbitrate_evidence_type where arbitrate_type_name = #{arbitrateTypeName})" })
    List<EvidenceType> findEvidenceTypeByArbitrateTypeName(final String p0);
    
    @Select({ "select * from arbitrate_evidence_type where arbitrate_type_name=#{arbitrateTypeName}" })
    List<ArbitrateEvidenceType> findByArbitrateTypeName(final String p0);
    
    @Delete({ "<script>delete from arbitrate_evidence_type where id in (<foreach collection='list' item='c' separator=','>#{c.id}</foreach>)</script>" })
    int deleteBatch(final List<ArbitrateEvidenceType> p0);
    
    @Delete({ "delete from arbitrate_evidence_type where arbitrate_type_name = #{arbitrateTypeName}" })
    int deleteArbitrateTypeName(final String p0);
}
