package geektrust.challenges.family;

import java.util.ArrayList;
import java.util.HashMap;

public class Person {
	private String name;
	private char gender;
	private Person spouse;
	private Person father;
	private Person mother;
	private Person grandFather;
	private Person grandMother;
	private Person greatGrandFather;
	private Person greatGrandMother;
	private ArrayList<Person> children;
	private ArrayList<Person> grandChildren;
	private ArrayList<Person> siblings;
	private ArrayList<Person> inLaws;
	private ArrayList<Person> patUncles;
	private ArrayList<Person> matUncles;
	private ArrayList<Person> patAunts;
	private ArrayList<Person> matAunts;
	private ArrayList<Person> cousins;
	private ArrayList<Person> greatGrandChildren;

	Person(String name, char gender) {
		this.name = name;
		this.gender = gender;
		this.spouse = null;
		this.grandFather=null;
		this.grandMother=null;
		this.greatGrandFather=null;
		this.greatGrandMother=null;
		children = new ArrayList<Person>();
		grandChildren = new ArrayList<Person>();
		siblings = new ArrayList<Person>();
		inLaws = new ArrayList<Person>();
		patUncles = new ArrayList<Person>();
		patAunts = new ArrayList<Person>();
		matUncles = new ArrayList<Person>();
		matAunts = new ArrayList<Person>();
		cousins = new ArrayList<Person>();
		greatGrandChildren = new ArrayList<>();
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
			if(null != person.father.father.father){
				person.greatGrandFather=person.father.father.father;
				person.greatGrandMother=person.father.father.mother;
				person.father.father.father.greatGrandChildren.add(person);
				person.father.father.mother.greatGrandChildren.add(person);
			}
			if(null != person.father.mother.father){
				person.greatGrandFather=person.father.mother.father;
				person.greatGrandMother=person.father.mother.mother;
				person.father.mother.father.greatGrandChildren.add(person);
				person.father.mother.mother.greatGrandChildren.add(person);
			}
		}
		if (null != person.mother && null != person.mother.father) {
			person.grandFather = person.mother.father;
			person.grandMother = person.mother.mother;
			person.mother.father.grandChildren.add(person);
			person.mother.mother.grandChildren.add(person);
			if(null != person.mother.father.father){
				person.greatGrandFather=person.mother.father.father;
				person.greatGrandMother=person.mother.father.mother;
				person.mother.father.father.greatGrandChildren.add(person);
				person.mother.father.mother.greatGrandChildren.add(person);
			}
			if(null != person.mother.mother.father){
				person.greatGrandFather=person.mother.mother.father;
				person.greatGrandMother=person.mother.mother.mother;
				person.mother.mother.father.greatGrandChildren.add(person);
				person.mother.mother.mother.greatGrandChildren.add(person);
			}
		}

		// Paternal Uncles && Aunts
		if (null != person.father && null != person.father.father) {
			ArrayList<Person> fathersSiblings = person.father.siblings;
			if (null != fathersSiblings && fathersSiblings.size() > 0) {
				for (Person sibling : fathersSiblings) {
					if (sibling.gender == 'M') {
						person.patUncles.add(sibling);
					}
					if (sibling.gender == 'F') {
						person.patAunts.add(sibling);
					}
					if(null != sibling.children && sibling.children.size()>0){
						for(Person cous : sibling.children){
							person.cousins.add(cous);
							cous.cousins.add(person);
						}
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
		if (null != person.mother && null != person.mother.father) {
			ArrayList<Person> mothersSiblings = person.mother.siblings;
			if (null != mothersSiblings && mothersSiblings.size() > 0) {
				for (Person sibling : mothersSiblings) {
					if (sibling.gender == 'M') {
						person.matUncles.add(sibling);
					}
					if (sibling.gender == 'F') {
						person.matAunts.add(sibling);
					}
					if(null != sibling.children && sibling.children.size()>0){
						for(Person cous : sibling.children){
							person.cousins.add(cous);
							cous.cousins.add(person);
						}
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
		
		//Niece/Nephew
		if(null != person.father || null != person.mother){
			if(null != person.siblings && person.siblings.size()>0){
				for(Person sib : person.siblings){
					if(null != sib.children && sib.children.size()>0){
						for(Person nn : sib.children){
							if(sib.gender=='M'){
								if(person.gender=='M'){
									nn.patUncles.add(person);
								}else{
									nn.patAunts.add(person);
								}
							}else{
								if(person.gender=='M'){
									nn.matUncles.add(person);
								}else{
									nn.matAunts.add(person);
								}
							}
						}
					}
				}
			}
		}else{
			if(null != person.inLaws && person.inLaws.size()>0){
				for(Person inlaw : person.inLaws){
					if(null != inlaw.children && inlaw.children.size()>0){
						for(Person nn : inlaw.children){
							if(inlaw.gender=='M'){
								if(person.gender=='M'){
									nn.patUncles.add(person);
								}else{
									nn.patAunts.add(person);
								}
							}else{
								if(person.gender=='M'){
									nn.matUncles.add(person);
								}else{
									nn.matAunts.add(person);
								}
							}
						}
					}
				}
			}
		}
	}
	
	public HashMap<String, String> getRelationMatrix(){
		HashMap<String, String> relMat = new HashMap<String, String>();
		if(null != this.greatGrandFather){
			relMat.put(this.grandFather.name, "Great Grand Father");
		}
		if(null != this.greatGrandMother){
			relMat.put(this.grandMother.name, "Great Grand Mother");
		}
		if(null != this.grandFather){
			relMat.put(this.grandFather.name, "Grand Father");
		}
		if(null != this.grandMother){
			relMat.put(this.grandMother.name, "Grand Mother");
		}
		if(null != this.father){
			relMat.put(this.father.name, "Father");
		}
		if(null != this.mother){
			relMat.put(this.mother.name, "Mother");
		}
		if(null != this.children && this.children.size()>0){
			for(Person child : this.children){
				if(child.gender=='M'){
					relMat.put(child.name, "Son");
				}else{
					relMat.put(child.name, "Daughter");
				}
			}
		}
		if(null != this.grandChildren && this.grandChildren.size()>0){
			for(Person child : this.grandChildren){
				if(child.gender=='M'){
					relMat.put(child.name, "Grand Son");
				}else{
					relMat.put(child.name, "Grand Daughter");
				}
			}
		}
		if(null != this.greatGrandChildren && this.greatGrandChildren.size()>0){
			for(Person child : this.greatGrandChildren){
				if(child.gender=='M'){
					relMat.put(child.name, "Great Grand Son");
				}else{
					relMat.put(child.name, "Great Grand Daughter");
				}
			}
		}
		if(null != this.spouse){
			if(this.spouse.gender=='M'){
				relMat.put(this.spouse.name, "Husband");
			}else{
				relMat.put(this.spouse.name, "Wife");
			}
		}
		if(null != this.siblings && this.siblings.size()>0){
			for(Person sib : this.siblings){
				if(sib.gender=='M'){
					relMat.put(sib.name, "Brother");
				}else{
					relMat.put(sib.name, "Sister");
				}
			}
		}
		if(null != this.matAunts && this.matAunts.size()>0){
			for(Person aunt : this.matAunts){
				relMat.put(aunt.name, "Maternal Aunt");
			}
		}
		if(null != this.patAunts && this.patAunts.size()>0){
			for(Person aunt : this.patAunts){
				relMat.put(aunt.name, "Paternal Aunt");
			}
		}
		if(null != this.matUncles && this.matUncles.size()>0){
			for(Person uncle : this.matUncles){
				relMat.put(uncle.name, "Maternal Uncle");
			}
		}
		if(null != this.patUncles && this.patUncles.size()>0){
			for(Person uncle : this.patUncles){
				relMat.put(uncle.name, "Paternal Uncle");
			}
		}
		if(null != this.inLaws && this.inLaws.size()>0){
			for(Person inlaw : this.inLaws){
				if(inlaw.gender=='M'){
					relMat.put(inlaw.name, "Brother-in-law");
				}else{
					relMat.put(inlaw.name, "Sister-in-law");
				}
			}
		}
		if(null != this.cousins && this.cousins.size()>0){
			for(Person sib : this.cousins){
				if(sib.gender=='M'){
					relMat.put(sib.name, "Cousin Brother");
				}else{
					relMat.put(sib.name, "Cousin Sister");
				}
			}
		}
		return relMat;
	}

	public void addChild(String name, char gender) {
		Person child = new Person(name, gender);
		this.spouse.children.add(child);
		this.children.add(child);
		if(this.gender=='M'){
			child.father = this;
			if (null != this.spouse) {
				child.mother = this.spouse;
			}
		}else{
			child.mother = this;
			if (null != this.spouse) {
				child.father = this.spouse;
			}
		}
		updateRelationships(child);
	}

	public void setChild(Person child) {
		this.children.add(child);
		this.spouse.children.add(child);
		if(this.gender=='M'){
			child.father = this;
			if (null != this.spouse) {
				child.mother = this.spouse;
			}
		}else{
			child.mother = this;
			if (null != this.spouse) {
				child.father = this.spouse;
			}
		}
		updateRelationships(child);
	}
	
	//POJOs
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

	public Person getGreatGrandFather() {
		return greatGrandFather;
	}

	public void setGreatGrandFather(Person greatGrandFather) {
		this.greatGrandFather = greatGrandFather;
	}

	public Person getGreatGrandMother() {
		return greatGrandMother;
	}

	public void setGreatGrandMother(Person greatGrandMother) {
		this.greatGrandMother = greatGrandMother;
	}

	public ArrayList<Person> getGreatGrandChildren() {
		return greatGrandChildren;
	}

	public void setGreatGrandChildren(ArrayList<Person> greatGrandChildren) {
		this.greatGrandChildren = greatGrandChildren;
	}

	public Person getSpouse() {
		return spouse;
	}
}
