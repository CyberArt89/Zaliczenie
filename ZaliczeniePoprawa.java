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

