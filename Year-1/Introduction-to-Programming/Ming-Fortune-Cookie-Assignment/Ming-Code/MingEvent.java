package AssignmentFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Daniel Elstob
 */

   
    public class MingEvent implements ActionListener{
             
        String[] day28 = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"};
        String[] day30 = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" };
        String[] day31 = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
        String[] month = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] year = new String[113];
        ArrayList food = new ArrayList();
        ArrayList car = new ArrayList();
        ArrayList <String> yearlist = new ArrayList();
        
        public MingEvent() {
            controlMethod();
        }
        
        public void controlMethod() {
        ArrayList <String> yearlist = new ArrayList();
        for (int i = 1900;i < 2013;i++){
            yearlist.add(Integer.toString(i));
            year[i - 1900] = Integer.toString(i);
        }

        initFoods();
        initCars();

        }
      
      private void initFoods() {
        food.add("Apple");
        food.add("Banana");
        food.add("Pear");
        food.add("Pineapple");
        food.add("Starfruit");
        food.add("Melon");
        food.add("Tomatoe");
        food.add("Potatoe");
        food.add("Carrot");
        food.add("Peas");
        food.add("Crisps");
        food.add("Bread");
        food.add("Cereal");
        food.add("Rice");
        food.add("Curry");
        food.add("Chips");
        food.add("Pasta");
        food.add("Pizza");
        food.add("Kebab");
        food.add("Egg");
        food.add("Fish");
        food.add("Steak");
        food.add("Chicken");
        food.add("Lamb");
        food.add("Pork");
        food.add("Yoghurt");
        food.add("Biscuits");
        food.add("Cheese");
        food.add("Cake");
        food.add("Jelly");
        food.add("Ice Cream");
    }

    private void initCars() {
        car.add("Ford Focus ST");
        car.add("Audi R8");
        car.add("Aston DBS");
        car.add("Lotus Exige");
        car.add("Nissan GTR");
        car.add("Bugatti Veyron");
        car.add("BMW M5");
        car.add("MINI Hatch");
        car.add("Jaguar xk");
        car.add("Chevy Camaro");
        car.add("Lexus LFA");
        car.add("Mitsubishi Lancer");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
