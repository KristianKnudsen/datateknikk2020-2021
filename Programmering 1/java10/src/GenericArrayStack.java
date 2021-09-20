public class GenericArrayStack<E> {

  private E [] elements = (E[]) new Object[1];

  public int getSize() {
    int counter = 0;

    for (E element : elements) {
      if (element != null) {
        counter++;
      }
    }

    return counter;
  }

  public E peek() {
    return elements[getSize()-1];
  }

  public void push(E o) {
    if ( elements.length == getSize()) {
      doubleElements();
    }

    elements[getSize()] = o;
  }

  private void doubleElements(){
    E [] temp = (E[]) new Object[elements.length*2];
    System.arraycopy(elements, 0, temp, 0, elements.length);
    elements = temp;
  }

  public E pop() {
    E o = elements[getSize()-1];
    elements[getSize()-1] = null;
    return o;
  }

  public boolean isEmpty() {
    return elements[0] == null;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("stack: [");

    if ( getSize() > 0) {
      for (int i = 0; i < getSize(); i++){
        sb.append(elements[i]).append(", ");
      }
      sb.setLength(sb.length() - 2);
    }

    sb.append("]");

	return sb.toString();
  }
}
