package geektrust.challenges.family;

import java.util.HashMap;
import java.util.Scanner;

public class WhosYourDaddy {
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
			String rel = part2[1].trim();
			Person person = tree.persons.get(per);
			if(null==person){
				System.out.println(per+" is not part of the Shan family");
				return;
			}
			Person relative = tree.persons.get(rel);
			if(null==relative){
				System.out.println(rel+" is not part of the Shan family");
				return;
			}
			if(per.equalsIgnoreCase(rel)){
				System.out.println("Self");
			}else{
				HashMap<String, String> mat = person.getRelationMatrix();
				if(null != mat.get(relative.getName())){
					System.out.println(mat.get(relative.getName()));
				}else{
					mat = relative.getRelationMatrix();
					if(null != mat.get(person.getName())){
						System.out.println(mat.get(person.getName()));
					}else{
						System.out.println("No relationship mapped in these two individuals yet");
					}
				}
			}
			System.out.println("Type 'Exit' to terminate execution.");
		}
	}
}
