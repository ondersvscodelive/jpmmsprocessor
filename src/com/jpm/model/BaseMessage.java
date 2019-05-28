package com.jpm.model;

import java.util.Collections;
import java.util.List;

abstract class BaseMessage implements IMessage {
    protected List<Sale> sales;

    @Override
    public List<Sale> getSales() {
        return this.sales;
    }
    
    public void addSale(Sale sale) {
        sales.add(sale);
    }

    protected boolean equalLists(List one, List two) {
        if (one == null && two == null) {
            return true;
        }

        if ((one == null && two != null) || one != null && two == null || one.size() != two.size()) {
            return false;
        }

        Collections.sort(one);
        Collections.sort(two);
        return one.equals(two);
    }
    
    @Override
    public int hashCode() {
            final int prime = 31;
            int result = 1;

            for (Sale sale : sales) {
                    result = prime * result + ((sale == null) ? 0 : sale.hashCode());
            }
            return result;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        BaseMessage that = (BaseMessage)o;

        boolean result = equalLists(sales, that.sales);
        if (!result)
            return false;

        return true;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{Message=[");
        sales.stream().forEach(s -> sb.append(s.toString()).append(","));
        sb.deleteCharAt(sb.toString().length()-1);
        sb.append("]}");        
        return sb.toString();
    }
}
