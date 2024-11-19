package me.koba1.cegesautok;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.koba1.cegesautok.feladatok.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

@Getter
public class Main {
    private List<Car> cars;
    private List<CarPair> carPairs;
    @Getter private static Main instance;

    public Main() throws FileNotFoundException {
        this.cars = new ArrayList<>();
        this.carPairs = new ArrayList<>();
    }

    public void start() throws FileNotFoundException {

        long nano = System.currentTimeMillis();
        // Read file
        BufferedReader reader = new BufferedReader(new FileReader("autok.txt"));

        Scanner scn = new Scanner(reader);

        while(scn.hasNext()) {
            String line = scn.nextLine();
            String[] args = line.split(" ");

            Car car = new Car(
                    Integer.parseInt(args[0]),
                    args[1],
                    args[2],
                    Integer.parseInt(args[3]),
                    Integer.parseInt(args[4]),
                    (Integer.parseInt(args[5]) == 1)
            );

            this.cars.add(car);
        }

        Map<String, CarPair> tempPairs = new HashMap<>();
        for (Car car : this.cars) {
            if (!car.isDeposit() && !tempPairs.containsKey(car.getPlate())) {
                tempPairs.put(car.getPlate(), new CarPair(car, null));
                continue;
            }

            if(car.isDeposit() && tempPairs.containsKey(car.getPlate())) {
                CarPair tempPair = tempPairs.get(car.getPlate());
                tempPair.carIn = car;
                tempPairs.remove(car.getPlate());
                this.carPairs.add(tempPair);
            }
        }

        this.carPairs.addAll(tempPairs.values());

        List<Feladat> tasks = List.of(
                new ElsoFeladat(),
                new MasodikFeladat(),
                new HarmadikFeladat(),
                new NegyedikFeladat(),
                new OtodikFeladat(),
                new HatodikFeladat()
        );
        for (Feladat task : tasks) {
            System.out.println(task.getNumber() + ". feladat");
            task.run();
        }

        System.out.println("Lefutott idő: " + (System.currentTimeMillis() - nano));
    }

    public static void main(String[] args) {
        try {
            instance = new Main();
            instance.start();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Getter
    @AllArgsConstructor
    public class CarPair {
        private Car carOut;
        private Car carIn;

        public int getDistanceTraveled() {
            if(carIn == null) return 0;
            return carIn.getKm() - carOut.getKm();
        }
    }
}
