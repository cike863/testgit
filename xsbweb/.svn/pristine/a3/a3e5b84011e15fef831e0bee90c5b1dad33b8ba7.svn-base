package com.xsbweb.util;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultipleDataSource extends AbstractRoutingDataSource{
	private Logger log = Logger.getLogger(MultipleDataSource.class);
	private static final ThreadLocal<String> dataSourceKey = new ThreadLocal<String>();

    public static void setDataSourceKey(String dataSource) {
    	System.out.println("dataSource:"+dataSource);
        dataSourceKey.set(dataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
    	log.info("determineCurrentLookupKey========="+dataSourceKey.get());
    	
        return dataSourceKey.get();
    }
    public static Object getDataSourceKey(){
    	return dataSourceKey.get();
    }
    public static void clearDataSourceType(){
    	dataSourceKey.remove();
    }
}
