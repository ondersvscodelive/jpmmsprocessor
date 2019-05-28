package com.jpm.model;

import java.util.ArrayList;

//this is class is also abstracted behind IMessage
public class SingleSaleMessage extends BaseMessage {
    public SingleSaleMessage() {
        this.sales = new ArrayList<Sale>(1);
    }
}
