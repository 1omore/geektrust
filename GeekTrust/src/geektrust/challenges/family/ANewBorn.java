package geektrust.challenges.family;

import java.util.Scanner;

public class ANewBorn {
	public static void main(String[] args) {
		FamilyTree tree = new FamilyTree();
		Scanner sc = new Scanner(System.in);
		while(true){
			String in = sc.nextLine().trim();
			if(in.equalsIgnoreCase("exit"))
				break;
			String[] cut = in.split(";");
			String[] part1 = cut[0].split("=");
			String per = part1[1].trim();
			String[] part2 = cut[1].split("=");
			String relation = part2[1].trim();
			Person person = tree.persons.get(per);
			if(null == person){
				System.out.println("No such member in the family.");
				return;
			}
			if(part1[0].trim().equalsIgnoreCase("Person")){
				MeetTheFamily family = new MeetTheFamily();
				family.getRelatives(person, relation);
			}else{
				if(part2[0].trim().equalsIgnoreCase("Daughter")){
					person.addChild(relation, 'F');
				}else{
					person.addChild(relation, 'M');
				}
				System.out.println("Child added to the family");
			}
			System.out.println("Enter another test case or type 'Exit' to terminate execution.");
		}
	}
}
