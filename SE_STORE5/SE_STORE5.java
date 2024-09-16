package SE_STORE5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;

public class SE_STORE5 {
    public static void main(String[] args) throws IOException {
        File Product = new File("C:\\Users\\ghost\\IdeaProjects\\untitled\\src\\SE_STORE5\\PRODUCT (4).txt");;
        Scanner countNum = new Scanner(System.in);
        int typeNumber;
        ArrayList<String> product = new ArrayList<String>();
        boolean loopOrNot = true;
        ArrayList<Double> cost = new ArrayList<Double>();
        ArrayList<Integer> quantity = new ArrayList<Integer>();
        Scanner reader = new Scanner(Product);
        ArrayList<String>id = new ArrayList<String>();
        int countProduct=0;
        ArrayList<String> idCategory = new ArrayList<String>();
        File category = new File("C:\\Users\\ghost\\IdeaProjects\\untitled\\src\\SE_STORE5\\CATEGORY.txt");
        ArrayList<String>nameCategory = new ArrayList<String>();
        String typeCategory;
        Scanner inputCategory = new Scanner(category);
        boolean quit = false;
        ArrayList<String> hostIdCategory = new ArrayList<String>();
        boolean loopCategory = true;
        Scanner inputTypeCategory = new Scanner(System.in);
        boolean loopDisplay1 = true;
        File member = new File("C:\\Users\\ghost\\IdeaProjects\\untitled\\src\\SE_STORE5\\MEMBER (5).txt");
        Scanner inputMember = new Scanner(member);
        ArrayList<String> idUser = new ArrayList<String>();
        ArrayList<String> fisrtnameUser = new ArrayList<String>();
        ArrayList<String> lastnameUser = new ArrayList<String>();
        ArrayList<String> emailUser = new ArrayList<String>();
        ArrayList<String> passwordUser = new ArrayList<String>();
        ArrayList<String> phoneUser = new ArrayList<String>();
        ArrayList<String> pointUser = new ArrayList<String>();
        boolean loopLogin = true;
        Scanner inputEmail = new Scanner(System.in);
        boolean checkUser = false;
        Scanner inputPassword = new Scanner(System.in);
        int correctIndex = 0;
        String role = "";
        boolean loopAddMember = true;
        while(inputMember.hasNextLine()){
            String[] line = inputMember.nextLine().split("\\s+");
            idUser.add(line[0]);
            fisrtnameUser.add(line[1]);
            lastnameUser.add(line[2]);
            emailUser.add(line[3]);
            passwordUser.add(line[4]);
            phoneUser.add(line[5]);
            pointUser.add(line[6]);
        }
        while(reader.hasNextLine()){
            String[] line = reader.nextLine().split("\\s+");
            id.add(line[0]);
            product.add(line[1]);
            cost.add(Double.parseDouble(line[2].replace("$", "")));
            quantity.add(Integer.parseInt(line[3]));
            idCategory.add(line[4]);
        }
        while(inputCategory.hasNextLine()){
            hostIdCategory.add(inputCategory.next());
            nameCategory.add(inputCategory.nextLine());
        }
        while(loopDisplay1 == true) {
            System.out.print("\t===== SE STORE =====\t\t\t\t\t\n" +
                    "\t1. Login\t\t\t\t\n" +
                    "\t2. Exit\t\t\t\n" +
                    "\t====================\t\t\t\t\t\n" +
                    "\tSelect (1-2) : ");
            typeNumber = countNum.nextInt();
            int countErrorUser = 0;
            loopLogin = true;
            if(typeNumber == 1) {
                while(loopLogin == true) {
                    System.out.print("\t===== LOGIN =====\n" +
                            "\tEmail : "); //johndoe@outlook.com
                    String Email = inputEmail.nextLine();
                    System.out.print("\tPassword : "); //665331
                    String password = inputPassword.nextLine();
                    boolean checkEmailCorrect = false;
                    int emailIndex = -1;
                    StringBuilder passWord = new StringBuilder();
                    for (int i = 0; i < emailUser.size(); i++) {
                        if (Email.equalsIgnoreCase(emailUser.get(i))) {
                            passWord.append(passwordUser.get(i).charAt(9));
                            passWord.append(passwordUser.get(i).charAt(10));
                            passWord.append(passwordUser.get(i).charAt(13));
                            passWord.append(passwordUser.get(i).charAt(14));
                            passWord.append(passwordUser.get(i).charAt(15));
                            passWord.append(passwordUser.get(i).charAt(16));
                            checkEmailCorrect = true;
                            emailIndex = i;
                        }
                    }
                    if(checkEmailCorrect) {
                        if (checkEmailCorrect == true && emailIndex != -1) {
                            int loopCheck = 1;
                            if (password.equals(passWord.toString())) {
                                checkUser = true;
                                correctIndex = emailIndex;
                                emailIndex = emailUser.size();
                            } else {
                                checkUser = false;
                                countErrorUser++;
                            }
                        } else {
                            checkUser = false;
                            countErrorUser++;
                        }
                    }else{
                        countErrorUser++;
                        checkEmailCorrect = false;
                    }
                    if(!checkUser || !checkEmailCorrect) {
                        System.out.println("\t====================\n" +
                                "\tError! - Email or Password is Incorrect ("+countErrorUser+")");
                        if(countErrorUser==3){
                            loopLogin = false;
                        }
                    }

                    //check role
                    if(passwordUser.get(correctIndex).charAt(6) == '0'){
                        role = "Staff";
                    }else if(passwordUser.get(correctIndex).charAt(6) == '1'){
                        role = "Regular";
                    }else if(passwordUser.get(correctIndex).charAt(6) == '2'){
                        role = "Silver";
                    }else if(passwordUser.get(correctIndex).charAt(6) == '3'){
                        role = "Gold";
                    }
                    if(checkUser == true){
                        if(passwordUser.get(correctIndex).charAt(2) == '1'){
                            String emailDisplay = emailUser.get(correctIndex).replaceAll("^([\\w]{2})(.*)(@[\\w]{2})(.*)$", "$1***$3***");
                            double point = Double.parseDouble(pointUser.get(correctIndex));
                            int pointDisplay = (int)point;
                            StringBuilder phoneDisplay = new StringBuilder(phoneUser.get(correctIndex));
                            phoneDisplay.insert(3,'-');
                            phoneDisplay.insert(7,'-');
                            System.out.println("\t===== SE STORE =====\n" +
                                    "\tHello, "+lastnameUser.get(correctIndex).charAt(0)+". "+ fisrtnameUser.get(correctIndex)+"\t("+ role +")\t\t\n" +
                                    "\tEmail: "+ emailDisplay+ "\t\t\t\n" +
                                    "\tPhone: "+phoneDisplay+"\t\t\t\n" +
                                    "\tYou have "+pointDisplay+" Point\t\t\t\n" +
                                    "\t====================");
                            loopOrNot = true;
                            if(!role.equalsIgnoreCase("Staff")) {
                                while (loopOrNot == true) {
                                    System.out.printf(
                                            "\t===== SE STORE =====\t\t\t\n" +

                                                    "\t1. Show Category\t\t\t\n" +
                                                    "\t2. Exit\t\t\t\n" +
                                                    "\t====================\t\t\t\n" +
                                                    "\tSelect (1-2) :\t");
                                    typeNumber = countNum.nextInt();
                                    if (typeNumber == 1) {
                                        while (loopCategory) {
                                            System.out.printf("\t=========== SE STORE's Product Categories ===========\t\t\t\n" +
                                                    "\t#\t    Category\n");
                                            for (countProduct = 0; countProduct < nameCategory.size(); countProduct++) {
                                                System.out.printf("\t%-3d %-15s\n",
                                                        countProduct + 1,
                                                        nameCategory.get(countProduct));
                                            }
                                            System.out.printf("\t=========================================\t\t\t\n" +
                                                    "\tSelect Category to Show Product (1-" + (nameCategory.size()) + ") or Q for exit\n" +
                                                    "\tSelect : ");
                                            typeCategory = inputTypeCategory.nextLine();
                                            int choosedTypeCategory = -1;
                                            if (typeCategory.equalsIgnoreCase("Q")) {
                                                loopCategory = false;
                                            } else {
                                                try {
                                                    choosedTypeCategory = Integer.parseInt(typeCategory);
                                                    if (choosedTypeCategory >= 1 && choosedTypeCategory <= nameCategory.size()) {
                                                        System.out.println("\t============ " + nameCategory.get(choosedTypeCategory - 1) + " ============\t\t\t\n" +
                                                                "\t#\tName\t    \tPrice(฿)  Quantity");
                                                        countProduct = 1;
                                                        for (int i = 0; i < idCategory.size(); i++) {
                                                            if (idCategory.get(i).equals(hostIdCategory.get(choosedTypeCategory - 1))) {
                                                                System.out.printf("\t%-3d %-15s %-9.2f %-8d\n",
                                                                        countProduct,
                                                                        product.get(i),
                                                                        cost.get(i) * 34,
                                                                        quantity.get(i));
                                                                countProduct++;
                                                            }
                                                        }
                                                        System.out.print("\t================================\t\t\t\n" +
                                                                "\tPress Q to Exit\t");
                                                        typeCategory = inputTypeCategory.nextLine();
                                                        while (!typeCategory.equalsIgnoreCase("q")) {
                                                            System.out.print("\tPress Q to Exit\t");
                                                            typeCategory = inputTypeCategory.nextLine();
                                                        }
                                                    } else {
                                                        System.out.println("Invalid number, please enter a value between 1-" + nameCategory.size());
                                                    }
                                                } catch (NumberFormatException e) {
                                                    System.out.println("Invalid input, please enter a valid number 1-" + nameCategory.size() + " or Q/q.");
                                                }
                                            }
                                        }
                                        System.out.printf("\t===========================================\t\n");
                                    } else if (typeNumber == 2) {
                                        loopLogin = false;
                                        loopOrNot = false;
                                    }
                                }
                            }else{
                                while (loopOrNot == true) {
                                    System.out.printf(
                                            "\t===== SE STORE =====\t\t\t\n" +

                                                    "\t1. Show Category\t\t\t\n" +
                                                    "\t2. Add Members\n"+
                                                    "\t3. Exit\t\t\t\n" +
                                                    "\t====================\t\t\t\n" +
                                                    "\tSelect (1-3) :\t");
                                    typeNumber = countNum.nextInt();
                                    if (typeNumber == 1) {
                                        while (loopCategory) {
                                            System.out.printf("\t=========== SE STORE's Product Categories ===========\t\t\t\n" +
                                                    "\t#\t    Category\n");
                                            for (countProduct = 0; countProduct < nameCategory.size(); countProduct++) {
                                                System.out.printf("\t%-3d %-15s\n",
                                                        countProduct + 1,
                                                        nameCategory.get(countProduct));
                                            }
                                            System.out.printf("\t=========================================\t\t\t\n" +
                                                    "\tSelect Category to Show Product (1-" + (nameCategory.size()) + ") or Q for exit\n" +
                                                    "\tSelect : ");
                                            typeCategory = inputTypeCategory.nextLine();
                                            int choosedTypeCategory = -1;
                                            if (typeCategory.equalsIgnoreCase("Q")) {
                                                loopCategory = false;
                                            } else {
                                                try {
                                                    choosedTypeCategory = Integer.parseInt(typeCategory);
                                                    if (choosedTypeCategory >= 1 && choosedTypeCategory <= nameCategory.size()) {
                                                        System.out.println("\t============ " + nameCategory.get(choosedTypeCategory - 1) + " ============\t\t\t\n" +
                                                                "\t#\tName\t    \tPrice(฿)  Quantity");
                                                        countProduct = 1;
                                                        for (int i = 0; i < idCategory.size(); i++) {
                                                            if (idCategory.get(i).equals(hostIdCategory.get(choosedTypeCategory - 1))) {
                                                                System.out.printf("\t%-3d %-15s %-9.2f %-8d\n",
                                                                        countProduct,
                                                                        product.get(i),
                                                                        cost.get(i) * 34,
                                                                        quantity.get(i));
                                                                countProduct++;
                                                            }
                                                        }
                                                        System.out.print("\t================================\t\t\t\n" +
                                                                "\tPress Q to Exit\t");
                                                        typeCategory = inputTypeCategory.nextLine();
                                                        while (!typeCategory.equalsIgnoreCase("q")) {
                                                            System.out.print("\tPress Q to Exit\t");
                                                            typeCategory = inputTypeCategory.nextLine();
                                                        }
                                                    } else {
                                                        System.out.println("Invalid number, please enter a value between 1-" + nameCategory.size());
                                                    }
                                                } catch (NumberFormatException e) {
                                                    System.out.println("Invalid input, please enter a valid number 1-" + nameCategory.size() + " or Q/q.");
                                                }
                                            }
                                        }
                                        System.out.printf("\t===========================================\t\n");
                                    }else if (typeNumber == 2){
                                        loopAddMember = true;
                                        Scanner inputAddMember = new Scanner(System.in);
                                        String firstnameAddMember, lastnameAddMember, emailAddMember, phoneAddMember;
                                        while(loopAddMember){
                                            boolean checkAddmember = true;
                                            System.out.print("\t===== Add Member =====\n" +
                                                    "\tFirstname : ");
                                            firstnameAddMember = inputAddMember.nextLine();
                                            System.out.print("\tLastname : ");
                                            lastnameAddMember = inputAddMember.nextLine();
                                            System.out.print("\tEmail : ");
                                            emailAddMember = inputAddMember.nextLine();
                                            System.out.print("\tPhone : ");
                                            phoneAddMember = inputAddMember.nextLine();

                                            //checkAddMember
                                            if(firstnameAddMember.length() <= 2){
                                                checkAddmember = false;
                                            }else if(lastnameAddMember.length() <= 2){
                                                checkAddmember = false;
                                            }else if(emailAddMember.length() <= 2 || !emailAddMember.contains("@")){
                                                checkAddmember = false;
                                            }else if(phoneAddMember.length() != 10){
                                                checkAddmember = false;
                                            }

                                            if(checkAddmember == true){
                                                //generate password
                                                FileWriter writer = new FileWriter("C:\\Users\\ghost\\IdeaProjects\\untitled\\src\\SE_STORE5\\MEMBER (5).txt", true);
                                                Random random = new Random();
                                                String randomPassword = Integer.toString(random.nextInt(999999));
                                                String randomLetter = "";
                                                for(int i = 0 ; i < 19; i++){
                                                    randomLetter += (char) (random.nextInt(26) + 'A');
                                                }
                                                StringBuilder newPassword = new StringBuilder(randomLetter);
                                                newPassword.setCharAt(2,'1');
                                                newPassword.setCharAt(6,'1');
                                                int[] numberPosition = {9,10,13,14,15,16};
                                                for(int i = 0; i < randomPassword.length() ; i++){
                                                    newPassword.setCharAt(numberPosition[i],randomPassword.charAt(i));
                                                }
                                                System.out.println("\tSuccess - New Member has been created!");
                                                System.out.println("\t" + firstnameAddMember + " " + lastnameAddMember.charAt(0) + " Password is " + randomPassword);
                                                loopAddMember = false;

                                                // แปลง newIdUser เป็น String
                                                int indexIdUserLastest = idUser.size();
                                                int newIdUser = Integer.parseInt(idUser.get(indexIdUserLastest-1))+1;
                                                String keepMember = newIdUser + "\t" + firstnameAddMember + "\t" + lastnameAddMember + "\t" +
                                                        emailAddMember + "\t" + newPassword + "\t" + phoneAddMember + "\t" + 0.00 + "\n";
                                                writer.write(keepMember);
                                               // ปิด FileWriter
                                                writer.close();

                                                //clear ข้อมูลใน arraylist เพื่อเก็บค่าใน อาเรย์ใหม่
                                                idUser.clear();
                                                fisrtnameUser.clear();
                                                lastnameUser.clear();
                                                emailUser.clear();
                                                passwordUser.clear();
                                                phoneUser.clear();
                                                pointUser.clear();

                                                while(inputMember.hasNextLine()){
                                                    String[] line = inputMember.nextLine().split("\\s+");
                                                    idUser.add(line[0]);
                                                    fisrtnameUser.add(line[1]);
                                                    lastnameUser.add(line[2]);
                                                    emailUser.add(line[3]);
                                                    passwordUser.add(line[4]);
                                                    phoneUser.add(line[5]);
                                                    pointUser.add(line[6]);
                                                }

                                            }else if(checkAddmember == false){
                                                System.out.println("\tError! - Your Information are Incorrect!");
                                                loopAddMember = false;
                                            }
                                        }
                                    }else if (typeNumber == 3) {
                                        loopLogin = false;
                                        loopOrNot = false;
                                    }
                                }
                            }
                        }else{
                            System.out.println("\t====================\n" +
                                    "\tError! - Your Account are Expired! ");
                        }
                    }
                }
            }else if(typeNumber == 2){
                System.out.printf("\t===== SE STORE =====\t\t\t\n" +
                        "Thank you for using our service :3\t");
                loopDisplay1 = false;
                System.exit(0);
            }
        }
    }
}
//	        Price	   Quantity
//	%-3d %-15s $%-9.2f %-10d