// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import com.tjfae.notice.entity.ArbitrateRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArbitrateRecordMapper
{
    @Delete({ "delete from arbitrate_record where id=#{id}" })
    int deleteById(final Long p0);
    
    @Select({ "select * from arbitrate_record where id = #{id}" })
    ArbitrateRecord findById(final Long p0);
    
    @Insert({ "insert into arbitrate_record (evidence_digital_code, acceptance_institution, arbitrate_type_name, case_id, title, creator, user_platform, create_time, status, business_side)values(#{evidence_digital_code}, #{acceptance_institution}, #{arbitrate_type_name}, #{case_id}, #{title}, #{creator}, #{user_platform}, #{create_time}, #{status}, #{business_side})" })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(final ArbitrateRecord p0);
    
    @Update({ "update arbitrate_record set evidence_digital_code=#{evidence_digital_code}, acceptance_institution=#{acceptance_institution}, arbitrate_type_name=#{arbitrate_type_name}, case_id=#{case_id}, title=#{title}, status=#{status}" })
    int update(final ArbitrateRecord p0);
    
    @Insert({ "<script>insert into arbitrate_record (id, evidence_digital_code, acceptance_institution, arbitrate_type_name, case_id, title, creator, user_platform, create_time, status, business_side) values <foreach collection='list' item='c' separator=','>(#{c.id},#{c.evidence_digital_code},#{c.acceptance_institution},#{c.arbitrate_type_name},#{c.case_id},#{c.title},#{c.creator},#{c.user_platform},#{c.create_time},#{c.status},#{c.business_side})</foreach></script>" })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertBatch(final List<ArbitrateRecord> p0);
    
    @Update({ "<script><foreach collection='list' item='c' separator=';'>update arbitrate_record<set>evidence_digital_code=#{c.evidence_digital_code}, acceptance_institution=#{c.acceptance_institution}, arbitrate_type_name=#{c.arbitrate_type_name}, case_id=#{c.case_id}, title=#{c.title}, status=#{c.status} </set> where id=#{c.id}</foreach>;</script>" })
    int updateBatch(final List<ArbitrateRecord> p0);
    
    @Select({ "select * from arbitrate_record;" })
    List<ArbitrateRecord> getArbitrateRecord();
    
    @Select({ "select count(*) from arbitrate_record" })
    Long getCount();
    
    @Select({ "select * from arbitrate_record where evidence_digital_code = #{evidenceId}" })
    ArbitrateRecord findByEvidenceId(final String p0);
    
    @Select({ "select * from arbitrate_record where creator = #{creator} and user_platform = #{user_platform}" })
    List<ArbitrateRecord> findByUserInfo(final ArbitrateRecord p0);
    
    @Select({ "select * from arbitrate_record where acceptance_institution like '%${institutionName}%'" })
    List<ArbitrateRecord> search(final String p0);
}
