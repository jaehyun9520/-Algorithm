package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Boj14002_가장긴증가하는부분수열4 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(in.readLine());
		int ans=0;
		int index=0;
		int arr[]=new int[n+1];
	    int dp[]=new int[n+1];
	    StringTokenizer st=new StringTokenizer(in.readLine(), " ");    
	    for(int i=1; i<=n; i++)
	    {
            arr[i]=Integer.parseInt(st.nextToken());	    	
	    }
	    for(int i=1; i<=n; i++)
	    {
	    	int len=0;
	    	for(int j=i-1; j>=1; j--)
	    	{ 		
	    		if( arr[j]<arr[i])
	    		{
	    			len=Math.max(dp[j],len);			
	    		}
	    	}
	    	
	    	dp[i]=len+1;
	    	
	    	if(ans<dp[i])
	    		index=i;
	    	ans=Math.max(ans,dp[i]);
	    } 
	    List<Integer> list=new ArrayList<>();
	    list.add(arr[index]);
	    
	    System.out.println(ans);

	   
	    for(int i=ans-1; i>=1; i--)
	    {
	    	
	    	
	    	for(int j=index-1; j>=1; j--)
		    {
		         
	    		if(i==dp[j]&&arr[index]>arr[j])
	    		{
	    			
	    			index=j;
	    			list.add(arr[j]);
	    		
	    			
	    		}
		    	
		    	
		    }	
	    	
	    	
	    }
	    
	Collections.sort(list);    
	    
	for(int i=0; i<list.size(); i++)
	{
		System.out.print(list.get(i)+" ");
		
	}

	}
}
