
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package question;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.Set;
/**
 * defines the client side consists of simple implementation of the cache mechanism
 * @author Umut Deniz Sener
 *
 */
public class Client{
	/**
	 * for accessing the tree structure
	 */
	private DnsTree root;
	/**
	 * ip address of client
	 */
	private String ipAddress;
	/**
	 * for arranging cache mechanism
	 */
	int i = 0;
	/**
	 * The cache of a client 
	 */
	CachedContent[] cacheList=new CachedContent[10];
	/**
	 * Constructor of Client class
	 * @param ipAddress ip address of client
	 * @param root root of dns tree
	 */
	public Client(String ipAddress, DnsTree root){
		this.i=i;
		this.ipAddress=ipAddress;
		this.root=root;
		this.cacheList=cacheList ; 

	
	}
	/**
	 * returns the IP address of the requested domain name
	 * @param domainName requested domain name
	 * @return the IP address of the requested domain name
	 */
	public String sendRequest(String domainName) {

	
	for(int k = 0 ; k<i ;k++) {
	if(this.cacheList[k].domainName==domainName) {

		this.cacheList[k].hitNo++;
	
		return this.cacheList[k].ipAddress;
	}
	}

	String result = root.queryDomain(domainName);
	this.addToCache(domainName, result);


		

		

		return result;
		
	}
	/**
	 * After obtaining an IP address, adds ip address to the cache
	 * @param domainName domain name
	 * @param ipAddress ip address
	 */
	public void addToCache(String domainName, String ipAddress) {

		CachedContent cached = new CachedContent(domainName,ipAddress,0);
		int temp = 999;
		int a = 0;
		if(i<10) {
		
		cacheList[i]= cached;
		i++;
		
		}
		else {
			
			for(int j = 0  ; j<cacheList.length;j++) {
				
				if(cacheList[j].hitNo<temp) {
				
					temp = cacheList[j].hitNo;
					a=j;
				}
			}
			cacheList[a]=cached;
		}
		
	}
	/**
	 * 
	 * a nested class for cache mechanism
	 *
	 */
private class CachedContent{
	/**
	 * domain name
	 */
	String domainName;
	/**
	 * ip address
	 */
	String ipAddress;
	/**
	 * shows how many times cache is used
	 */
	int hitNo;
public CachedContent(String domainName,String ipAddress,int hitNo) {
	this.domainName=domainName;
	this.ipAddress=ipAddress;
	this.hitNo=hitNo;
	
	
}

}









}
//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

