import javax.swing.JOptionPane;
import java.time.Year;

public class App {
    public static void main(String[] args) throws Exception {
        String personnummerString = JOptionPane.showInputDialog(null, "Skriv ditt personnummer i ett av följande format:\nÅÅÅÅMMDDXXXX, ÅÅÅÅMMDD-XXXX,\n eller ÅÅMMDDXXXX (Programmet antar att du är född under 2000-talet.)", "Personnummer", 3);
        
        personnummerString = personnummerString.trim();

        String helapersonnumret = personnummerString;

        personnummerString = personnummerString.replace("-", "");
        personnummerString = personnummerString.replace("+", "");

        boolean korrekt = true;

        Year currentYear = Year.now();
        int fullyear = currentYear.getValue();
        String fullyearString = fullyear + "";
        int year = Integer.parseInt(fullyearString.substring(2, 4));

        String personnummer = "";
        if (personnummerString.length() == 10) {
            personnummer = personnummerString;
        } else if (personnummerString.length() == 12) {
            personnummer = personnummerString.substring(2);
        } else {
            JOptionPane.showMessageDialog(null, "Felaktigt personnummer.", "Fel", 0);
            korrekt = false;
        }

        String sekel = "20";

        if (helapersonnumret.length() == 11) {
            if (helapersonnumret.charAt(6) == '+' && Integer.parseInt(personnummer.substring(0, 2)) < year) {
                sekel = "19";
            } else if (helapersonnumret.charAt(6) == '+' && Integer.parseInt(personnummer.substring(0, 2)) > year) {
                sekel = "18";
            } else if (helapersonnumret.charAt(6) == '-' && Integer.parseInt(personnummer.substring(0, 2)) > year) {
                sekel = "19";
            } else if (helapersonnumret.charAt(6) == '-' && Integer.parseInt(personnummer.substring(0, 2)) <= year) {
                sekel = "20";
            } else {
                JOptionPane.showMessageDialog(null, "Felaktigt personnummer.", "Fel", 0);
                korrekt = false;
            }
        } else if (helapersonnumret.length() == 13) {
            if (helapersonnumret.charAt(8) == '+' && Integer.parseInt(personnummer.substring(2, 4)) < year) {
                sekel = "19";
            } else if (helapersonnumret.charAt(8) == '+' && Integer.parseInt(personnummer.substring(2, 4)) > year) {
                sekel = "18";
            } else if (helapersonnumret.charAt(8) == '-' && Integer.parseInt(personnummer.substring(2, 4)) > year) {
                sekel = "19";
            } else if (helapersonnumret.charAt(8) == '-' && Integer.parseInt(personnummer.substring(2, 4)) <= year) {
                sekel = "20";
            } else {
                JOptionPane.showMessageDialog(null, "Felaktigt personnummer.", "Fel", 0);
                korrekt = false;
            }
        }

        int år = Integer.parseInt(personnummerString.substring(0, 5));

        switch(Integer.parseInt(personnummer.substring(2, 4))) {
            case 4: case 6: case 9: case 11:
                if (Integer.parseInt(personnummer.substring(4, 6)) > 30) {
                    JOptionPane.showMessageDialog(null, "Felaktigt personnummer.", "Fel", 0);
                    korrekt = false;
                }
                break;
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                if (Integer.parseInt(personnummer.substring(4, 6)) > 31) {
                    JOptionPane.showMessageDialog(null, "Felaktigt personnummer.", "Fel", 0);
                    korrekt = false;
                }
                break;
            case 2:
                switch (personnummerString.length()) {
                    case 12:
                        if (Integer.parseInt(personnummer.substring(4, 6)) == 29 && (år % 4 == 0 && år % 100 != 0) || (år % 400 == 0)) {
                            break;
                        } else if (Integer.parseInt(personnummer.substring(4, 6)) <= 28) {
                            break;
                        }   else {
                            JOptionPane.showMessageDialog(null, "Felaktigt personnummer.", "Fel", 0);
                            korrekt = false;
                        }
                    case 10:
                        String årString = sekel + personnummerString.substring(0, 2);
                        år = Integer.parseInt(årString);
                        if (Integer.parseInt(personnummer.substring(4, 6)) == 29 && (år % 4 == 0 && år % 100 != 0) || (år % 400 == 0)) {
                            break;
                        } else if (Integer.parseInt(personnummer.substring(4, 6)) <= 28) {
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Felaktigt personnummer.", "Fel", 0);
                            korrekt = false;
                        }
                }
        }

        // Kod tagen från https://stackoverflow.com/questions/26383926/how-do-i-implement-the-luhn-algorithm, men med egna förklaringar för att förstå for loopar, och vissa ändringar.
        int sum = 0;
        for (int i = 0; i < personnummer.length(); i++){ // Så länge i och personnummret är lika långa fortsätter loopen. Det läggs på ett nummer på i varje gång loopen körs.
            char tmp = personnummer.charAt(i); // tmp blir den i:e siffran i personnumret.
            int num = tmp - '0'; // Omvandlar tmp till en int som heter num.
            int product;
            if (i % 2 != 0) { // Om i är ett udda tal multipliceras num med ett.
                product = num * 1;
            } else {  // Om i är ett jämt tal multipliceras num med två.
            product = num * 2;
            }
            if (product > 9) // Verkar behandla tal över 10 på ett sätt jag tyvärr inte förstår.
                product -= 9;
                sum += product;
        }

        boolean valid = (sum % 10 == 0);
        if (!valid){
            JOptionPane.showMessageDialog(null, "Felaktigt personnummer.", "Fel", 0);
            korrekt = false;
        }
        
        String kön = "";

        if (Integer.parseInt(personnummer.substring(8, 9)) % 2 != 0) {
            kön = "man";
        } else {
            kön = "kvinna";
        }

        System.out.println(personnummer.substring(8, 9));

        if (korrekt) {
            JOptionPane.showMessageDialog(null, "Du skrev in personnumret " + helapersonnumret + ", som är giltigt och tillhör en " + kön + ".");
        }
    }
}