package com.asiainfo.crm.common.utils;

import com.ai.datasources.DynamicDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import com.asiainfo.crm.common.exception.SeqException;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一序列，提供18位序列和16位序列
 * 依赖于数据源
 * Created by wuxiaowei on 2018/7/23
 */
@Component
public class SeqUtil implements EnvironmentAware {


    private Environment environment;

    private Map<String,String> cacheMap = new HashMap<String,String>();

    private int inc18 = 0;

    private int inc16 = 0;

    DynamicDataSource dynamicDataSource;

    DruidDataSource dataSource;

    private String ip_port = null;

    @Resource
    public void setDataSource(DataSource dataSource) {
        this.dynamicDataSource = (DynamicDataSource) dataSource;
        this.dataSource = (DruidDataSource) this.dynamicDataSource.getTargetDataSources().get("ds1");
    }
    /**
     * 提供统一的序列ID生成方法
     * 18位只限于Long型，19位超出Long型，Long最大值：9223372036854775807
     *
     * 规则：4位机器码 + 5位日期 + 5位时间 + 4位自增
     * @return
     */
    public String querySeq18(){
        //4位机器码
        String machinecode = registServerIPAuto(MachineCodeParam.Four);

        Date d = new Date();
        //五位日期
        String dateNowStr = getDateYHHDD(d);

        long time = d.getTime();
        String s = String.valueOf(time);
        //五位时间
        String substringTime = s.substring(s.length() - 8, s.length() - 3);

        StringBuilder sb = new StringBuilder(machinecode);
        sb.append(dateNowStr);
        sb.append(substringTime);

        if(inc18 >= 10000){
            inc18 = -1;
        }
        DecimalFormat countFormat = new DecimalFormat("0000");
        String formatInc = countFormat.format(inc18++);
        //4位自增
        sb.append(formatInc);

        return  sb.toString();
    }

    /**
     * 返回16位编码
     * 规则：5位机器 + 5位日期 + 6位序列
     * @return
     */
    public String querySeq16(){
        //五位机器码
        String machinecode = registServerIPAuto(MachineCodeParam.five);
        StringBuilder sb = new StringBuilder(machinecode);
        Date d = new Date();
        //五位日期
        String dateNowStr = getDateYHHDD(d);
        sb.append(dateNowStr);

        if(inc16 >= 1000000){
            inc16 = -1;
        }
        //6位自增
        DecimalFormat countFormat = new DecimalFormat("000000");
        String formatInc = countFormat.format(inc16++);
        sb.append(formatInc);
        return sb.toString();
    }

    /**
     * 5位日期
     * @return
     */
    private String getDateYHHDD(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateNowStr = sdf.format(date);
        dateNowStr = dateNowStr.substring(dateNowStr.length() - 5,dateNowStr.length());
        return dateNowStr;
    }

    /**
     * 自动注册机器编码
     * @param codeParam 注册几位编码
     * @return
     */
    private String registServerIPAuto(MachineCodeParam codeParam){
        String seq = null;
        try {
            if(ip_port == null){
                InetAddress addr = InetAddress.getLocalHost();
                String ip = addr.getHostAddress().toString(); //获取本机ip
                //测试用例server.port是-1
                String port = environment.getProperty("server.port");
                if(port == null){
                    port = new String("8080");
                }
                ip_port = ip + ":" + port;
            }
            seq = cacheMap.get(ip_port);
            if(seq != null){
                return seq;
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            int id = querySeqBySql(ip_port, statement);
            connection.commit();
            statement.close();
            DecimalFormat countFormat = null;
            if(codeParam.equals(MachineCodeParam.Four)){
                //4位
                if(id >=10000){
                    throw new SeqException("超过机器码超过最大值");
                }
                countFormat = new DecimalFormat("0000");
            }else if(codeParam.equals(MachineCodeParam.five)){
                //5位
                if(id >=100000){
                    throw new SeqException("超过机器码超过最大值");
                }
                countFormat = new DecimalFormat("00000");
            }
            seq = countFormat.format(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        cacheMap.put(ip_port,seq);
        return seq;
    }

    enum  MachineCodeParam{
        Four,five;
    }

    private int querySeqBySql(String ip_port, Statement statement) throws SQLException {
        String  querySQL = new String("SELECT * from machine_code where ip_port = '"+ip_port+"'");
        ResultSet rs = statement.executeQuery(querySQL);
        if (rs != null) {
            // 使用元数据获得关于结果集列的信息
            while (rs.next()) {
                //处理结果
                int id = rs.getInt(1);
                String ip_port_rs = rs.getString(2);
                return id;
            }
            //没查到结果，证明还未在数据库在记录，插入一条新记录
            int i = statement.executeUpdate("insert into machine_code (ip_port) values ('"+ip_port+"')");
            if(i == 1){
                return querySeqBySql(ip_port,statement);
            }
        }
        return -1;
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
