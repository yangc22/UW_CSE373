/* Chaoyi Yang
 * 02/10/17
 * CSE373
 * TA: Raquel Van Hofwegen
 * 
 * MyClient program saves all the contact name and the corresponding phone numbers
 * to the TextAssociator and each time you want to get the number of someone, you
 * could just enter the name.
 */
import java.util.Scanner;
import java.util.Set;

public class MyClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextAssociator sc = new TextAssociator();
		sc.addNewWord("Michael");
		sc.addAssociation("Michael", "Work-2069819887");
		sc.addAssociation("Michael", "Home-4254573324");
		sc.addAssociation("Michael", "Mobile-8103943254");
		sc.addNewWord("Jayson");
		sc.addAssociation("Jayson", "Work-2065197586");
		sc.addAssociation("Jayson", "Home-4256780987");
		sc.addNewWord("David");
		sc.addAssociation("David", "Work-2065597586");
		sc.addAssociation("David", "Home-4256780987");
		sc.addNewWord("Kalpanatit");
		sc.addAssociation("Kalpanatit", "Work-9255567586");
		sc.addAssociation("Kalpanatit", "Home-3102380987");
		sc.addNewWord("Acacia");
		sc.addAssociation("Acacia", "Home-4256555987");
		sc.addNewWord("Jessica");
		sc.addAssociation("Jessica", "Home-4256766667");
		sc.addNewWord("Shayne");
		sc.addAssociation("Shayne", "Work-2065937586");
		sc.addAssociation("Shayne", "Mobile-4256496987");
		sc.addNewWord("Shen");
		sc.addAssociation("Shen", "Home-4256988977");
		sc.addNewWord("Shawn");
		sc.addAssociation("Shawn", "Work-4256099987");
		sc.addNewWord("Money");
		sc.addAssociation("Money", "Home-4259780987");
		sc.addNewWord("Wanwisa");
		sc.addAssociation("Wanwisa", "Work-2006978587");
		sc.addAssociation("Wanwisa", "Home-4259988608");
		sc.addAssociation("Wanwisa", "Mobile-8104956254");
		sc.addNewWord("Penguin");
		sc.addAssociation("Penguin", "Work-4256789687");
		sc.addNewWord("Karen");
		sc.addAssociation("Karen", "Work-2060495867");
		sc.addAssociation("Karen", "Home-4254050697");
		sc.addAssociation("Karen", "Mobile-8100019484");
		sc.addNewWord("Arjit");
		sc.addAssociation("Arjit", "Mobile-4259687987");
		sc.addNewWord("Susie");
		sc.addAssociation("Susie", "Home-4785197586");
		sc.addAssociation("Susie", "Mobile-4256780987");
		sc.addNewWord("Marideth");
		sc.addAssociation("Marideth", "Work-2069666587");
		sc.addAssociation("Marideth", "Home-4254006608");
		sc.addAssociation("Marideth", "Mobile-8100099254");
		sc.addNewWord("Jamie");
		sc.addAssociation("Jamie", "Work-2065104856");
		sc.addAssociation("Jamie", "Home-4256704967");
		sc.addNewWord("Brent");
		sc.addAssociation("Brent", "Work-2065098906");
		sc.addNewWord("Maddey");
		sc.addAssociation("Maddey", "Work-2069719887");
		sc.addAssociation("Maddey", "Home-4254098798");
		sc.addAssociation("Maddey", "Mobile-8100593254");
		sc.addNewWord("Wendy");
		sc.addAssociation("Wendy", "Work-2069506856");
		System.out.println("Current contact list");
		sc.wordPrint();
		Scanner scan = new Scanner(System.in);
		String inputString = "";
		while (true) {
			System.out.print("Please input the person (only one) you would like to call? (enter \"exit\" to exit):");
			inputString = scan.nextLine();
			if (inputString.equals("exit")) {
				break;
			}
			Set<String> words = sc.getAssociations(inputString);
			for (String number : words) {
				System.out.println(number);
			}
		}
	}

}
