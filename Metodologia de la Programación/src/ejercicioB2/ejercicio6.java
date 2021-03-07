package ejercicioB2;

public class ejercicio6 {
	
	public static void main(String[] args) {
		
		int[] numbers = new int[] { 4, 2, 3, 1, 5, 1000 };

		int kPosition0 = 0;
		int kPosition3 = 3;
		int kPosition5 = 5;

		System.out.println("K-ésimo (0) menor elemento del vector: "
				+ kthSmallest(numbers, 0, numbers.length - 1, kPosition0 + 1));
		System.out.println("K-ésimo (3) menor elemento del vector: "
				+ kthSmallest(numbers, 0, numbers.length - 1, kPosition3 + 1));
		System.out.println("K-ésimo (5) menor elemento del vector: "
				+ kthSmallest(numbers, 0, numbers.length - 1, kPosition5 + 1));
	}
	
	public static int kthSmallest(int[] ns, int li, int ls, int n) {

		int partition = partition(ns, li, ls);

		if (partition == n - 1) {
			return ns[partition];
		} else if (partition < n - 1) {
			return kthSmallest(ns, partition + 1, ls, n);
		} else {
			return kthSmallest(ns, li, partition - 1, n);
		}
	}
	
	public static int partition(int[] ns, int li, int ls) {
		int pivot = ns[ls], pivotloc = li;
		
		for (int i = li; i <= ls; i++) {
			if (ns[i] < pivot) {
				int temp = ns[i];
				ns[i] = ns[pivotloc];
				ns[pivotloc] = temp;
				pivotloc++;
			}
		}

		int aux = ns[ls];
		ns[ls] = ns[pivotloc];
		ns[pivotloc] = aux;

		return pivotloc;
	}
}
