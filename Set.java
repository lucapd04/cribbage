/*
 * The Set class is responsible for storing a hand of cards in the form of a single-linked list
 */
public class Set<T> {
	private LinearNode<T> setStart;

	/*
	 * Constructor sets the head and tail of the linked list to be equal to null
	 */
	public Set() {
		setStart = null;
	}

	/*
	 * The add method prepends a new node to the list with the corresponding element
	 * as its value
	 */
	public void add(T element) {
		LinearNode<T> newNode = new LinearNode<T>(element);

		if (setStart == null) {
			setStart = newNode;
			newNode.setNext(null);
		} else {
			newNode.setNext(setStart);
			setStart = newNode;
		}

	}

	/*
	 * Getter which returns the length of the linked list by keeping count of the
	 * nodes until they reach a null node
	 */
	public int getLength() {
		LinearNode<T> current = setStart;
		int totalElements = 0;
		while (current != null) {
			totalElements += 1;
			current = current.getNext();
		}

		return totalElements;

	}

	/*
	 * Getter which returns the value of the node at the corresponding location
	 */
	public T getElement(int i) {
		LinearNode<T> current = setStart;
		T element = current.getElement();
		for (int j = 0; j <= i; j++) {
			element = current.getElement();
			current = current.getNext();
		}
		return element;
	}

	/*
	 * This method checks if one of the nodes in the list contains that specific
	 * value
	 */
	public boolean contains(T element) {
		LinearNode<T> current = setStart;
		while (current != null) {
			if (current.getElement() == element) {
				return true;
			}
			current = current.getNext();
		}

		return false;
	}

	/*
	 * This method returns a string which contains all the values of each node
	 */
	@Override
	public String toString() {
		LinearNode<T> current = setStart;
		String fullSet = "";
		while (current != null) {
			fullSet += String.valueOf(current.getElement());
			if (current.getNext() != null) {
				fullSet += " ";
			}

			current = current.getNext();
		}

		return fullSet;
	}

}
