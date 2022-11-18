package ru.netology.graphics;
import ru.netology.graphics.image.*;
import ru.netology.graphics.server.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception {

        TextGraphicsConverter converter;    // Создайте тут объект вашего класса конвертера
        converter = new TextGraphicsConverter() {
            @Override
            public String convert(String url) throws IOException , BadImageSizeException {
                StringBuilder sb = new StringBuilder();
                Scanner scan = new Scanner(System.in);

                // Требования к цветовой схеме. Создал массив из 8 символов, на которые будет заменяться цветовой тон.
                //char[][] symbols = {{'"', '-', '+', '*', '%', '@', '$', '#'}};
                char[] symbols = {'"', '-', '+', '*', '%', '@', '$', '#'};
                // Так как символов 8, а тонов 256 (2^8 = 256), то справедливо воспользоваться log по основанию 2.

                TextColorSchema schema = color -> symbols[(int) (Math.log(color + 1)/Math.log(2)+1e-10)];

//                TextColorSchema schema = new TextColorSchema() {
//                    @Override
//                    public char convert(int color) {
//
//                        return symbols[(int) (Math.log(color + 1)/Math.log(2)+1e-10)];
//                    }
//                };

                // скачаем картинку из интернета
                BufferedImage img = ImageIO.read(new URL(url));

                // Если конвертер попросили проверять на максимально допустимое
                // соотношение сторон изображения, то вам здесь надо сделать эту проверку,
                // и, если картинка не подходит, выбросить исключение BadImageSizeException.
                // Чтобы получить ширину картинки, вызовите img.getWidth(), высоту - img.getHeight()
                int widhtOfImage = img.getWidth();
                int heightOfImage = img.getHeight();
                double currentRation = ((double)widhtOfImage / (double)heightOfImage);

                System.out.format("\nПолучим данные о переданной картинке. Ширина: %d , Высота: %d \n",widhtOfImage, heightOfImage);

                int permissibleHeightOfImage = 3000;
                int permissibleWidhtOfImage = 5000;
                double maxRation = 5;

                setMaxRatio(maxRation);
                setMaxHeight(permissibleHeightOfImage);
                setMaxWidth(permissibleWidhtOfImage);

                boolean question = true;
//                boolean changeControl = false;

                while(question){
                    int questionInt;
                    System.out.println("Для добавления настройки введите ее номер, если желаете продолжить введите 0: \n" +
                            "1. Можно установить максимально допустимое соотношение сторон (ширины и высоты); если метод не вызывали, то любое соотношение допустимо;\n" +
                            "2. Можно установить максимально допустимую высоту итогового изображения; если метод не вызывали, то любая высота допустима;\n" +
                            "3. Можно установить максимально допустимую ширину итогового изображения; если метод не вызывали, то любая ширина допустима;\n" +
                            "4. Можно установить текстовую цветовую схему.");

                    try{
                        questionInt = Integer.parseInt(scan.nextLine());
                        if(questionInt == 0){
                            question = false;
                            break;
                        }
                        switch (questionInt) {

//                            case (0):
//                                question = false;
//                                break;
                            case (1):
                                System.out.println("Введите значение максимального соотношения сторон (ширины и высоты) \n");
                                //setMaxRatio(Double.parseDouble(scan.nextLine()));
                                maxRation = Double.parseDouble(scan.nextLine());
//                                changeControl = true;
                                break;

                            case (2):
                                System.out.println("Введите значение максимально допустимой высоты итогового изображения\n");
                                permissibleHeightOfImage = Integer.parseInt(scan.nextLine());
                                //setMaxHeight(Integer.parseInt(scan.nextLine()));
//                                changeControl = true;
                                break;

                            case (3):
                                System.out.println("Введите значение максимально допустимой ширины итогового изображения\n");
                                permissibleWidhtOfImage = Integer.parseInt(scan.nextLine());
                                //setMaxWidth(Integer.parseInt(scan.nextLine()));
//                                changeControl = true;
                                break;

                            case (4):
                                System.out.println("Введите разом без пробелов 8 символов, которые будут отвечать за цветовую схему, где первый самый светлый, а последний самый темный\n");
                                //char[] helpsymbols = new char[8];
                                String newSymbols = scan.nextLine();
                                char[] helpsymbols = newSymbols.toCharArray(); // returns a length 8 char array ['#','!','$'...]
                                for(int i = 0; i < 8; i++){
                                    symbols[i] = helpsymbols[i];
                                }
//                                changeControl = true;
                                break;
                        }
                    } catch (Exception IOException){
                        System.out.println("Ввод был совершен некорректно.");
                    }
                }

                System.out.println("\n Требуется ли провести специальную проверку на соотношение кратностей сторон?\nВведите номер ответа\n" + "0. Нет\n" + "1. Да\n");
                int question2 = Integer.parseInt(scan.nextLine());
                if (question2 == 1) {
                    if(currentRation > maxRation){
                        throw new BadImageSizeException(currentRation, maxRation);
                    }
                }

                int differenceMagnitudeOfWidht;
                int differenceMagnitudeOfHeight;
                int differenceMagnitude;
                int newHeight = heightOfImage;
                int newWidth = widhtOfImage;
                boolean testOfParametrs = true;

                if( (currentRation > maxRation) || (permissibleHeightOfImage < heightOfImage) || (permissibleWidhtOfImage < widhtOfImage) ) {

                    System.out.println("В программе начата проверка параметров");
                    System.out.println(" currentRation: " + currentRation + ", maxRation: " + maxRation);
                    System.out.println(" permissibleHeightOfImage: " + permissibleHeightOfImage + ", heightOfImage: " + heightOfImage);
                    System.out.println(" permissibleWidhtOfImage: " + permissibleWidhtOfImage + ", widhtOfImage: " + widhtOfImage);

                    differenceMagnitudeOfWidht = (heightOfImage / permissibleWidhtOfImage) + 1;
                    differenceMagnitudeOfHeight = (heightOfImage / permissibleHeightOfImage) + 1;

                    System.out.format("\nПолучим данные о расхождении размеров. По ширине: %d , По высоте: %d \n"
                            ,differenceMagnitudeOfWidht, differenceMagnitudeOfHeight);

                    // Если конвертеру выставили максимально допустимые ширину и/или высоту,
                    // вам надо по ним и по текущим высоте и ширине вычислить новые высоту
                    // и ширину.
                    // Соблюдение пропорций означает, что вы должны уменьшать ширину и высоту должны
                    // в одинаковое количество раз.
                    // Пример 1: макс. допустимые 100x100, а картинка 500x200. Новый размер
                    // будет 100x40 (в 5 раз меньше).
                    // Пример 2: макс. допустимые 100x30, а картинка 150x15. Новый размер
                    // будет 100x10 (в 1.5 раза меньше).
                    // Подумайте, какими действиями можно вычислить новые размеры.
                    // Не получается? Спросите вашего руководителя по курсовой, поможем!

                    if(differenceMagnitudeOfWidht >= differenceMagnitudeOfHeight) {
                        differenceMagnitude = differenceMagnitudeOfWidht;
                        System.out.format("\nСжимание картинки будет проводится с коэф.сжатия: %d \n",differenceMagnitude);
                        //setMaxRatio(differenceMagnitude);
                    } else {
                        differenceMagnitude = differenceMagnitudeOfHeight;
                        System.out.format("\nСжимание картинки будет проводится с коэф.сжатия: %d \n",differenceMagnitude);
                        //setMaxRatio(differenceMagnitude);

                    }
                    newWidth = widhtOfImage / differenceMagnitude;
                    newHeight = heightOfImage / differenceMagnitude;

                    // Теперь нам надо попросить картинку изменить свои размеры на новые.
                    // Последний параметр означает, что мы просим картинку плавно сузиться
                    // на новые размеры. В результате мы получаем ссылку на новую картинку, которая
                    // представляет собой суженную старую.

                    Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
                    System.out.format("\nПолучим сжатую картинку. Ширина: %d , Высота: %d \n",newWidth, newHeight);
                    // Теперь сделаем её чёрно-белой. Для этого поступим так:
                    // Создадим новую пустую картинку нужных размеров, заранее указав последним
                    // параметром чёрно-белую цветовую палитру:
                    BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);

                    // Попросим у этой картинки инструмент для рисования на ней:
                    Graphics2D graphics = bwImg.createGraphics();

                    // А этому инструменту скажем, чтобы он скопировал содержимое из нашей суженной картинки:
                    graphics.drawImage(scaledImage, 0, 0, null);
                    // Теперь в bwImg у нас лежит чёрно-белая картинка нужных нам размеров.
                    // Вы можете отслеживать каждый из этапов, просто в любом удобном для
                    // вас моменте сохранив промежуточную картинку в файл через:
                    // ImageIO.write(imageObject, "png", new File("out.png"));
                    // После вызова этой инструкции у вас в проекте появится файл картинки out.png

                    // Теперь давайте пройдёмся по пикселям нашего изображения.
                    // Если для рисования мы просили у картинки .createGraphics(),
                    // то для прохода по пикселям нам нужен будет этот инструмент:
                    WritableRaster bwRaster = bwImg.getRaster();
                    // Он хорош тем, что у него мы можем спросить пиксель на нужных
                    // нам координатах, указав номер столбца (w) и строки (h)
                    // int color = bwRaster.getPixel(w, h, new int[3])[0];
                    // Выглядит странно? Согласен. Сам возвращаемый методом пиксель — это
                    // массив из трёх интов, обычно это интенсивность красного, зелёного и синего.
                    // Но у нашей чёрно-белой картинки цветов нет, и нас интересует
                    // только первое значение в массиве. Вы спросите, а зачем
                    // мы ещё параметром передаём интовый массив на три ячейки?
                    // Дело в том, что этот метод не хочет создавать его сам и просит
                    // вас сделать это, а сам метод лишь заполнит его и вернёт.
                    // Потому что создавать массивы каждый раз слишком медленно. Вы можете создать
                    // массив один раз, сохранить в переменную и передавать один
                    // и тот же массив в метод, ускорив тем самым программу.

                    // Вам осталось пробежаться двойным циклом по всем столбцам (ширина)
                    // и строкам (высота) изображения, на каждой внутренней итерации
                    // получить степень белого пикселя (int color выше) и по ней
                    // получить соответствующий символ c. Логикой превращения цвета
                    // в символ будет заниматься другой объект, который мы рассмотрим ниже
                    char[][] arraySymbols;
                    arraySymbols = new char [newHeight][newWidth];
                    for (int h = 0; h < newHeight; h++) {
                        for (int w = 0; w < newWidth; w++) {
                            int color = bwRaster.getPixel(w, h, new int[3])[0];
                            char c = schema.convert(color);
                            //запоминаем символ c, например, в двумерном массиве или как-то ещё на ваше усмотрение
                            arraySymbols[h][w] = c;
                        }
                    }

                    //Процесс отладки, попробуем вывести сформированный массив:
//                            for (int h = 0; h < newHeight; h++) {
//                                //System.out.println("");
//                                for (int w = 0; w < newWidth; w++) {
//                                    //System.out.print(arraySymbols[h][w] + " ");
//                                }
//                            }

                    // Осталось собрать все символы в один большой текст
                    // Для того, чтобы изображение не было слишком узким, рекомендую
                    // каждый пиксель превращать в два повторяющихся символа, полученных
                    // от схемы.
                    int offset = 0;
                    for(int i = 0; i < newHeight; i++) {
                        sb.insert(offset, "\n" );
                        offset = offset + 1;

                        for(int j = 0; j < newWidth; j++) {
                            char symbol = arraySymbols[i][j];
                            sb.insert(offset, symbol );
                            offset = offset + 1;
                            sb.insert(offset, symbol );
                            offset = offset + 1;
                        }
                    }
                } else {
                    System.out.println("Картинка удовлетворяет условиям по разрешенным размерам. Сжатие не потребовалось.");

                    Image scaledImage = img.getScaledInstance(widhtOfImage, heightOfImage, BufferedImage.SCALE_SMOOTH);
                    BufferedImage bwImg = new BufferedImage(widhtOfImage, heightOfImage, BufferedImage.TYPE_BYTE_GRAY);
                    Graphics2D graphics = bwImg.createGraphics();
                    graphics.drawImage(scaledImage, 0, 0, null);

                    WritableRaster bwRaster = bwImg.getRaster();

                    char[][] arraySymbols;
                    arraySymbols = new char [newHeight][newWidth];
                    for (int h = 0; h < newHeight; h++) {
                        for (int w = 0; w < newWidth; w++) {
                            int color = bwRaster.getPixel(w, h, new int[3])[0];
                            char c = schema.convert(color);
                            //запоминаем символ c, например, в двумерном массиве или как-то ещё на ваше усмотрение
                            arraySymbols[h][w] = c;
                        }
                    }

//                    //Процесс отладки, попробуем вывести сформированный массив:
//                    for (int h = 0; h < newHeight; h++) {
//                        System.out.print("\n");
//                        for (int w = 0; w < newWidth; w++) {
//                            System.out.print(arraySymbols[h][w] + " ");
//                        }
//                    }
                    int offset = 0;
                    for(int i = 0; i < newHeight; i++) {
                        sb.insert(offset, "\n" );
                        offset = offset + 1;
                        for(int j = 0; j < newWidth; j++) {
                            char symbol = arraySymbols[i][j];
                            sb.insert(offset, symbol );
                            offset = offset + 1;
                            sb.insert(offset, symbol );
                            offset = offset + 1;
                        }
                    }
                    return sb.toString(); // Возвращаем собранный текст.
                }
                return sb.toString(); // Возвращаем собранный текст.
            }

            @Override
            public void setMaxWidth(int width) {

            }

            @Override
            public void setMaxHeight(int height) {

            }

            @Override
            public void setMaxRatio(double maxRatio) {

            }

            @Override
            public void setTextColorSchema(TextColorSchema schema) {

            }
        };

        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем

        // Или то же, но с выводом на экран:
//        String url = "https://raw.githubusercontent.com/netology-code/java-diplom/main/pics/simple-test.png";
//        String imgTxt = converter.convert(url);
//        System.out.println(imgTxt);

    }
}
