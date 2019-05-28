package com.jpm.repository;

import com.jpm.model.IMessage;

public interface SalesRepository {

    String generateSaleReport();

    String generateAdjustmentReport();

    void add(IMessage message);
    
}
