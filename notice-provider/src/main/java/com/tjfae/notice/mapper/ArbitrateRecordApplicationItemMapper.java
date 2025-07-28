// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import com.tjfae.notice.entity.ArbitrateRecordApplicationItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArbitrateRecordApplicationItemMapper
{
    @Delete({ "delete from arbitrate_record_application_item where id=#{id}" })
    int deleteById(final Long p0);
    
    @Select({ "select * from arbitrate_record_application_item where id = #{id}" })
    ArbitrateRecordApplicationItem findById(final Long p0);
    
    @Insert({ "insert into arbitrate_record_application_item (record_id, evidence_digital_code, image_name, image_url)values(#{record_id}, #{evidence_digital_code},#{image_name},#{image_url})" })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(final ArbitrateRecordApplicationItem p0);
    
    @Update({ "update arbitrate_record_application_item set record_id=#{record_id}, evidence_digital_code=#{evidence_digital_code}, image_name=#{image_name}, image_url=#{image_url}" })
    int update(final ArbitrateRecordApplicationItem p0);
    
    @Insert({ "<script>insert into arbitrate_record_application_item (id, record_id, evidence_digital_code, image_name, image_url) values <foreach collection='list' item='c' separator=','>(#{c.id},#{c.record_id},#{c.evidence_digital_code},#{c.image_name},#{c.image_url})</foreach></script>" })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertBatch(final List<ArbitrateRecordApplicationItem> p0);
    
    @Update({ "<script><foreach collection='list' item='c' separator=';'>update arbitrate_record_application_item<set>record_id=#{c.record_id}, evidence_digital_code=#{c.evidence_digital_code}, image_name=#{c.image_name}, image_url=#{c.image_url} </set> where id=#{c.id}</foreach>;</script>" })
    int updateBatch(final List<ArbitrateRecordApplicationItem> p0);
    
    @Select({ "select * from arbitrate_record_application_item;" })
    List<ArbitrateRecordApplicationItem> getArbitrateRecordApplicationItem();
    
    @Select({ "select * from arbitrate_record_application_item where evidence_digital_code = #{evidenceId}" })
    List<ArbitrateRecordApplicationItem> findByEvidenceId(final String p0);
    
    @Select({ "select * from arbitrate_record_application_item where record_id = #{recordId}" })
    List<ArbitrateRecordApplicationItem> findByRecordId(final Long p0);
}
