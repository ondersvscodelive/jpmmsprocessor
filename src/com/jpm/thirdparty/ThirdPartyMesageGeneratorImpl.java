package com.jpm.thirdparty;

import java.math.BigDecimal;

import com.jpm.model.IMessage;
import com.jpm.model.Sale;
import com.jpm.model.SingleSaleMessage;

public class ThirdPartyMesageGeneratorImpl implements ThirdPartyMessageGenerator {

    @Override
    public IMessage getMessage() {
        
        System.out.println("Getting message from third party");
        SingleSaleMessage message = new SingleSaleMessage();
        
        message.addSale(new Sale("PRODUCT", new BigDecimal(10), 1));
        
        return message;
    }

}
