package com.jpm.runner;
import com.jpm.repository.InMemorySalesRepositoryImpl;
import com.jpm.repository.SalesRepository;
import com.jpm.service.MessageProcessor;
import com.jpm.service.MessageProcessorImpl;
import com.jpm.thirdparty.ThirdPartyMesageGeneratorImpl;

public class JPMRunner {

    public static void main(String[] args) {
       SalesRepository salesRepository = new InMemorySalesRepositoryImpl();
       MessageProcessor messageProcessor = new MessageProcessorImpl(salesRepository, new ThirdPartyMesageGeneratorImpl());
       messageProcessor.startProcessing();
    }

}
