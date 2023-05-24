/*
 * The Counter class counts the points given by a hand of cards 
 */
public class Counter {
	private PowerSet<Card> cardps;
	private Card starter;

	public Counter(Card[] hand, Card starter) {
		this.starter = starter;
		cardps = new PowerSet<Card>(hand);
	}

	/*
	 * Returns total score of the hand
	 */
	public int countPoints() {
		return pairScore() + runScore() + fifteenScore() + flushScore() + knobsScore();
	}

	/*
	 * Method which counts all pairs in the hand. It looks for all combinations
	 * containing two cards and keeps track of which of them contain the same
	 * numbered cards
	 */
	private int pairScore() {
		int count = 0;
		for (int i = 1; i < cardps.getLength(); i++) {
			Set<Card> SET = cardps.getSet(i);
			if (SET.getLength() == 2) {
				if (SET.getElement(0).getLabel() == SET.getElement(1).getLabel()) {
					count++;
				}
			}
		}

		return count * 2;

	}

	/*
	 * Method which counts the runs formed by the hand. It determines the score
	 * based on the length of the longest run and how many runs of that specific
	 * length were found
	 */
	private int runScore() {
		int highestCount = 0;
		int numRuns = 0;
		int numRuns2 = 0;
		for (int i = 1; i < cardps.getLength(); i++) {
			Set<Card> SET = cardps.getSet(i);
			if (isRun(SET)) {

				if (SET.getLength() == 5) {
					highestCount = 5;
				}

				else if (SET.getLength() == 4) {
					highestCount = 4;
					numRuns2++;
				}

				else if (SET.getLength() == 3) {
					highestCount = 3;
					numRuns++;
				}

			}
		}

		if (highestCount == 3) {
			return numRuns * 3;
		}

		else if (highestCount == 4) {
			return numRuns2 * 4;
		}

		else if (highestCount == 5) {
			return 5;
		}

		else {
			return 0;
		}

	}

	/*
	 * Method which counts all fifteens given by a hand. It searches through all
	 * possible card combinations and counts how many equate to 15 points total
	 */
	private int fifteenScore() {
		int count = 0;
		for (int i = 1; i < cardps.getLength(); i++) {
			int total = 0;
			Set<Card> SET = cardps.getSet(i);
			for (int j = 0; j < SET.getLength(); j++) {
				total += SET.getElement(j).getFifteenRank();
			}
			if (total == 15) {
				count++;
			}
		}

		return count * 2;
	}

	/*
	 * Determines if the hand is a flush or not by looking for a combination where
	 * all cards have the same suit. The combination must also not have the starter
	 * card, or otherwise it does not count as a flush. If the starter card has the
	 * same suit as the combination one extra point will be added to the score.
	 */
	private int flushScore() {
		boolean isFlush = true;
		for (int i = 1; i < cardps.getLength(); i++) {
			boolean hasStarter = false;
			Set<Card> SET = cardps.getSet(i);
			for (int j = 0; j < SET.getLength(); j++) {
				if (SET.getElement(j) == this.starter) {
					hasStarter = true;
				}
			}

			if (hasStarter == false && SET.getLength() == 4) {
				String suit = SET.getElement(0).getSuit();
				for (int k = 1; k < SET.getLength(); k++) {
					if (SET.getElement(k).getSuit() != suit) {
						isFlush = false;
					}
				}

				if (isFlush == true) {
					if (suit == starter.getSuit()) {
						return 5;
					}
					return 4;
				}
			}

		}

		return 0;
	}

	/*
	 * Determines whether the hand has a Has Knobs or not by looking for a jester
	 * card that has the same suit as the starter card
	 */
	private int knobsScore() {
		Set<Card> SET = cardps.getSet(cardps.getLength() - 1);
		for (int i = 0; i < SET.getLength(); i++) {
			if ((SET.getElement(i).getLabel() == "J") && (SET.getElement(i).getSuit() == starter.getSuit())) {
				return 1;
			}
		}
		return 0;
	}

	/*
	 * In this method, we are going through the given set to check if it constitutes
	 * a run of 3 or more consecutive cards. To do this, we are going to create an
	 * array of 13 cells to represent the range of card ranks from 1 to 13. We go
	 * through each card and increment the cell corresponding to each card's rank.
	 * For example, an Ace (rank 1) would cause the first (index 0) cell to
	 * increment. An 8 would cause the 8th (index 7) cell to increment. When this
	 * loop is done, the array will contain 5 or less cells with values of 1 or more
	 * to represent the number of cards with each rank. Then we can use this array
	 * to search for 3 or more consecutive non-zero values to represent a run.
	 */
	private boolean isRun(Set<Card> set) {
		int n = set.getLength();

		if (n <= 2)
			return false; // Run must be at least 3 in length.

		int[] rankArr = new int[13];
		for (int i = 0; i < 13; i++)
			rankArr[i] = 0; // Ensure the default values are all 0.

		for (int i = 0; i < n; i++) {
			rankArr[set.getElement(i).getRunRank() - 1] += 1;
		}

		// Now search in the array for a sequence of n consecutive 1's.
		int streak = 0;
		int maxStreak = 0;
		for (int i = 0; i < 13; i++) {
			if (rankArr[i] == 1) {
				streak++;
				if (streak > maxStreak)
					maxStreak = streak;
			} else {
				streak = 0;
			}
		}
		if (maxStreak == n) { // Check if this is the maximum streak.
			return true;
		} else {
			return false;
		}

	}

}
