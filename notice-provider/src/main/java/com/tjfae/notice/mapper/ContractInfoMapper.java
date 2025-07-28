// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.mapper;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import com.tjfae.notice.entity.ContractInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContractInfoMapper
{
    @Select({ "select * from t_contract_info order by id asc limit 100" })
    List<ContractInfo> getListAll();
    
    @Select({ "select count(*) from t_contract_info" })
    Long getTotalCount();
    
    @Select({ "<script> select * from t_contract_info where 1=1  <if test='userName != null and userName != \"\"'>   and userName like concat('%', #{userName}, '%')  </if>  <if test='title != null and title != \"\"'>   and title like concat('%', #{title}, '%')  </if>  <if test='hash != null and hash != \"\"'>   and hash like concat('%', #{hash}, '%')  </if>  <if test='creator != null and creator != \"\"'>   and creator like concat('%', #{creator}, '%')  </if>  <if test='sceneType != null and sceneType != \"\"'>   and sceneType = #{sceneType}  </if>  <if test='creator != null and creator != \"\"'>   and creator = #{creator}  </if>  <if test='type != null and type != \"\"'>   and type = #{type}  </if>  <if test='beginTime != null and beginTime != \"\"'>   and date_format(createTime,'%y%m%d') &gt;= date_format(#{beginTime},'%y%m%d')  </if>  <if test='endTime != null and endTime != \"\"'>   and date_format(createTime,'%y%m%d') &lt;= date_format(#{endTime},'%y%m%d')  </if>  order by createTime desc  </script>" })
    List<ContractInfo> getPageList(final ContractInfo p0);
    
    @Insert({ "<script>insert into t_contract_info (id, userId, userName, title, type,remark,fileUrl,creator,createTime,sceneType,code) values <foreach collection='list' item='c' separator=','>(#{c.id},#{c.userId},#{c.userName},#{c.title},#{c.type},#{c.remark},#{c.fileUrl},#{c.creator},#{c.createTime},#{c.sceneType},#{c.code})</foreach></script>" })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertBatch(final List<ContractInfo> p0);
    
    @Insert({ "insert into t_contract_info (userId, userName, title, type,remark,fileUrl,creator,createTime,sceneType,code,hash,sourceTag,creatorName,threeInOneFileUrl,idCardImageUrl,companyDescUrl,companyRuleUrl,resumeUrl,financeReportUrl,grayListUrl,creditReportUrl,aiRtingReportUrl,serviceReportUrl,applyReportUrl,standingBookReportUrl,promiseUrl)values(#{userId}, #{userName}, #{title}, #{type}, #{remark}, #{fileUrl}, #{creator}, #{createTime}, #{sceneType}, #{code}, #{hash}, #{sourceTag},#{creatorName},#{threeInOneFileUrl},#{idCardImageUrl},#{companyDescUrl},#{companyRuleUrl},#{resumeUrl},#{financeReportUrl},#{grayListUrl},#{creditReportUrl},#{aiRtingReportUrl},#{serviceReportUrl},#{applyReportUrl},#{standingBookReportUrl},#{promiseUrl})" })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(final ContractInfo p0);
    
    @Select({ "select * from t_contract_info where id = #{id}" })
    ContractInfo findById(final int p0);
    
    @Select({ "select * from t_contract_info where code = #{code}" })
    ContractInfo findByCode(final String p0);
}
