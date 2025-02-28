import java.util.*;
import java.text.SimpleDateFormat;

class SortowaniePrzezWstawianie<T extends Comparable<T>> {
    public void sortuj(T[] tablica, boolean rosnąco) {
        for (int i = 1; i < tablica.length; i++) {
            T klucz = tablica[i];
            int j = i - 1;
            while (j >= 0 && (rosnąco ? tablica[j].compareTo(klucz) > 0 : tablica[j].compareTo(klucz) < 0)) {
                tablica[j + 1] = tablica[j];
                j--;
            }
            tablica[j + 1] = klucz;
        }
    }
}

class SzybkieSortowanie {
    public void sortuj(List<Double> lista, int lewy, int prawy) {
        if (lewy < prawy) {
            int pi = podziel(lista, lewy, prawy);
            sortuj(lista, lewy, pi - 1);
            sortuj(lista, pi + 1, prawy);
        }
    }

    private int podziel(List<Double> lista, int lewy, int prawy) {
        int srodek = lewy + (prawy - lewy) / 2;
        double pivot = mediana(lista.get(lewy), lista.get(srodek), lista.get(prawy));

        if (pivot == lista.get(lewy)) Collections.swap(lista, lewy, prawy);
        else if (pivot == lista.get(srodek)) Collections.swap(lista, srodek, prawy);

        double wartoscPivot = lista.get(prawy);
        int i = lewy - 1;

        for (int j = lewy; j < prawy; j++) {
            if (lista.get(j) <= wartoscPivot) {
                i++;
                Collections.swap(lista, i, j);
            }
        }
        Collections.swap(lista, i + 1, prawy);
        return i + 1;
    }

    private double mediana(double a, double b, double c) {
        return (a > b) ? ((a < c) ? a : Math.max(b, c)) : ((b < c) ? b : Math.max(a, c));
    }
}


class WezelDrzewa {
    int wartosc;
    WezelDrzewa lewy, prawy;

    WezelDrzewa(int wartosc) {
        this.wartosc = wartosc;
        this.lewy = this.prawy = null;
    }
}

class DrzewoBinarne {
    private WezelDrzewa korzen;

    public void wstaw(int wartosc) {
        korzen = wstawWezel(korzen, wartosc);
    }

    private WezelDrzewa wstawWezel(WezelDrzewa wezel, int wartosc) {
        if (wezel == null) {
            return new WezelDrzewa(wartosc);
        }
        if (wartosc < wezel.wartosc) {
            wezel.lewy = wstawWezel(wezel.lewy, wartosc);
        } else if (wartosc > wezel.wartosc) {
            wezel.prawy = wstawWezel(wezel.prawy, wartosc);
        }
        return wezel;
    }

    public void przejscieInorder() {
        inorder(korzen);
        System.out.println();
    }

    private void inorder(WezelDrzewa wezel) {
        if (wezel != null) {
            inorder(wezel.lewy);
            System.out.print(wezel.wartosc + " ");
            inorder(wezel.prawy);
        }
    }

    public void przejsciePreorder() {
        preorder(korzen);
        System.out.println();
    }

    private void preorder(WezelDrzewa wezel) {
        if (wezel != null) {
            System.out.print(wezel.wartosc + " ");
            preorder(wezel.lewy);
            preorder(wezel.prawy);
        }
    }

    public void przejsciePostorder() {
        postorder(korzen);
        System.out.println();
    }

    private void postorder(WezelDrzewa wezel) {
        if (wezel != null) {
            postorder(wezel.lewy);
            postorder(wezel.prawy);
            System.out.print(wezel.wartosc + " ");
        }
    }
}

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
