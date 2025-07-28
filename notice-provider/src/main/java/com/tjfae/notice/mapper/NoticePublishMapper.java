// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.mapper;

import org.apache.ibatis.annotations.Param;
import com.tjfae.notice.entity.NoticeAuditLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import com.tjfae.notice.entity.NoticePublish;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticePublishMapper
{
    @Select({ "select * from t_notice_publish order by id asc limit 100" })
    List<NoticePublish> getListAll();
    
    @Select({ "<script> select id,  userId,  userName,  code,  title,  type,  source,  platform,  noticeUrl,  remark,  fileUrl,  fileName,  fileHash,  createTime,  creator,  creatorName,  sceneType,  hash,  sourceTag,  managerName,  managerPhone,  authUrl,  payUrl,  status,  reason,  updateTime,  publishTime,  views,  pay_order_id payOrderId,  pay_type payType,  pay_time payTime,  pay_callback_time payCallbackTime,  pay_status payStatus,  pay_amount payAmount from t_notice_publish where 1=1  <if test='userId != null and userId != \"\"'>   and userId = #{userId}  </if>  <if test='userName != null and userName != \"\"'>   and userName like concat('%', #{userName}, '%')  </if>  <if test='title != null and title != \"\"'>   and title like concat('%', #{title}, '%')  </if>  <if test='code != null and code != \"\"'>   and code like concat('%', #{code}, '%')  </if>  <if test='creator != null and creator != \"\"'>   and creator like concat('%', #{creator}, '%')  </if>  <if test='hash != null and hash != \"\"'>   and hash like concat('%', #{hash}, '%')  </if>  <if test='source != null and source != \"\"'>   and source = #{source}  </if>  <if test='creator != null and creator != \"\"'>   and creator = #{creator}  </if>  <if test='sceneType != null and sceneType != \"\"'>   and sceneType = #{sceneType}  </if>  <if test='platform != null and platform != \"\"'>   and platform = #{platform}  </if>  <if test='beginTime != null and beginTime != \"\"'>   and date_format(publishTime,'%y%m%d') &gt;= date_format(#{beginTime},'%y%m%d')  </if>  <if test='endTime != null and endTime != \"\"'>   and date_format(publishTime,'%y%m%d') &lt;= date_format(#{endTime},'%y%m%d')  </if>  <if test='sourceTag != null and sourceTag != \"\"'>   and sourceTag = #{sourceTag}  </if>  <if test='managerName != null and managerName != \"\"'>   and managerName like concat('%', #{managerName}, '%')  </if>  <if test='managerPhone != null and managerPhone != \"\"'>   and managerPhone = #{managerPhone}  </if>  <if test='status != null and status != \"\"'>   and status = #{status}  </if>  <if test='statuses != null and statuses != \"\"'>   and status in (${statuses})  </if>  order by  <if test='publishTime != null'>   publishTime desc,  </if>  createTime desc  </script>" })
    List<NoticePublish> getPageList(final NoticePublish p0);
    
    @Select({ "select count(*) from t_notice_publish" })
    Long getTotalCount();
    
    @Insert({ "<script>insert into t_notice_publish (id, userId, userName, title, type,remark,fileUrl,creator,createTime,sceneType,code,source,noticeUrl,platform) values <foreach collection='list' item='c' separator=','>(#{c.id},#{c.userId},#{c.userName},#{c.title},#{c.type},#{c.remark},#{c.fileUrl},#{c.creator},#{c.createTime},#{c.sceneType},#{c.code},#{c.source},#{c.noticeUrl},#{c.platform})</foreach></script>" })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertBatch(final List<NoticePublish> p0);
    
    @Insert({ "insert into t_notice_publish (userId, userName, title, type,remark,fileUrl,fileName,fileHash,creator,createTime,sceneType,code,source,noticeUrl,platform,hash,sourceTag,creatorName,managerName,managerPhone,authUrl,payUrl,status,reason,updateTime,publishTime,views,pay_order_id,pay_type,pay_amount,pay_status,pay_time,pay_callback_time)values(#{userId}, #{userName}, #{title}, #{type}, #{remark}, #{fileUrl},#{fileName},#{fileHash}, #{creator}, #{createTime}, #{sceneType}, #{code}, #{source},#{noticeUrl}, #{platform}, #{hash}, #{sourceTag}, #{creatorName}, #{managerName}, #{managerPhone}, #{authUrl}, #{payUrl}, #{status}, #{reason}, #{updateTime}, #{publishTime}, #{views}, #{payOrderId},#{payType},#{payAmount},#{payStatus},#{payTime},#{payCallbackTime})" })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(final NoticePublish p0);
    
    @Update({ "<script> update t_notice_publish set  <if test='userName != null and userName != \"\"'>   userName = #{userName},  </if>  <if test='title != null and title != \"\"'>   title = #{title},  </if>  <if test='remark != null and remark != \"\"'>   remark = #{remark},  </if>  <if test='fileUrl != null and fileUrl != \"\"'>   fileUrl = #{fileUrl},  </if>  <if test='sceneType != null and sceneType != \"\"'>   sceneType = #{sceneType},  </if>  <if test='code != null and code != \"\"'>   code = #{code},  </if>  <if test='source != null and source != \"\"'>   source = #{source},  </if>  <if test='platform != null and platform != \"\"'>   platform = #{platform},  </if>  <if test='noticeUrl != null and noticeUrl != \"\"'>   noticeUrl = #{noticeUrl},  </if>  <if test='fileName != null and fileName != \"\"'>   fileName = #{fileName},  </if>  <if test='fileHash != null and fileHash != \"\"'>   fileHash = #{fileHash},  </if>  <if test='hash != null and hash != \"\"'>   hash = #{hash},  </if>  <if test='sourceTag != null and sourceTag != \"\"'>   sourceTag = #{sourceTag},  </if>  <if test='managerName != null and managerName != \"\"'>   managerName = #{managerName},  </if>  <if test='managerPhone != null and managerPhone != \"\"'>   managerPhone = #{managerPhone},  </if>  <if test='authUrl != null and authUrl != \"\"'>   authUrl = #{authUrl},  </if>  <if test='payUrl != null and payUrl != \"\"'>   payUrl = #{payUrl},  </if>  <if test='status != null and status != \"\"'>   status = #{status},  </if>  <if test='reason != null and reason != \"\"'>   reason = #{reason},  </if>  <if test='publishTime != null'>   publishTime = #{publishTime},  </if>  updateTime = now() where id = #{id} </script>" })
    int update(final NoticePublish p0);
    
    @Delete({ "delete from t_notice_publish where id = #{id}" })
    int delete(final int p0);
    
    @Select({ "select id,  userId,  userName,  code,  title,  type,  source,  platform,  noticeUrl,  remark,  fileUrl,  fileName,  fileHash,  createTime,  creator,  creatorName,  sceneType,  hash,  sourceTag,  managerName,  managerPhone,  authUrl,  payUrl,  status,  reason,  updateTime,  publishTime,  views,  pay_order_id payOrderId,  pay_type payType,  pay_time payTime,  pay_callback_time payCallbackTime,  pay_status payStatus,  pay_amount payAmount from t_notice_publish where id = #{id}" })
    NoticePublish findById(final int p0);
    
    @Select({ "select * from t_notice_publish where code = #{code}" })
    NoticePublish findByCode(final String p0);
    
    @Insert({ "insert into t_notice_audit_log (noticeId, userId, userName, role, reason, type,remark,creator,createTime,creatorName)values(#{noticeId}, #{userId}, #{userName}, #{role},#{reason}, #{type}, #{remark},#{creator}, #{createTime}, #{creatorName})" })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertLog(final NoticeAuditLog p0);
    
    @Select({ "select * from t_notice_audit_log where id = #{id}" })
    NoticeAuditLog findLogById(final int p0);
    
    @Select({ "<script> select * from t_notice_audit_log where 1=1  <if test='userName != null and userName != \"\"'>   and userName like concat('%', #{userName}, '%')  </if>  <if test='noticeId != null and noticeId != \"\"'>   and noticeId = #{noticeId}  </if>  <if test='role != null and role != \"\"'>   and role like concat('%', #{role}, '%')  </if>  <if test='creator != null and creator != \"\"'>   and creator like concat('%', #{creator}, '%')  </if>  order by createTime desc  </script>" })
    List<NoticeAuditLog> getLogPageList(final NoticeAuditLog p0);
    
    @Select({ "select count(*) from t_notice_audit_log" })
    Long getLogTotalCount();
    
    @Update({ "<script> update t_notice_publish set views = views + #{views} where id = #{id}</script>" })
    int view(final NoticePublish p0);
    
    @Select({ "select MAX(code) from t_notice_publish where LENGTH(code) = 15 and code like 'GGZDL__${year}%'" })
    String selectMaxCode(@Param("year") final String p0);
    
    @Update({ "<script> update t_notice_publish set pay_status=#{payStatus}, pay_callback_time=#{payCallbackTime} where pay_order_id=#{payOrderId} </script>" })
    int updateByOrderId(final NoticePublish p0);
}
