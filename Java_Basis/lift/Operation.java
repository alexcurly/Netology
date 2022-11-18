public class Operation implements Comparable<Operation> {

  private String push;
  private int priority;

  public Operation( String push, int priority){
    this.push = push;
    this.priority = priority;
  }

  public static Operation of(String push, int priority) {
    return new Operation(push, priority);
  }

  @Override
  public String toString(){
    return String.format("%s ",push);
  }

  @Override
  public int compareTo(Operation altOperation){
    // < 0   --> this < altOperation
    // == 0  --> this == altOperation
    // > 0   --> this > altOperation

    return priority - altOperation.priority;

  }
}
