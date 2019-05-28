package com.jpm.model;

import java.util.ArrayList;

// this is class is also abstracted behind IMessage
public class MultiSaleMessage extends BaseMessage {
    public MultiSaleMessage() {
        this.sales = new ArrayList<Sale>();
    }
}
