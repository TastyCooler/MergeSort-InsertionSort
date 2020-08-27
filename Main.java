class Sortierung {

 public static void main(String[] args) {

  // Wird für die Messung der Zeit genutzt
  long tStart, tEnd, msecs;

  // Hier wird der Code ausgefuehrt, dessen Laufzeit gemessen werden soll
  // Hier werden die Parameter Regeln aufgestellt
  if (args.length < 1) {

   System.out.println("FEHLER: Du musst ein Argument angeben");

  } else if (Integer.parseInt(args[0]) <= 0) {

   System.out.println("FEHLER: Feld muss größer als 0 sein, deine Feldgröße war: " + Integer.parseInt(args[0]));

  } else {

   // Hier beginnt die Sortier Logik
   Sortierung i = new Sortierung();
   // Neues Feld mit größe des übergebenen Parameters
   int[] x = new int[Integer.parseInt(args[0])];

   // Feld initialisierung bei den Argumenten "auf", "ab" und "rand"
   if (args.length == 1 || args[2].equals("rand")) {

    java.util.Random numberGenerator = new java.util.Random();

    for (int a = 0; a < x.length; a++) {

     x[a] = (int) numberGenerator.nextInt(100);

    }

   } else if (args[2].equals("auf")) {

    for (int a = 0; a < x.length; a++) {

     x[a] = a;

    }

   } else if (args[2].equals("ab")) {

    for (int a = 0; a < x.length; a++) {

     x[a] = x.length - 1 - a;

    }

   }

   // Es sollen nicht mehr als 100 Elemente angezeigt werden?
   int cap;

   if (x.length > 100) {

    cap = 99;

   } else {

    cap = x.length;

   }

   // Hier werden die Zahlen vor der Sortierung angezeigt
   for (int j = 0; j < cap; j++) {

    System.out.print(x[j] + " ");

   }

   System.out.print("\n");

   // Beginn der Messung
   tStart = System.currentTimeMillis();
   // Insertionsort 


   if (args.length == 1 || args[1].equals("merge")) {
    i.mergeSort(x);
   } else {
    i.insertionSort(x);
   }

   // Ende der Messung
   tEnd = System.currentTimeMillis();
   // Die vergangene Zeit ist die Differenz von tStart und tEnd
   msecs = tEnd - tStart;
   // Hier werden die Zahlen nach der Sortierung angezeigt
   for (int j = 0; j < cap; j++) {

    System.out.print(x[j] + " ");

   }


   // Überprüfung ob auch richtig sortiert wurde, inkl. Ausgabe
   if (i.isSorted(x, args)) {

    System.out.println("\nFeld ist sortiert!");

   } else {

    System.out.println("\nFEHLER: Feld ist NICHT sortiert!");

   }

   System.out.println("Das Sortieren des Arrays hat " + msecs + "ms gedauert");
  }

 }

 public static void mergeSort(int[] array, int[] tempArray, int left, int right) {

  if (left < right) {

   int mid = (left + right) / 2;

   mergeSort(array, tempArray, left, mid);
   mergeSort(array, tempArray, mid + 1, right);
   merge(array, tempArray, left, mid, right);
  }

 }

 public static void mergeSort(int[] array) {

  int[] tmpArray = new int[array.length];
  mergeSort(array, tmpArray, 0, array.length - 1);
  //assert isSorted(array, args); //args ist local variable, um diese funktion zu benutzen brauche ich entweder isSorted mit einem paramter, also ohne String args oder eine Globale Variable args. 
 }

 public static void merge(int[] array, int[] tempArray, int left, int mid, int right) {

  int indexLeft = left;
  int indexRight = mid + 1;
  int marked = left;

  // TempArray befüllen mit der Hälfte des Array Inhalts
  for (int i = left; i <= right; i++) {
   tempArray[i] = array[i];
  }

  // Sortiervorgang:
  // Zusammenfügen der Inhalte aus beiden Arrays
  while (indexLeft <= mid && indexRight <= right) {
   if (tempArray[indexLeft] < tempArray[indexRight]) {
    array[marked] = tempArray[indexLeft];
    indexLeft++;
   } else {
    array[marked] = tempArray[indexRight];
    indexRight++;
   }
   marked++;
  }

  // Restliche Elemente von der linken Hälfte zusammenfügen
  while (indexLeft <= mid) {
   array[marked] = tempArray[indexLeft];
   indexLeft++;
   marked++;
  }

  // Restliche Elemente von der rechten Hälfte zusammenfügen
  while (indexRight <= right) {
   array[marked] = tempArray[indexRight];
   indexRight++;
   marked++;
  }

 }



 public static void insertionSort(int[] array) {

  assert array.length > 0: array.length + "ist nicht größer 0";
  //Pseudocode fängt bei j= 2 an, weil index bei 1 beginnt, wieso tut man das?
  for (int j = 1; j < array.length; j++) {

   int key = array[j];
   int i = j - 1;

   //Für aufsteigende Sortierung einfach array[i] > key abfragen
   while (i >= 0 && array[i] < key) {

    array[i + 1] = array[i];
    i = i - 1;

   }
   array[i + 1] = key;
  }

 }


 public static boolean isSorted(int[] array, String[] args) {

  assert array.length > 0: array.length + "ist nicht größer 0";

  boolean sorted = false;
  if (args.length == 1 || args[1].equals("merge")) {

   for (int i = 0; i < array.length - 1; i++) {

    if (array[i] <= array[i + 1]) {

     sorted = true;

    } else {
     return false;
    }
   }

  } else {

   for (int i = 0; i < array.length - 1; i++) {

    if (array[i] >= array[i + 1]) {

     sorted = true;

    } else {
     return false;
    }
   }
  }
  return sorted;
 }


}