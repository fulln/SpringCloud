package com.fulln.provider.Service.Impl;

import com.fulln.provider.Service.ProviderService;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl implements ProviderService{

    @Override
    public String getAge(Integer Age) {
        return Age+10+"";
    }
}
