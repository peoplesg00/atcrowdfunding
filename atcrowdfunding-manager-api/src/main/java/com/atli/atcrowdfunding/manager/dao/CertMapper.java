package com.atli.atcrowdfunding.manager.dao;

import com.atli.atcrowdfunding.bean.Cert;
import com.atli.atcrowdfunding.bean.MemberCert;

import java.util.List;

public interface CertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cert record);

    Cert selectByPrimaryKey(Integer id);

    List<Cert> selectAll();

    int updateByPrimaryKey(Cert record);

    List<Cert> queryCertByAccttype(String accttype);

    void insertMemberCert(MemberCert memberCert);
}