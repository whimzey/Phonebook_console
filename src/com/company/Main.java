package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class Main {

    static BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
    static BufferedReader reader;

    public static void main(String[] args) throws IOException {

        while (true) {

            System.out.println("Choose:\n 1 - Create; \n 2 - Remove; \n 3 - Show all; \n" +
                    " 4 - Search by phone; \n 5 - Exit.");

            int a = Integer.parseInt(scan.readLine());
            if (a == 1) {
                add();
            } else if (a == 2) {
                remove();
            } else if (a == 3) {
                showAll();

            } else if (a == 4) {
                search();
            } else if (a == 5) {
                System.exit(0);
            }
        }
    }

    private static void search() throws IOException {
        System.out.println("Set phone");
        String phone = scan.readLine();
        reader = new BufferedReader(new InputStreamReader(new FileInputStream("contacts.txt.txt")));
        String line;
        int x = 0;
        while ((line = reader.readLine()) != null) {
            if (line.contains(phone)) {
                line = line.replace("/", " ");
                System.out.println(line);
                x++;
                break;
            }
        }
        if (x == 0) {
            System.out.println("No such phone was found");
        }
        reader.close();
    }

    private static void showAll() throws IOException {

        String line;
        reader = new BufferedReader(new InputStreamReader(new FileInputStream("contacts.txt.txt")));

        while ((line = reader.readLine()) != null) {
            line = line.replace("/", " ");
            System.out.println(line);
        }
        reader.close();
    }

    public static void add() {
        try {

            FileOutputStream writer = new FileOutputStream("contacts.txt.txt", true);
            System.out.println("Set name");
            String text;
            String line = "";

            if ((text = scan.readLine()) != null) {
                line += "Name:" + text + "/";

                System.out.println("Set phone");
                String phone = scan.readLine();

                if (phone != null) {
                    line += "Phone:" + phone + "/";


                    System.out.println("Set email");
                    String email = scan.readLine();
                    if (email != null) {
                        line += "Email:" + email + "/" + "\n";

                    }
                    writer.write(line.getBytes());
                    writer.close();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void remove() {
        try {
            System.out.println("Choose name");
            String a = scan.readLine();
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("contacts.txt")));
            PrintWriter printWriter = new PrintWriter("tempfile.txt");


            String s;
            while ((s = reader.readLine()) != null) {
                if (!s.contains(a)) {
                    printWriter.append(s).append("\n");
                }
            }
            reader.close();
            printWriter.close();

            Files.copy(Paths.get("tempfile.txt"), Paths.get("contacts.txt.txt"), StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
