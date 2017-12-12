package geektrust.challenges.family;

import java.util.Scanner;

public class MeetTheFamily {
	public static void main(String[] aa){
		MeetTheFamily family = new MeetTheFamily();
		Scanner sc = new Scanner(System.in);
		String in = sc.nextLine().trim();
		String[] cut = in.split(";");
		String[] part1 = cut[0].split("=");
		String per = part1[1].trim();
		String[] part2 = cut[1].split("=");
		String relation = part2[1].trim();
		FamilyTree tree = new FamilyTree();
		Person person = tree.persons.get(per);
		if(null == person){
			System.out.println("No such member in the family.");
			System.out.println("Please check the spelling of the name.");
			return;
		}
		family.getRelatives(person,relation);
	}

	public void getRelatives(Person person, String relation) {
		switch(relation){
		case "Paternal Uncle":
			listPaternalUncles(person);
			break;
		case "Maternal Uncle":
			listMaternalUncles(person);
			break;
		case "Paternal Aunt":
			listPaternalAunts(person);
			break;
		case "Maternal Aunt":
			listMaternalAunts(person);
			break;
		case "Sister-in-law":
			listSisterInLaw(person);
			break;
		case "Brother-in-law":
			listBrotherInLaw(person);
			break;
		case "Cousins":
			listCousins(person);
			break;
		case "Father":
			if(null != person.getFather()){
				System.out.println(person.getFather().getName());
			}else{
				System.out.println("No such relationship has been provided for "+person.getName());
			}
			break;
		case "Mother":
			if(null != person.getMother()){
				System.out.println(person.getMother().getName());
			}else{
				System.out.println("No such relationship has been provided for "+person.getName());
			}
			break;
		case "Children":
			listChildren(person);
			break;
		case "Son":
			listSons(person);
			break;
		case "Daughter":
			listDaughters(person);
			break;
		case "Brothers":
			listBrothers(person);
			break;
		case "Sisters":
			listSisters(person);
			break;
		case "Grand Daughter":
			listGrandDaughters(person);
			break;
		case "Grand Son":
			listGrandSons(person);
			break;
		case "Grand Children":
			listGrandChildren(person);
			break;
		default :
			System.out.println("This relationship is not supported yet.");
		}
	}

	private void listMaternalAunts(Person person) {
		if(null != person.getMatAunts() && person.getMatAunts().size()>0){
			String ans="";
			for(Person aunt : person.getMatAunts()){
				ans+=", "+aunt.getName();
			}
			output(ans, "Maternal Aunts", person.getName());
		}
	}

	private void listPaternalAunts(Person person) {
		if(null != person.getPatAunts() && person.getPatAunts().size()>0){
			String ans="";
			for(Person aunt : person.getPatAunts()){
				ans+=", "+aunt.getName();
			}
			output(ans, "Paternal Aunts", person.getName());
		}
	}

	private void listMaternalUncles(Person person) {
		String ans="";
		if(null != person.getMatUncles() && person.getMatUncles().size()>0){
			for(Person uncle : person.getMatUncles()){
				ans+=", "+uncle.getName();
			}
		}
		output(ans, "Maternal Uncles", person.getName());
	}

	private void listSisterInLaw(Person person) {
		String ans="";
		if(null != person.getInLaws() && person.getInLaws().size()>0){
			for(Person sib : person.getInLaws()){
				if(sib.getGender()=='F'){
					ans+=", "+sib.getName();
				}
			}
		}
		output(ans, "Sisters-in-law", person.getName());
	}

	private void listBrotherInLaw(Person person) {
		String ans="";
		if(null != person.getInLaws() && person.getInLaws().size()>0){
			for(Person sib : person.getInLaws()){
				if(sib.getGender()=='M'){
					ans+=", "+sib.getName();
				}
			}
		}
		output(ans, "Brothers-in-law", person.getName());
	}

	private void listCousins(Person person) {
		String ans="";
		if(null != person.getCousins() && person.getCousins().size()>0){
			for(Person gch : person.getCousins()){
				ans+=", "+gch.getName();
			}
		}
		output(ans, "Cousins", person.getName());
	}

	private void listChildren(Person person) {
		String ans="";
		if(null != person.getChildren() && person.getChildren().size()>0){
			for(Person gch : person.getChildren()){
				ans+=", "+gch.getName();
			}
		}
		output(ans, "Children", person.getName());
	}

	private void listBrothers(Person person) {
		String ans="";
		if(null != person.getSiblings() && person.getSiblings().size()>0){
			for(Person sib : person.getSiblings()){
				if(sib.getGender()=='M'){
					ans+=", "+sib.getName();
				}
			}
		}
		output(ans, "Brothers", person.getName());
	}

	private void listSisters(Person person) {
		String ans="";
		if(null != person.getSiblings() && person.getSiblings().size()>0){
			for(Person sib : person.getSiblings()){
				if(sib.getGender()=='F'){
					ans+=", "+sib.getName();
				}
			}
		}
		output(ans, "Sisters", person.getName());
	}

	private void listSons(Person person) {
		String ans="";
		if(null != person.getChildren() && person.getChildren().size()>0){
			for(Person gch : person.getChildren()){
				if(gch.getGender()=='M'){
					ans+=", "+gch.getName();
				}
			}
		}
		output(ans, "Sons", person.getName());
	}

	private void listDaughters(Person person) {
		String ans="";
		if(null != person.getChildren() && person.getChildren().size()>0){
			for(Person gch : person.getChildren()){
				if(gch.getGender()=='F'){
					ans+=", "+gch.getName();
				}
			}
		}
		output(ans, "Daughters", person.getName());
	}

	private void listGrandDaughters(Person person) {
		String ans="";
		if(null != person.getGrandChildren() && person.getGrandChildren().size()>0){
			for(Person gch : person.getGrandChildren()){
				if(gch.getGender()=='F'){
					ans+=", "+gch.getName();
				}
			}
		}
		output(ans, "Grand Daughters", person.getName());
	}

	private void listGrandSons(Person person) {
		String ans="";
		if(null != person.getGrandChildren() && person.getGrandChildren().size()>0){
			for(Person gch : person.getGrandChildren()){
				if(gch.getGender()=='M'){
					ans+=", "+gch.getName();
				}
			}
		}
		output(ans, "Grand Sons", person.getName());
	}

	private void listGrandChildren(Person person) {
		String ans="";
		if(null != person.getGrandChildren() && person.getGrandChildren().size()>0){
			for(Person gch : person.getGrandChildren()){
				ans+=", "+gch.getName();
			}
		}
		output(ans, "Grand Children", person.getName());
	}

	private void listPaternalUncles(Person person) {
		String ans="";
		if(null != person.getPatUncles() && person.getPatUncles().size()>0){
			for(Person uncle : person.getPatUncles()){
				ans+=", "+uncle.getName();
			}
		}
		output(ans, "Paternal Uncles", person.getName());
	}
	
	private void output(String ans,String relation,String name){
		if(!"".equals(ans)){
			ans=ans.replaceFirst(", ", "");
			System.out.println(ans);
		}else{
			System.out.println(name+" does not have any "+relation);
		}
	}
}
