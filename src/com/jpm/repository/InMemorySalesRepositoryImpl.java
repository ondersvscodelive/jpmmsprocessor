package com.jpm.repository;

import java.util.*;
import java.math.*;

import com.jpm.model.AdjustmentSaleMessage;
import com.jpm.model.IMessage;
import com.jpm.model.Sale;

public class InMemorySalesRepositoryImpl implements SalesRepository{
    
    private List<Sale> salesRepo;
    private List<IMessage> messagesRepo;
    
    public InMemorySalesRepositoryImpl() {
        this.salesRepo = new ArrayList<>();
        this.messagesRepo = new ArrayList<>();
    }

    @Override
    public String generateSaleReport() {
        System.out.println("Generating Sales Report");
        
        Map<String, BigDecimal> totalSum = new HashMap<>();
        Map<String, Integer> counts = new HashMap<>();

        BigDecimal total = BigDecimal.ZERO;
        int totalUnitsSold = 0;
        for (Sale sale : salesRepo) {
            if (!totalSum.containsKey(sale.getProductName())) {
                BigDecimal curSum = sale.getUnitPrice().multiply(BigDecimal.valueOf(sale.getTotalUnits()));
                totalSum.put(sale.getProductName(), curSum);

            } else {
                BigDecimal curSum = totalSum.get(sale.getProductName());
                curSum = curSum.add(sale.getUnitPrice().multiply(BigDecimal.valueOf(sale.getTotalUnits())));
                totalSum.put(sale.getProductName(), curSum);
            }

            if (!counts.containsKey(sale.getProductName())) {
                counts.put(sale.getProductName(), sale.getTotalUnits());
            } else {
                counts.put(sale.getProductName(), counts.get(sale.getProductName()) + sale.getTotalUnits());
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("*** SALES REPORT ***\n");
        for (Map.Entry<String, BigDecimal> entry : totalSum.entrySet()) {
            totalUnitsSold += counts.get(entry.getKey());
            sb.append("# ")
                    .append(entry.getKey())
                    .append(" - units sold: ")
                    .append(counts.get(entry.getKey()))
                    .append(" , sales amount: £")
                    .append(entry.getValue().toString())
                    .append("\n");
            total = total.add(entry.getValue());
        }
        sb.append("Total units sold - " + totalUnitsSold)
                .append("\n")
                .append("All sales - £")
                .append(total.toString())
                .append("\n\n");

        return sb.toString();
    }

    @Override
    public String generateAdjustmentReport() {
        System.out.println("Generating Adjustment Report");
        StringBuilder sb = new StringBuilder();
        try {
            Thread.currentThread().sleep(5000);
            
            
            sb.append("*** ADJUSTMENTS RECORDS ***\n");
            for (IMessage message : messagesRepo) {
                if(message instanceof AdjustmentSaleMessage) {
                    sb.append("# ")
                        .append(message)
                        .append("\n");
                }
            }
            
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return sb.toString();
    }

    @Override
    public void add(IMessage message) {
        salesRepo.addAll(message.getSales());
        messagesRepo.add(message);
    }

}
