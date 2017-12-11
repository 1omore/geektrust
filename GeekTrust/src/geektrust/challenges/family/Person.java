package geektrust.challenges.family;

import java.util.ArrayList;

public class Person {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Person getFather() {
		return father;
	}

	public void setFather(Person father) {
		this.father = father;
	}

	public Person getMother() {
		return mother;
	}

	public void setMother(Person mother) {
		this.mother = mother;
	}

	public Person getGrandFather() {
		return grandFather;
	}

	public void setGrandFather(Person grandFather) {
		this.grandFather = grandFather;
	}

	public Person getGrandMother() {
		return grandMother;
	}

	public void setGrandMother(Person grandMother) {
		this.grandMother = grandMother;
	}

	public ArrayList<Person> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Person> children) {
		this.children = children;
	}

	public ArrayList<Person> getGrandChildren() {
		return grandChildren;
	}

	public void setGrandChildren(ArrayList<Person> grandChildren) {
		this.grandChildren = grandChildren;
	}

	public ArrayList<Person> getSiblings() {
		return siblings;
	}

	public void setSiblings(ArrayList<Person> siblings) {
		this.siblings = siblings;
	}

	public ArrayList<Person> getInLaws() {
		return inLaws;
	}

	public void setInLaws(ArrayList<Person> inLaws) {
		this.inLaws = inLaws;
	}

	public ArrayList<Person> getPatUncles() {
		return patUncles;
	}

	public void setPatUncles(ArrayList<Person> patUncles) {
		this.patUncles = patUncles;
	}

	public ArrayList<Person> getMatUncles() {
		return matUncles;
	}

	public void setMatUncles(ArrayList<Person> matUncles) {
		this.matUncles = matUncles;
	}

	public ArrayList<Person> getPatAunts() {
		return patAunts;
	}

	public void setPatAunts(ArrayList<Person> patAunts) {
		this.patAunts = patAunts;
	}

	public ArrayList<Person> getMatAunts() {
		return matAunts;
	}

	public void setMatAunts(ArrayList<Person> matAunts) {
		this.matAunts = matAunts;
	}

	public ArrayList<Person> getCousins() {
		return cousins;
	}

	public void setCousins(ArrayList<Person> cousins) {
		this.cousins = cousins;
	}

	public Person getSpouse() {
		return spouse;
	}

	private String name;
	private char gender;
	private Person spouse;
	private Person father;
	private Person mother;
	private Person grandFather;
	private Person grandMother;
	private ArrayList<Person> children;
	private ArrayList<Person> grandChildren;
	private ArrayList<Person> siblings;
	private ArrayList<Person> inLaws;
	private ArrayList<Person> patUncles;
	private ArrayList<Person> matUncles;
	private ArrayList<Person> patAunts;
	private ArrayList<Person> matAunts;
	private ArrayList<Person> cousins;

	Person(String name, char gender) {
		this.name = name;
		this.gender = gender;
		this.spouse = null;
		this.grandFather=null;
		this.grandMother=null;
		children = new ArrayList<Person>();
		grandChildren = new ArrayList<Person>();
		siblings = new ArrayList<Person>();
		inLaws = new ArrayList<Person>();
		patUncles = new ArrayList<Person>();
		patAunts = new ArrayList<Person>();
		matUncles = new ArrayList<Person>();
		matAunts = new ArrayList<Person>();
		cousins = new ArrayList<Person>();
	}

	public void setSpouse(Person person) {
		this.spouse = person;
		person.spouse = this;
		updateRelationships(person);
	}

	public void updateRelationships(Person person) {
		// Siblings
		if (null != person.father) {
			ArrayList<Person> siblings = person.father.children;
			if (null != siblings && siblings.size() > 0) {
				for (Person sibling : siblings) {
					if (sibling.name != person.name) {
						person.siblings.add(sibling);
						sibling.siblings.add(person);
					}
				}
			}
		}

		// Grand Children
		if (null != person.father && null != person.father.father) {
			person.grandFather = person.father.father;
			person.grandMother = person.father.mother;
			person.father.father.grandChildren.add(person);
			person.father.mother.grandChildren.add(person);
		}

		// Paternal Uncles && Aunts
		if (null != person.father) {
			ArrayList<Person> fathersSiblings = person.father.siblings;
			if (null != fathersSiblings && fathersSiblings.size() > 0) {
				for (Person sibling : fathersSiblings) {
					if (sibling.gender == 'M') {
						person.patUncles.add(sibling);
					}
					if (sibling.gender == 'F') {
						person.patAunts.add(sibling);
					}
				}
			}
			ArrayList<Person> fathersInLaws = person.father.inLaws;
			if (null != fathersInLaws && fathersInLaws.size() > 0) {
				for (Person sibling : fathersInLaws) {
					if (sibling.gender == 'M') {
						person.patUncles.add(sibling);
					}
					if (sibling.gender == 'F') {
						person.patAunts.add(sibling);
					}
				}
			}
		}

		// Maternal Uncles & Aunts
		if (null != person.mother) {
			ArrayList<Person> mothersSiblings = person.mother.siblings;
			if (null != mothersSiblings && mothersSiblings.size() > 0) {
				for (Person sibling : mothersSiblings) {
					if (sibling.gender == 'M') {
						person.matUncles.add(sibling);
					}
					if (sibling.gender == 'F') {
						person.matAunts.add(sibling);
					}
				}
			}
			ArrayList<Person> mothersInLaws = person.mother.inLaws;
			if (null != mothersInLaws && mothersInLaws.size() > 0) {
				for (Person sibling : mothersInLaws) {
					if (sibling.gender == 'M') {
						person.matUncles.add(sibling);
					}
					if (sibling.gender == 'F') {
						person.matAunts.add(sibling);
					}
				}
			}
		}

		// In Laws
		if (null != person.spouse) {
			ArrayList<Person> inLaws = person.spouse.siblings;
			if (null != inLaws && inLaws.size() > 0) {
				for (Person inlaw : inLaws) {
					person.inLaws.add(inlaw);
					inlaw.inLaws.add(person);
				}
			}
		}
		ArrayList<Person> siblings = person.siblings;
		if (null != siblings && siblings.size() > 0) {
			for (Person sib : siblings) {
				if (null != sib.spouse) {
					person.inLaws.add(sib.spouse);
					sib.spouse.inLaws.add(person);
				}
			}
		}
	}

	public void addChild(String name, char gender) {
		Person child = new Person(name, gender);
		this.children.add(child);
		child.father = this;
		if (null != this.spouse) {
			child.mother = this.spouse;
		}
		updateRelationships(child);
	}

	public void setChild(Person child) {
		this.children.add(child);
		this.spouse.children.add(child);
		child.father = this;
		if (null != this.spouse) {
			child.mother = this.spouse;
		}
		updateRelationships(child);
	}
	
	public void printPersonsRelationships(Person person){
		System.out.println("===================================================================");
		System.out.println("Relationships of "+person.name);
		if(null != person.grandFather){
			System.out.println("Grandfather : "+person.grandFather.name);
		}
		if(null != person.grandMother){
			System.out.println("Grandmother : "+person.grandMother.name);
		}
		if(null != person.father){
			System.out.println("Father : "+person.father.name);
		}
		if(null != person.mother){
			System.out.println("Mother : "+person.mother.name);
		}
		if(null != person.spouse){
			if(person.spouse.gender=='M'){
				System.out.println("Husband : "+person.spouse.name);
			}else{
				System.out.println("Wife : "+person.spouse.name);
			}
		}
		if(null != person.siblings && person.siblings.size()>0){
			System.out.println("Siblings : ");
			for(Person sib : person.siblings){
				if(sib.gender=='M'){
					System.out.println("	Brother : "+sib.name);
				}else{
					System.out.println("	Sister : "+sib.name);
				}
			}
		}
		if(null != person.children && person.children.size()>0){
			System.out.println("Children : ");
			for(Person child : person.children){
				if(child.gender=='M'){
					System.out.println("	Son : "+child.name);
				}else{
					System.out.println("	Daughter : "+child.name);
				}
			}
		}
		if(null != person.grandChildren && person.grandChildren.size()>0){
			System.out.println("Grandhildren : ");
			for(Person child : person.grandChildren){
				if(child.gender=='M'){
					System.out.println("	Grandson : "+child.name);
				}else{
					System.out.println("	Granddaughter : "+child.name);
				}
			}
		}
		if(null != person.inLaws && person.inLaws.size()>0){
			System.out.println("In Laws :");
			for(Person sib : person.inLaws){
				if(sib.gender=='M'){
					System.out.println("	Brother-in-Law : "+sib.name);
				}else{
					System.out.println("	Sister-in-Law : "+sib.name);
				}
			}
		}
		if(null != person.matAunts && person.matAunts.size()>0){
			for(Person aunt : person.matAunts){
				System.out.println("Maternal Aunt : "+aunt.name);
			}
		}
		if(null != person.patAunts && person.patAunts.size()>0){
			for(Person aunt : person.patAunts){
				System.out.println("Paternal Aunt : "+aunt.name);
			}
		}
		if(null != person.matUncles && person.matUncles.size()>0){
			for(Person uncle : person.matUncles){
				System.out.println("Maternal Uncle : "+uncle.name);
			}
		}
		if(null != person.patUncles && person.patUncles.size()>0){
			for(Person uncle : person.patUncles){
				System.out.println("Paternal Uncle : "+uncle.name);
			}
		}
		System.out.println("===================================================================");
	}
}
