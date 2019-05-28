package com.jpm.service;

import com.jpm.model.IMessage;
import com.jpm.repository.SalesRepository;
import com.jpm.thirdparty.ThirdPartyMessageGenerator;

public class MessageProcessorImpl implements MessageProcessor {

    private SalesRepository salesRepository;
    private ThirdPartyMessageGenerator thirdParty;
    private int messageCount;
    private volatile boolean isPaused;

    public MessageProcessorImpl(SalesRepository salesRepository, ThirdPartyMessageGenerator thirdPartyMesageGeneratorImpl) {
        this.salesRepository = salesRepository;
        this.thirdParty = thirdPartyMesageGeneratorImpl;
        this.messageCount = 0;
        this.isPaused = false;
    }

    @Override
    public void startProcessing() {
        
        while(true) {
            IMessage message = thirdParty.getMessage();
            
            System.out.println(message.toString());
            
            if(isPaused) {
                System.out.println("Report of adjustments is running, skipping message= " + message.toString());
            } else {
                messageCount++;
                
                salesRepository.add(message);
                
                if(messageCount % 10 == 0) {
                    System.out.println(salesRepository.generateSaleReport());
                }
                
                if(messageCount == 50) {
                    isPaused = true;
                    Thread newAdjustmentThread = new Thread(() -> {
                        System.out.println(salesRepository.generateAdjustmentReport());
                        isPaused = false;
                    });
                    newAdjustmentThread.start();
                }
            }
            
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
