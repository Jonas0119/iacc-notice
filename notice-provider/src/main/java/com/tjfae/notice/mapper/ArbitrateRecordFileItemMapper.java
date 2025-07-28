// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import com.tjfae.notice.entity.ArbitrateRecordFileItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArbitrateRecordFileItemMapper
{
    @Delete({ "delete from arbitrate_record_file_item where id=#{id}" })
    int deleteById(final Long p0);
    
    @Select({ "select * from arbitrate_record_file_item where id = #{id}" })
    ArbitrateRecordFileItem findById(final Long p0);
    
    @Insert({ "insert into arbitrate_record_file_item (record_id, evidence_digital_code, file_name, hash, file_type, file_sn, description)values(#{record_id}, #{evidence_digital_code},#{file_name},#{hash},#{file_type},#{file_sn}, #{description})" })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(final ArbitrateRecordFileItem p0);
    
    @Update({ "update arbitrate_record_file_item set record_id=#{record_id}, evidence_digital_code=#{evidence_digital_code}, file_name=#{file_name}, hash=#{hash}, file_type=#{file_type}, file_sn=#{file_sn}, description=#{description}" })
    int update(final ArbitrateRecordFileItem p0);
    
    @Insert({ "<script>insert into arbitrate_record_file_item (id, record_id, evidence_digital_code, file_name, hash, file_type, file_sn, description) values <foreach collection='list' item='c' separator=','>(#{c.id},#{c.record_id},#{c.evidence_digital_code},#{c.file_name},#{c.hash},#{c.file_type},#{c.file_sn},#{c.description})</foreach></script>" })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertBatch(final List<ArbitrateRecordFileItem> p0);
    
    @Update({ "<script><foreach collection='list' item='c' separator=';'>update arbitrate_record_file_item<set>record_id=#{c.record_id}, evidence_digital_code=#{c.evidence_digital_code}, file_name=#{c.file_name}, hash=#{c.hash}, file_type=#{c.file_type}, file_sn=#{c.file_sn}, description=#{c.description} </set> where id=#{c.id}</foreach>;</script>" })
    int updateBatch(final List<ArbitrateRecordFileItem> p0);
    
    @Select({ "select * from arbitrate_record_file_item;" })
    List<ArbitrateRecordFileItem> getArbitrateRecordFileItem();
    
    @Select({ "select * from arbitrate_record_file_item where evidence_digital_code = #{evidenceId}" })
    List<ArbitrateRecordFileItem> findByEvidenceId(final String p0);
    
    @Select({ "select * from arbitrate_record_file_item where record_id = #{recordId}" })
    List<ArbitrateRecordFileItem> findByRecordId(final Long p0);
}
