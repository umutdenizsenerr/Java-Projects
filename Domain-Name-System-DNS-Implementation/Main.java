package question;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
/**
 * Defines the main class of simple DNS system
 * @author Umut Deniz Sener
 *
 */
public class Main {

    public static void main(String[] args) {
        DnsTree tree = new DnsTree();
        Client client = new Client("44.22.11.22", tree);

        tree.insertRecord("bbc.co.uk", "7.7.7.7");
        tree.insertRecord("cambridge.ac.uk", "8.8.8.8");
        tree.insertRecord("google.com", "3.3.3.3");
        tree.insertRecord("mail.google.com", "4.4.4.4");
        tree.insertRecord("twitter.com", "5.5.5.5");
        tree.insertRecord("developer.twitter.com", "6.6.6.6");
       tree.removeRecord("bbc.co.uk");
       System.out.println(tree.getAllRecords());

}
}