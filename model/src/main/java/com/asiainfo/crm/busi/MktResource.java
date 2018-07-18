package com.asiainfo.crm.busi;

import java.util.Date;

public class MktResource {
    private Integer mkt_Res_Id;

    private String mkt_Res_Nbr;

	private Integer mkt_Res_Type_Id;

    private Integer mkt_ResExt_type_Id;

    private Long mkt_Mkt_Res_Id;

    private String mkt_Res_Name;

    private String mkt_Res_Desc;

    private Integer mkt_Pricing_Plan_Id;

    private String unit;

    private String ordered_Flag;

    private Long manage_RegionId;

    private Date eff_Date;

    private Date exp_Date;

    private Date status_Date;

    private String status_Cd;



    private String cost_Price;

    public Integer getMkt_Res_Id() {
        return mkt_Res_Id;
    }

    public void setMkt_Res_Id(Integer mkt_Res_Id) {
        this.mkt_Res_Id = mkt_Res_Id;
    }

    public String getMkt_Res_Nbr() {
        return mkt_Res_Nbr;
    }

    public void setMkt_Res_Nbr(String mkt_Res_Nbr) {
        this.mkt_Res_Nbr = mkt_Res_Nbr;
    }

    public Integer getMkt_Res_Type_Id() {
        return mkt_Res_Type_Id;
    }

    public void setMkt_Res_Type_Id(Integer mkt_Res_Type_Id) {
        this.mkt_Res_Type_Id = mkt_Res_Type_Id;
    }

    public Integer getMkt_ResExt_type_Id() {
        return mkt_ResExt_type_Id;
    }

    public void setMkt_ResExt_type_Id(Integer mkt_ResExt_type_Id) {
        this.mkt_ResExt_type_Id = mkt_ResExt_type_Id;
    }

    public Long getMkt_Mkt_Res_Id() {
        return mkt_Mkt_Res_Id;
    }

    public void setMkt_Mkt_Res_Id(Long mkt_Mkt_Res_Id) {
        this.mkt_Mkt_Res_Id = mkt_Mkt_Res_Id;
    }

    public String getMkt_Res_Name() {
        return mkt_Res_Name;
    }

    public void setMkt_Res_Name(String mkt_Res_Name) {
        this.mkt_Res_Name = mkt_Res_Name;
    }

    public String getMkt_Res_Desc() {
        return mkt_Res_Desc;
    }

    public void setMkt_Res_Desc(String mkt_Res_Desc) {
        this.mkt_Res_Desc = mkt_Res_Desc;
    }

    public Integer getMkt_Pricing_Plan_Id() {
        return mkt_Pricing_Plan_Id;
    }

    public void setMkt_Pricing_Plan_Id(Integer mkt_Pricing_Plan_Id) {
        this.mkt_Pricing_Plan_Id = mkt_Pricing_Plan_Id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOrdered_Flag() {
        return ordered_Flag;
    }

    public void setOrdered_Flag(String ordered_Flag) {
        this.ordered_Flag = ordered_Flag;
    }

    public Long getManage_RegionId() {
        return manage_RegionId;
    }

    public void setManage_RegionId(Long manage_RegionId) {
        this.manage_RegionId = manage_RegionId;
    }

    public Date getEff_Date() {
        return eff_Date;
    }

    public void setEff_Date(Date eff_Date) {
        this.eff_Date = eff_Date;
    }

    public Date getExp_Date() {
        return exp_Date;
    }

    public void setExp_Date(Date exp_Date) {
        this.exp_Date = exp_Date;
    }

    public Date getStatus_Date() {
        return status_Date;
    }

    public void setStatus_Date(Date status_Date) {
        this.status_Date = status_Date;
    }

    public String getStatus_Cd() {
        return status_Cd;
    }

    public void setStatus_Cd(String status_Cd) {
        this.status_Cd = status_Cd;
    }

    public String getCost_Price() {
        return cost_Price;
    }

    public void setCost_Price(String cost_Price) {
        this.cost_Price = cost_Price;
    }


    @Override
    public String toString() {
        return "MktResource{" +
                "mkt_Res_Id=" + mkt_Res_Id +
                ", mkt_Res_Nbr='" + mkt_Res_Nbr + '\'' +
                ", mkt_Res_Type_Id=" + mkt_Res_Type_Id +
                ", mkt_ResExt_type_Id=" + mkt_ResExt_type_Id +
                ", mkt_Mkt_Res_Id=" + mkt_Mkt_Res_Id +
                ", mkt_Res_Name='" + mkt_Res_Name + '\'' +
                ", mkt_Res_Desc='" + mkt_Res_Desc + '\'' +
                ", mkt_Pricing_Plan_Id=" + mkt_Pricing_Plan_Id +
                ", unit='" + unit + '\'' +
                ", ordered_Flag='" + ordered_Flag + '\'' +
                ", manage_RegionId=" + manage_RegionId +
                ", eff_Date=" + eff_Date +
                ", exp_Date=" + exp_Date +
                ", status_Date=" + status_Date +
                ", status_Cd='" + status_Cd + '\'' +
                ", cost_Price='" + cost_Price + '\'' +
                '}';
    }
}