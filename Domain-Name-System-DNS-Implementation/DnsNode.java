
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package question;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
/**
 * defines each of the nodes in the DNS tree structure. 
 * @author Umut Deniz Sener
 *
 */
public class DnsNode{
	/**
	 * utilized for maintaining the tree structure
	 */
	Map<String, DnsNode> childNodeList;
	/**
	 * shows whether the current node is a valid domain name or just a subdomain that cannot have any IP address. 
	 */
	boolean validDomain;
	/**
	 * for storing the IP addresses as a list of a domain name
	 */
	Set<String> ipAddresses;
	/**
	 * for storing the IP addresses as a list of a domain name in queue
	 */
	Queue<String> ipAdressesQueue;
	/**
	 * domain name of the DnsNode
	 */
	String domainName;
	/**
	 * Constructor of DnsNode class
	 */
	public DnsNode() {
		this.domainName= domainName;
		this.childNodeList = new TreeMap();
		this.ipAddresses = new TreeSet();
		this.validDomain = false;
		this.ipAdressesQueue = new LinkedList();
		
		
		
	}
	/**
	 * to get ip addresses
	 * @return ip addresses
	 */
  Set<String> getIpAddresses() {
	return this.ipAddresses;
}
  /**
   * to get domain name
   * @return domain name
   */
  String getDomainName() {
	  return this.domainName;
  }
  /**
   * to get valid domain
   * @return valid domain
   */
  boolean getValidDomain() {
	  return this.validDomain;
  }
  /**
   * to get child node list
   * @return child node list
   */
  Map<String, DnsNode> getChildNodeList() {
	  return this.childNodeList;
	  
  }
	

}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

