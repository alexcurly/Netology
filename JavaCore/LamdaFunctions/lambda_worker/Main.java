class Main {
  public static void main(String[] args) {
    OnTaskDoneListener listener = System.out::println;
    OnTaskErrorListener listener2 = System.out::println;
    Worker worker = new Worker(listener, listener2);
    worker.start();
  }
}
