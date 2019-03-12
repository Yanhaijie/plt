<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${namespace}">
    <resultMap id="BaseResultMap" type="${resultType}">
        <!--
          WARNING - @mbg.generated
        -->
        <id column="id" jdbcType="INTEGER" property="id" />
        <result column="bounty_name" jdbcType="VARCHAR" property="bountyName" />
        <result column="bounty_dsc" jdbcType="VARCHAR" property="bountyDsc" />
        <result column="bounty_money" jdbcType="DECIMAL" property="bountyMoney" />
        <result column="ads_url" jdbcType="VARCHAR" property="adsUrl" />
        <result column="ads_callback_url" jdbcType="VARCHAR" property="adsCallbackUrl" />
        <result column="bounty_status" jdbcType="INTEGER" property="bountyStatus" />
        <result column="create_time" jdbcType="TIMESTAMP" property="createTime" />
        <result column="update_time" jdbcType="TIMESTAMP" property="updateTime" />
        <result column="opt" jdbcType="VARCHAR" property="opt" />
    </resultMap>
</mapper>