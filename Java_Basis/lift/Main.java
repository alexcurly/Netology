import java.util.*;

class Main {
  public static void main(String[] args) {

    Scanner scan = new Scanner(System.in);
    Queue<Operation> elevatorHistory = new PriorityQueue<>();

    try{

      boolean run = true;
      int i = 0;
      int putIn = 0;
      int currentLevel = 0;
      String historyOfLevels;
      do {
        System.out.println("Ожидаю ввод этажа: (для завершения введите 0)");

        putIn = scan.nextInt();

        // Проверка ввода числа пользователем
        if(putIn == 0){
          run = false;
          break;
        } else if(putIn == currentLevel){
          System.out.println("Вы находитесь на данном этаже");
        } else if(putIn > 25 || putIn < 0) {
          System.out.println("Ошибка ввода этажа");
        } else {

          i = i + 1;
          currentLevel = putIn;
          historyOfLevels = "Этаж "+ currentLevel + " -> ";
          elevatorHistory.offer(Operation.of(historyOfLevels, i));
        }
      } while (run);
    
        elevatorHistory.offer(Operation.of("Этаж 0 ", i + 1));
        System.out.println("Лифт проследовал по следующим этажам: ");
        workOperator(elevatorHistory);

    } catch (InputMismatchException exceprion) {
      System.out.println("Введен неверный символ. Перезапустите программу и попробуйте снова!");
    }
  }

  public static void workOperator(Queue<Operation> elevatorHistory) {
    while(!elevatorHistory.isEmpty()){
      System.out.print(elevatorHistory.poll());
    }
  }
}
