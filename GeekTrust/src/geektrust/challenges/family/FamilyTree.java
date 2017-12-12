package geektrust.challenges.family;

import java.util.HashMap;

public class FamilyTree {
	HashMap<String, Person> persons = new HashMap<String, Person>();
	
	FamilyTree() {
		constructInitialTree();
	}
	private void constructInitialTree() {
		Person shan = new Person("Shan",'M');
		persons.put(shan.getName(), shan);
		Person anga = new Person("Anga",'F');
		persons.put(anga.getName(), anga);
		shan.setSpouse(anga);
		Person ish = new Person("Ish",'M');
		persons.put(ish.getName(), ish);
		shan.setChild(ish);
		Person chit = new Person("Chit",'M');
		persons.put(chit.getName(), chit);
		shan.setChild(chit);
		Person ambi = new Person("Ambi",'F');
		persons.put(ambi.getName(), ambi);
		chit.setSpouse(ambi);
		Person vich = new Person("Vich",'M');
		persons.put(vich.getName(), vich);
		shan.setChild(vich);
		Person lika = new Person("Lika",'F');
		persons.put(lika.getName(), lika);
		vich.setSpouse(lika);
		Person satya = new Person("Satya",'F');
		persons.put(satya.getName(), satya);
		shan.setChild(satya);
		Person vyan = new Person("Vyan",'M');
		persons.put(vyan.getName(), vyan);
		satya.setSpouse(vyan);
		Person drita = new Person("Drita", 'M');
		persons.put(drita.getName(), drita);
		chit.setChild(drita);
		Person jaya = new Person("Jaya", 'F');
		persons.put(jaya.getName(), jaya);
		drita.setSpouse(jaya);
		Person vrita = new Person("Vrita", 'M');
		persons.put(vrita.getName(), vrita);
		chit.setChild(vrita);
		Person jata = new Person("Jata", 'M');
		persons.put(jata.getName(), jata);
		drita.setChild(jata);
		Person driya = new Person("Driya", 'F');
		persons.put(driya.getName(), driya);
		drita.setChild(driya);
		Person mnu = new Person("Mnu", 'M');
		persons.put(mnu.getName(), mnu);
		driya.setSpouse(mnu);
		Person vila = new Person("Vila", 'M');
		persons.put(vila.getName(), vila);
		vich.setChild(vila);
		Person jnki = new Person("Jnki", 'F');
		persons.put(jnki.getName(), jnki);
		vila.setSpouse(jnki);
		Person chika = new Person("Chika", 'F');
		persons.put(chika.getName(), chika);
		vich.setChild(chika);
		Person kpila = new Person("Kpila", 'M');
		persons.put(kpila.getName(), kpila);
		chika.setSpouse(kpila);
		Person lavnya = new Person("Lavnya", 'F');
		persons.put(lavnya.getName(), lavnya);
		vila.setChild(lavnya);
		Person gru = new Person("Gru", 'M');
		persons.put(gru.getName(), gru);
		lavnya.setSpouse(gru);
		Person satvy = new Person("Satvy", 'F');
		persons.put(satvy.getName(), satvy);
		satya.setChild(satvy);
		Person asva = new Person("Asva", 'M');
		persons.put(asva.getName(), asva);
		satvy.setSpouse(asva);
		Person savya = new Person("Savya", 'M');
		persons.put(savya.getName(), savya);
		satya.setChild(savya);
		Person krpi = new Person("Krpi", 'F');
		persons.put(krpi.getName(), krpi);
		savya.setSpouse(krpi);
		Person kriya = new Person("Kriya", 'M');
		persons.put(kriya.getName(), kriya);
		savya.setChild(kriya);
		Person saayan = new Person("Saayan", 'M');
		persons.put(saayan.getName(), saayan);
		satya.setChild(saayan);
		Person mina = new Person("Mina", 'F');
		persons.put(mina.getName(), mina);
		saayan.setSpouse(mina);
		Person misa = new Person("Misa", 'M');
		persons.put(misa.getName(), misa);
		saayan.setChild(misa);
		}
}
