import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) throws Exception {
        String personnummerString = JOptionPane.showInputDialog(null, "Skriv ditt personnummer i ett av följande format:\nÅÅÅÅMMDDXXXX, ÅÅÅÅMMDD-XXXX,\n eller ÅÅMMDDXXXX (Programmet antar att du är född under 2000-talet.)", "Personnummer", 3);
        
        personnummerString = personnummerString.trim();
        personnummerString = personnummerString.replace("-", "");

        String personnummer = "";
        if (personnummerString.length() == 10) {
            personnummer = personnummerString;
        } else if (personnummerString.length() == 12) {
            personnummer = personnummerString.substring(2);
        } else {
            JOptionPane.showMessageDialog(null, "Felaktigt personnummer.", "Fel", 0);
        }

        System.out.println(personnummer);
        System.out.println(personnummer.substring(2, 4));
        System.out.println(personnummer.substring(4, 6));

        int år = Integer.parseInt(personnummerString.substring(0, 5));
        System.out.println(personnummerString.substring(0, 4));

        switch(personnummer.substring(2, 4)) {
            case "04": case "06": case "09": case "11":
                if (Integer.parseInt(personnummer.substring(4, 6)) > 30) {
                    JOptionPane.showMessageDialog(null, "Felaktigt personnummer.", "Fel", 0);
                } else {
                    return;
                }
            case "01": case "03": case "05": case "07": case "08": case "10": case "12":
                if (Integer.parseInt(personnummer.substring(4, 6)) > 31) {
                    return;
                }
            case "02":
                switch (personnummerString.length()) {
                    case 12:
                        if (Integer.parseInt(personnummer.substring(4, 6)) == 29 && (år % 4 == 0 && år % 100 != 0) || (år % 400 == 0)) {
                            return;
                        } else if (Integer.parseInt(personnummer.substring(4, 6)) <= 28) {
                            return;
                        }   else {
                            JOptionPane.showMessageDialog(null, "Felaktigt personnummer.", "Fel", 0);
                        }
                    case 10:
                        String årString = "20" + personnummerString.substring(0, 2);
                        System.out.println(årString);
                        år = Integer.parseInt(årString);
                        if (Integer.parseInt(personnummer.substring(4, 6)) == 29 && (år % 4 == 0 && år % 100 != 0) || (år % 400 == 0)) {
                            return;
                        } else if (Integer.parseInt(personnummer.substring(4, 6)) <= 28) {
                            return;
                        } else {
                            JOptionPane.showMessageDialog(null, "Felaktigt personnummer.", "Fel", 0);
                        }
                }
            default:
                JOptionPane.showMessageDialog(null, "Felaktigt personnummer.", "Fel", 0);

            // ChatGPT uträkning kontrollsiffra:
            int sum = 0;
            int[] vikt = {2, 1, 2, 1, 2, 1, 2, 1, 2, 1};

            for (int i = 0; i < 10; i++) {
                int num = Integer.parseInt(personnummer.substring(i, i + 1)) * vikt[i];
                if (num > 9) {
                    num = num % 10 + num / 10;
                }
                sum += num;
            }

            int kontrollsiffra = 10 - (sum % 10);
            if (kontrollsiffra == 10) {
                kontrollsiffra = 0;
            }
        }
    }
}
