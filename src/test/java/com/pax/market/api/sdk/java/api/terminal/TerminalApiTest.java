/*
 * *******************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2017 PAX Technology, Inc. All rights reserved.
 * *******************************************************************************
 */
package com.pax.market.api.sdk.java.api.terminal;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.terminal.TerminalApi.TerminalSearchOrderBy;
import com.pax.market.api.sdk.java.api.terminal.TerminalApi.TerminalStatus;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalCreateRequest;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalDTO;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalUpdateRequest;
import com.pax.market.api.sdk.java.api.test.TestConstants;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;

/**
 *
 * @author tanjie
 * @date 2018-07-02
 */
public class TerminalApiTest {
	private static final Logger logger = LoggerFactory.getLogger(TerminalApiTest.class.getSimpleName());
	
	static TerminalApi terminalApi;
	
	static Long createdTerminalId = 0L;
	
    public void init(){
    	terminalApi = new  TerminalApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void testSearchTerminalList(){
    	Result<TerminalDTO> result = terminalApi.searchTerminal(1, 10, TerminalSearchOrderBy.Name, TerminalStatus.Inactive, "14494956");
		System.out.println("search result: " + result);
		System.out.println(EnhancedJsonUtils.toJson(result));
    }
    
    @Test
    public void testGetTerminal(){
    	Result<TerminalDTO> result = terminalApi.getTerminal(375977111L);
		System.out.println("get result: " + result);
		System.out.println(EnhancedJsonUtils.toJson(result));
    }
    
    @Test
    public void testCreateTerminal() {
    	TerminalCreateRequest createReq = new TerminalCreateRequest();
    	createReq.setName("KFC-TML-03");
    	createReq.setMerchantName("demo");
    	createReq.setResellerName("tan");
    	createReq.setLocation("USA");
    	createReq.setSerialNo("TJ000002");
    	createReq.setModelName("A920");

    	createReq.setStatus(TerminalStatus.Active);
    	Result<TerminalDTO> result = terminalApi.createTerminal(createReq);
    	if(result.getBusinessCode() == 0) {
    		createdTerminalId = result.getData().getId();
    	}
    	System.out.println("create result: " + result); 
    	System.out.println(EnhancedJsonUtils.toJson(result));

    }
    
    @Test
    public void testUpdateTerminal() {
    	TerminalUpdateRequest updateReq = new TerminalUpdateRequest();
    	updateReq.setName("KFC-TML-JS");
    	updateReq.setModelName("A920");
    	updateReq.setLocation("CN");
    	updateReq.setSerialNo("AD0099");
    	updateReq.setResellerName("New York");
    	updateReq.setMerchantName("KFC");
    	
    	Result<TerminalDTO> result = terminalApi.updateTerminal(316995L, updateReq);
    	System.out.println("update result: " + result); 
    	System.out.println(EnhancedJsonUtils.toJson(result));
    }
    
    
    @Test
    public void testActiveTerminal(){
    	Result<String> result = terminalApi.activateTerminal(907560L);
    	System.out.println("active result: " + result);
    	System.out.println(EnhancedJsonUtils.toJson(result));
        
    }
    
    @Test
    public void testDisableTerminal(){
    	Result<String> result = terminalApi.disableTerminal(907560L);
    	System.out.println("disable result: " + result); 
    }
    
    @Test
    public void testDeleteTerminal() {
    	Result<String> result = terminalApi.deleteTerminal(createdTerminalId);
    	System.out.println("delete result: " + result);
    }
    
}
