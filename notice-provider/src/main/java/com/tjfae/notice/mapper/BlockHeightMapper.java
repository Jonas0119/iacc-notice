// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.mapper;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Select;
import com.tjfae.notice.entity.BlockHeight;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlockHeightMapper
{
    @Select({ "select * from block_height where channel_name=#{channelName}" })
    BlockHeight getByChannelName(final String p0);
    
    @Update({ "update block_height set  blocknum = #{blocknum}, evidence_amount=#{evidence_amount} where channel_name = #{channel_name}" })
    int updateByChannelName(final BlockHeight p0);
    
    @Insert({ "insert into block_height (blocknum, evidence_amount, channel_name) values (#{blocknum}, #{evidence_amount}, #{channel_name})" })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(final BlockHeight p0);
}
