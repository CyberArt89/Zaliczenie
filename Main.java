import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nWybierz opcję:");
            System.out.println("1. Sortowanie przez wstawianie");
            System.out.println("2. Szybkie sortowanie");
            System.out.println("3. Drzewo binarne");
            System.out.println("4. Wyjście");
            int wybor = scanner.nextInt();
            scanner.nextLine();

            switch (wybor) {
                case 1:
                    sortowaniePrzezWstawianie(scanner);
                    break;
                case 2:
                    szybkieSortowanie(scanner);
                    break;
                case 3:
                    drzewoBinarne(scanner);
                    break;
                case 4:
                    System.out.println("Koniec programu.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Nieprawidłowy wybór, spróbuj ponownie.");
            }
        }
    }

    private static void sortowaniePrzezWstawianie(Scanner scanner) {
        System.out.println("Wybierz typ danych do sortowania: (1 - Liczby, 2 - Ciągi znaków, 3 - Daty)");
        int typ = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Podaj elementy oddzielone spacją:");
        String[] input = scanner.nextLine().split(" ");

        System.out.println("Sortowanie rosnąco? (true/false)");
        boolean rosnaco = scanner.nextBoolean();

        switch (typ) {
            case 1: // Liczby
                Integer[] liczby = Arrays.stream(input).map(Integer::parseInt).toArray(Integer[]::new);
                new SortowaniePrzezWstawianie<Integer>().sortuj(liczby, rosnaco);
                System.out.println("Posortowane: " + Arrays.toString(liczby));
                break;
            case 2: // Ciągi znaków
                String[] ciagi = input;
                new SortowaniePrzezWstawianie<String>().sortuj(ciagi, rosnaco);
                System.out.println("Posortowane: " + Arrays.toString(ciagi));
                break;
            case 3: // Daty
                Date[] daty = Arrays.stream(input).map(str -> {
                    try {
                        return new SimpleDateFormat("yyyy-MM-dd").parse(str);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).toArray(Date[]::new);
                new SortowaniePrzezWstawianie<Date>().sortuj(daty, rosnaco);
                System.out.println("Posortowane: " + Arrays.toString(daty));
                break;
            default:
                System.out.println("Nieprawidłowy wybór.");
        }
    }

    private static void szybkieSortowanie(Scanner scanner) {
        System.out.println("Podaj liczby rzeczywiste oddzielone spacją:");
        List<Double> liczby = new ArrayList<>();
        for (String s : scanner.nextLine().split(" ")) {
            liczby.add(Double.parseDouble(s));
        }

        SzybkieSortowanie qs = new SzybkieSortowanie();
        qs.sortuj(liczby, 0, liczby.size() - 1);
        System.out.println("Posortowane: " + liczby);
    }

    private static void drzewoBinarne(Scanner scanner) {
        DrzewoBinarne drzewo = new DrzewoBinarne();
        System.out.println("Podaj liczby do drzewa binarnego (oddzielone spacją):");
        for (String s : scanner.nextLine().split(" ")) {
            drzewo.wstaw(Integer.parseInt(s));
        }

        System.out.println("Wybierz sposób przeglądania drzewa:");
        System.out.println("1. Inorder");
        System.out.println("2. Preorder");
        System.out.println("3. Postorder");
        int typ = scanner.nextInt();

        System.out.print("Wynik: ");
        switch (typ) {
            case 1:
                drzewo.przejscieInorder();
                break;
            case 2:
                drzewo.przejsciePreorder();
                break;
            case 3:
                drzewo.przejsciePostorder();
                break;
            default:
                System.out.println("Nieprawidłowy wybór.");
                        }
            }
}
