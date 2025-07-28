// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.mapper;

import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import com.tjfae.notice.entity.EvidenceNews;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EvidenceNewsMapper
{
    @Select({ "select * from evidence_news order by id asc limit 10" })
    List<EvidenceNews> getNews();
    
    @Select({ "select count(*) from evidence_news" })
    Long getCount();
    
    @Insert({ "<script>insert into evidence_news (id, tx_hash, remark, type, create_time) values <foreach collection='list' item='c' separator=','>(#{c.id},#{c.tx_hash},#{c.remark},#{c.type},#{c.create_time})</foreach></script>" })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertBatch(final List<EvidenceNews> p0);
    
    @Update({ "<script><foreach collection='list' item='c' separator=';'>update evidence_news<set>tx_hash=#{c.tx_hash}, remark=#{c.remark}, type=#{c.type}, create_time=#{c.create_time} </set> where id=#{c.id}</foreach>;</script>" })
    int updateBatch(final List<EvidenceNews> p0);
}
