package geektrust.challenges.war;

import java.util.Scanner;

public class TheTwoPlanets {

	/**
	 * @author omore
	 * @param args
	 */
	
	static int availableHorses = 100;
	static int availableElephants = 50;
	static int availabletanks = 10;
	static int availableGuns = 5;
	static int horsesToDeploy, elephanstsToDeploy, tanksToDeploy, gunsToDeploy;
	static int horsesToBeCountered, elephantsToBeCountered, tanksToBeCountered, gunsToBeCountered;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String in = sc.nextLine().trim();
		in=in.replace("Falicornia attacks with ","");
		String[] cut = in.split(", ");
		for(int i=0;i<4;i++){
			String[] part = cut[i].split(" ");
			if(i==0){
				horsesToBeCountered=Integer.parseInt(part[0]);
			}
			if(i==1){
				elephantsToBeCountered=Integer.parseInt(part[0]);
			}
			if(i==2){
				tanksToBeCountered=Integer.parseInt(part[0]);
			}
			if(i==3){
				gunsToBeCountered=Integer.parseInt(part[0]);
			}
		}
		String result;

		if (horsesToBeCountered > 300 || elephantsToBeCountered > 200 || tanksToBeCountered > 40 || gunsToBeCountered > 20) {
			System.out.println("Invalid Output !");
			return;
		}

		//Like2Like matching first
		calculateHorses();
		calculateElephants();
		calculateTanks();
		calculateGuns();
		
		//Substitution if needed as per Rule #3 and #4
		substituteHorses();
		substituteElephants();
		substituteTanks();
		substituteGuns();

		if (horsesToBeCountered > 0 || elephantsToBeCountered > 0 || tanksToBeCountered > 0 || gunsToBeCountered > 0) {
			result = "loses";
		} else {
			result = "wins";
		}

		System.out.println("Lengaburu deploys " + horsesToDeploy + "H, "
				+ elephanstsToDeploy + "E, " + tanksToDeploy + "AT, "
				+ gunsToDeploy + "SG and " + result);
	}
	private static void substituteGuns() {
		if (gunsToBeCountered > 0) {
			if (gunsToBeCountered < availabletanks) {
				tanksToDeploy += gunsToBeCountered;
				availabletanks -= gunsToBeCountered;
				gunsToBeCountered = 0;
			} else {
				tanksToDeploy += availabletanks;
				gunsToBeCountered -= availabletanks;
				availabletanks = 0;
			}
		}
	}
	private static void substituteTanks() {
		if (tanksToBeCountered > 0) {
			if (tanksToBeCountered < availableElephants) {
				availableElephants -= tanksToBeCountered * 2;
				elephanstsToDeploy += tanksToBeCountered * 2;
				tanksToBeCountered = 0;
			} else {
				tanksToBeCountered -= availableElephants;
				elephanstsToDeploy += availableElephants;
				availableElephants = 0;
				if (tanksToBeCountered > 0) {
					if (tanksToBeCountered / 4.0 < availableGuns) {
						gunsToDeploy += Math.ceil(tanksToBeCountered / 4.0);
						availableGuns -= Math.ceil(tanksToBeCountered / 4.0);
						tanksToBeCountered = 0;
					} else {
						gunsToDeploy += availableGuns;
						tanksToBeCountered -= availableGuns;
						availableGuns = 0;
					}
				}
			}
		}
	}
	private static void substituteElephants() {
		if (elephantsToBeCountered > 0) {
			if (elephantsToBeCountered * 2 < availableHorses) {
				availableHorses -= elephantsToBeCountered * 2;
				horsesToDeploy += elephantsToBeCountered * 2;
				elephantsToBeCountered = 0;
			} else {
				elephantsToBeCountered -= availableHorses;
				horsesToDeploy += availableHorses;
				availableHorses = 0;
				if (elephantsToBeCountered > 0) {
					if (elephantsToBeCountered / 4.0 < availabletanks) {
						tanksToDeploy += Math.ceil(elephantsToBeCountered / 4.0);
						availabletanks -= Math.ceil(elephantsToBeCountered / 4.0);
						elephantsToBeCountered = 0;
					} else {
						tanksToDeploy += availabletanks;
						elephantsToBeCountered -= availabletanks;
						availabletanks = 0;
					}
				}
			}
		}
	}
	private static void substituteHorses() {
		if (horsesToBeCountered > 0) {
			if (horsesToBeCountered / 4.0 < availableElephants) {
				elephanstsToDeploy += Math.ceil(horsesToBeCountered / 4.0);
				availableElephants -= Math.ceil(horsesToBeCountered / 4.0);
				horsesToBeCountered = 0;
			} else {
				elephanstsToDeploy += availableElephants;
				horsesToBeCountered -= availableElephants * 4;
				availableElephants = 0;
			}
		}
	}
	private static void calculateGuns() {
		if (gunsToBeCountered / 2.0 < availableGuns) {
			gunsToDeploy = (int) Math.ceil(gunsToBeCountered / 2.0);
			gunsToBeCountered = 0;
		} else {
			gunsToDeploy = availableGuns;
			availableGuns = 0;
			gunsToBeCountered -= gunsToDeploy * 2;
		}
	}
	private static void calculateTanks() {
		if (tanksToBeCountered / 2.0 < availabletanks) {
			tanksToDeploy = (int) Math.ceil(tanksToBeCountered / 2.0);
		} else {
			tanksToDeploy = availabletanks;
			availabletanks = 0;
			tanksToBeCountered -= tanksToDeploy * 2;
		}
	}
	private static void calculateElephants() {
		if (elephantsToBeCountered / 2.0 < availableElephants) {
			elephanstsToDeploy = (int) Math.ceil(elephantsToBeCountered / 2.0);
			availableElephants -= Math.ceil(elephantsToBeCountered / 2.0);
			elephantsToBeCountered = 0;
		} else {
			elephanstsToDeploy = availableElephants;
			availableElephants = 0;
			elephantsToBeCountered -= elephanstsToDeploy * 2;
		}
	}
	private static void calculateHorses() {
		if (horsesToBeCountered / 2.0 < availableHorses) {
			horsesToDeploy = (int) Math.ceil(horsesToBeCountered / 2.0);
			horsesToBeCountered -= horsesToDeploy * 2;
			availableHorses -= horsesToDeploy;
		} else {
			horsesToDeploy = availableHorses;
			availableHorses = 0;
			horsesToBeCountered -= horsesToDeploy * 2;
		}
	}

}
