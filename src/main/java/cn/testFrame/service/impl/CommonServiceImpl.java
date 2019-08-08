package cn.testFrame.service.impl;

import cn.testFrame.service.CommonService;
import com.alibaba.dubbo.config.annotation.Service;

@Service
public class CommonServiceImpl implements CommonService {

    @Override
    public String testConnect() {
        return null;
    }
}
