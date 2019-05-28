package com.jpm.model;

import java.util.ArrayList;

//this is class is also abstracted behind IMessage
public class AdjustmentSaleMessage extends BaseMessage {
    
    private AdjustmentOperation operation;
    
    public AdjustmentSaleMessage() {
        this.sales = new ArrayList<Sale>();
        this.operation = AdjustmentOperation.NONE;
    }

    public AdjustmentOperation getOperation() {
        return operation;
    }

    public void setOperation(AdjustmentOperation operation) {
        this.operation = operation;
    }
    
    @Override
    public boolean equals(Object o) {
        
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        AdjustmentSaleMessage that = (AdjustmentSaleMessage)o;
        
        boolean result = super.equalLists(this.sales, that.sales);
        if (!result)
            return false;
        
        return true;
    }
    
    
    @Override
    public int hashCode() {
        final int prime = 31;        
        int result = operation != null ? operation.hashCode() : 0;

        for (Sale sale : this.sales) {
                result = prime * result + ((sale == null) ? 0 : sale.hashCode());
        }
        return result;
    }
    
    @Override
    public String toString() {
        return "AdjustmentSaleMessage{ adjustmentOperation=" + operation.toString() + ", " + super.toString() + "}";
    }
}
