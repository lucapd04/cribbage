/*
 * The PowerSet clans is responsible for detecting and compiling all possible card combinations in the round of scribbage
 */
public class PowerSet<T> {
	private Set<T>[] set;

	/*
	 * The constructor stores all the possible combinations within an array: 1.
	 * First, the length of the array is determined by calculating the number of
	 * possible combinations. Then, the for loop converts all numbers up to that to
	 * binary. 2. The binary numbers are then used to create the combinations that
	 * will be stored within the array, with each one representing a card within the
	 * hand
	 */
	public PowerSet(T[] elements) {
		int NUMBER_OF_COMPONENTS = (int) Math.pow(2, elements.length);
		set = new Set[NUMBER_OF_COMPONENTS];
		String[] binaries = new String[NUMBER_OF_COMPONENTS];
		for (int i = 0; i < NUMBER_OF_COMPONENTS; i++) {
			String binaryNum = Integer.toBinaryString(i);
			if (binaryNum.length() < elements.length) {
				binaries[i] = ("0".repeat(elements.length - binaryNum.length())) + binaryNum;
			} else {
				binaries[i] = binaryNum;
			}

		}

		for (int j = 0; j < binaries.length; j++) {
			Set<T> secondarySets = new Set<T>();
			for (int k = 0; k < binaries[j].length(); k++) {
				String[] STRING_ARRAY = binaries[j].split("");
				if (STRING_ARRAY[k].equals("1")) {
					secondarySets.add(elements[k]);
				}

			}

			set[j] = secondarySets;
		}

	}

	/*
	 * Getter which returns the amount of possible combinations
	 */
	public int getLength() {
		return set.length;
	}

	/*
	 * Getter which returns one of the possible combinations of cards
	 */
	public Set<T> getSet(int i) {
		return set[i];
	}

}
