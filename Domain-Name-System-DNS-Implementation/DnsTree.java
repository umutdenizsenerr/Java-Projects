
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
/**
 * Defines the main DNS structure.
 * @author Umut Deniz Sener
 *
 */
public class DnsTree{
	/**
	 * Root of tree
	 */
	DnsNode root;
	/**
	 * Map that contains all records
	 */
	Map<String, Set<String>> map  = new TreeMap();
	/**
	 * Constructor of DnsTree Class
	 */
	public DnsTree() {
		this.root = new DnsNode();
			}
	/**
	 * inserts a new record for a given domain name.
	 * @param domainName domain Name
	 * @param ipAddress ip address
	 */
	public void insertRecord(String domainName, String ipAddress) {
		ArrayList<String> arr = new ArrayList<String>();
		String domain = domainName;
		while(domainName.contains(".")) {
			arr.add(domainName.substring(domainName.lastIndexOf('.')+1)) ;
		domainName= domainName.substring(0,domainName.lastIndexOf('.'));
			}

		arr.add(domainName);
		DnsNode current = root;
	

for(int i = 0 ; i<arr.size(); i++) {
	if(!current.childNodeList.containsKey(arr.get(i))) {
		DnsNode child = new DnsNode();
		if(i==arr.size()-1) {
			child.domainName=domain;
			child.ipAddresses.add(ipAddress);
			child.ipAdressesQueue.add(ipAddress);
			
			child.validDomain=true;
		}
		current.childNodeList.put(arr.get(i), child);
		
	    current=child;
	}
	else {
		
		current = current.childNodeList.get(arr.get(i));
		if(i==arr.size()-1) {
			current.validDomain=true;
			current.domainName = domain;
			current.ipAddresses.add(ipAddress);
			current.ipAdressesQueue.add(ipAddress);
		}	
	}
	
}
map.put(domain, setDomain(domain));
		 }
	 /**
	  * removes the node with the given domainName from the tree.
	  * @param domainName domain name
	  * @return whether it is succesfully removed
	  */
	public boolean removeRecord(String domainName) {
		String domain = domainName;

		ArrayList<String> arr = new ArrayList<String>();
		DnsNode current = root;
				while(domainName.contains(".")) {
					arr.add(domainName.substring(domainName.lastIndexOf('.')+1));
				domainName= domainName.substring(0,domainName.lastIndexOf('.'));
					}
				arr.add(domainName);
				for(int i = 0 ; i<arr.size(); i++) {
					if(current.childNodeList.containsKey(arr.get(i))) {
						DnsNode temp = current;
						current = current.childNodeList.get(arr.get(i));
						if(i==arr.size()-1) {
							
							if(current.childNodeList.isEmpty()) {
								map.remove(domain);
							temp.childNodeList.remove(arr.get(i));
							return true;
																					
							}
							else {
								current.ipAddresses.clear();
								
								current.ipAdressesQueue.clear();

								if(current.validDomain==true) {
									map.remove(domain);

								current.validDomain=false;
								return true;}
								else {
									map.remove(domain);

									current.validDomain=false;
									return true;
								}
							}
							
						}
									
						}	
			
				}
		
		return false;
	}
	/**
	 * removes the given ipAddress of a DNS node with the given domainName
	 * @param domainName domain name
	 * @param ipAddress ip address
	 * @return whether it is succesfully removed
	 */
	public boolean removeRecord(String domainName, String ipAddress) {
		String domain = domainName;
ArrayList<String> arr = new ArrayList<String>();
DnsNode current = root;
		while(domainName.contains(".")) {
			arr.add(domainName.substring(domainName.lastIndexOf('.')+1));
		domainName= domainName.substring(0,domainName.lastIndexOf('.'));
			}

		arr.add(domainName);
		for(int i = 0 ; i<arr.size(); i++) {
			if(current.childNodeList.containsKey(arr.get(i))) {
				
				current = current.childNodeList.get(arr.get(i));
				if(i==arr.size()-1) {
							if(current.ipAddresses.size()==1) {
								removeRecord(domain);
								return true;
							}
							else{


								current.ipAdressesQueue.remove(ipAddress);

									return current.ipAddresses.remove(ipAddress);
							}
					
				}
							
				}	
			
		}
			
			
	return false;
	}
	/**
	 * queries a domain name within the DNS
	 * @param domainName domain name
	 * @return the next IP address of the domainName, following the Round Robin mechanism
	 */
	public String queryDomain(String domainName) {
		
		String k  = null;
		ArrayList<String> arr = new ArrayList<String>();
		DnsNode current = root;
		
				while(domainName.contains(".")) {
					arr.add(domainName.substring(domainName.lastIndexOf('.')+1));
				domainName= domainName.substring(0,domainName.lastIndexOf('.'));
					}

				arr.add(domainName);
				for(int i = 0 ; i<arr.size(); i++) {
					if(current.childNodeList.containsKey(arr.get(i))) {
						
						current = current.childNodeList.get(arr.get(i));
						if(i==arr.size()-1) {
							if(!current.ipAdressesQueue.isEmpty()) {
							k = current.ipAdressesQueue.poll();
							current.ipAdressesQueue.add(k);}
					
							
							
						}
									
						}	
					
				}
	
	return k;
	}
	/**
	 * queries a domain name within the DNS without effect roundrobin mechanism
	 * @param domainName domain name
	 * @return the next IP address
	 */
	public Set setDomain(String domainName) {
		Set k  = null;
		ArrayList<String> arr = new ArrayList<String>();
		DnsNode current = root;
				while(domainName.contains(".")) {
					arr.add(domainName.substring(domainName.lastIndexOf('.')+1));
				domainName= domainName.substring(0,domainName.lastIndexOf('.'));
					}

				arr.add(domainName);
				for(int i = 0 ; i<arr.size(); i++) {
					if(current.childNodeList.containsKey(arr.get(i))) {
						
						current = current.childNodeList.get(arr.get(i));
						if(i==arr.size()-1) {
							 k = current.ipAddresses;
						
							
							
						}
									
						}	
					
				}
	
	return k;
	}
	/**
	 * to get all all the valid domain names in the DNS mechanism 
	 * @return all the valid domain names in the DNS mechanism 
	 */
	public Map<String, Set<String>> getAllRecords(){
return map;
	}
	public DnsNode getRoot() {
		return this.root;
	}
	

}
//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

