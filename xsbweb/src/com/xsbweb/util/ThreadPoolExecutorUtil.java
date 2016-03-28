package com.xsbweb.util;

public class ThreadPoolExecutorUtil {

	public static Thread[] findAllThreads()
	{
	    ThreadGroup group = Thread.currentThread().getThreadGroup();
	    ThreadGroup topGroup = group;

	    /* 遍历线程组树，获取根线程组 */
	    while ( group != null )
	    {
	        topGroup    = group;
	        group        = group.getParent();
	    }
	    /* 激活的线程数加倍 */
	    int estimatedSize = topGroup.activeCount() * 2;
	    Thread[] slackList = new Thread[estimatedSize];

	    /* 获取根线程组的所有线程 */
	    int actualSize = topGroup.enumerate( slackList );
	    /* copy into a list that is the exact size */
	    Thread[] list = new Thread[actualSize];
	    System.arraycopy( slackList, 0, list, 0, actualSize );
	    return list;
	}
}
