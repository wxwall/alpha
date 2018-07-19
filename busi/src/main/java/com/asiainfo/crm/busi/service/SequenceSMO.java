package com.asiainfo.crm.busi.service;

import org.apache.ibatis.annotations.Param;

import java.security.PrivateKey;
import java.util.List;

public interface SequenceSMO {

    public List<Long> sequenceByNameAndNum(String seqName, Integer seqNum);
}
