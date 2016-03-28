package com.xsbweb.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MathUtil {
	 public static List<Long> Fun(Long num){
		int max = (int) (Math.log(num)/Math.log((double)2))+1;
		long j = 0;
		List<Long> result = new ArrayList<Long>();
	
		for(int i = 0; i < max; ++i) {
			j = (num >>> i) % 2;  // 取出右移的那一位
			j = j << i;           // j * 2的次幂 = 当前的十进制数	 
			if(j > 0) {
				result.add(j);
			  }
		}
		return result;
	 }
	 public static void main(String[] args){
		 //9007199254740992, 18014398509481984, 36028797018963968, 72057594037927936, 144115188075855872, 288230376151711744, 576460752303423488, 1152921504606846976, 2305843009213693952, 4611686018427387904]
		 long input = Long.parseLong("9214364837600034816");
		 long a =      Long.parseLong("9007199254740992");
		 long b =    Long.parseLong("18014398509481984");
		 long c =    Long.parseLong("36028797018963968");
		 long d =    Long.parseLong("72057594037927936");
		 //long e =    Long.parseLong("72057594037927936");
		 //long f =   Long.parseLong("144115188075855872");
		 long g =  Long.parseLong("288230376151711744");
		 long h =  Long.parseLong("576460752303423488");
		 long i =  Long.parseLong("1152921504606846976");
		 long j =  Long.parseLong("2305843009213693952");
		 long k = Long.parseLong("4611686018427387904");
		 
		 long result =a+b+c+d+g+h+i+j+k;
		 Object []test={a,b,c,d,g,h,i,j,k};
		 List<Long>list= Fun(input);
		 System.out.println(list.toString());
		 System.out.println(input-result);
	 }
}
