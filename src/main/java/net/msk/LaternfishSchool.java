package net.msk;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LaternfishSchool {
    final List<Laternfish> school = new ArrayList<>();

    public LaternfishSchool(final int[] spawnValues) {
        for(final int spawnValue : spawnValues) {
            this.school.add(new Laternfish(spawnValue));
        }

        System.out.print("Day 0: ");
        this.school.forEach(fish -> System.out.print(fish.getSpawnTimer() + ","));
        System.out.println();
    }

    public void spawnNewFishes(final int count) {
        IntStream.range(0, count).forEach(i -> this.school.add(new Laternfish(8)));
    }

    public int addDays(final int days) {
        for(int i=0; i<days; i++) {
            final long countOfNewFishesToSpawn = this.school.stream().filter(Laternfish::isSpawnNewFishAfterAddingDay).count();
            this.spawnNewFishes((int) countOfNewFishesToSpawn);
            /*
            System.out.print("Day " + (i+1) + ": ");
            this.school.forEach(fish -> System.out.print(fish.getSpawnTimer() + ","));
            System.out.println();
            */
        };

        return this.school.size();
    }

    private class Laternfish {
        private int spawnTimer;

        public Laternfish(final int spawnTimer) {
            this.spawnTimer = spawnTimer;
        }

        public boolean isSpawnNewFishAfterAddingDay() {
            this.spawnTimer--;
            if(this.spawnTimer < 0) {
                this.spawnTimer = 6;
                return true;
            }
            return false;
        }

        public int getSpawnTimer() {
            return this.spawnTimer;
        }
    }
}
