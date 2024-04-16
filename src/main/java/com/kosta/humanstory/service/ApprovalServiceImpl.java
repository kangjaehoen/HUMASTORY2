package com.kosta.humanstory.service;

import com.kosta.humanstory.domain.ApproveDTO;
import com.kosta.humanstory.domain.ApproveVO;
import com.kosta.humanstory.domain.Criteria;
import com.kosta.humanstory.mapper.ApprovalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprovalServiceImpl implements ApprovalService{
    @Autowired
    private ApprovalMapper mapper;

    @Override
    public List<ApproveVO> getLeaveCharts(String empNum , Criteria cri) {
        return mapper.getListWithPaging(empNum,cri);
    }
    @Override
    public int getTotalCount(Criteria cri) {
        return mapper.getTotalCount(cri);
    }
}
