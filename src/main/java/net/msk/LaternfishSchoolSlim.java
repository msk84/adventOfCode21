package net.msk;

import java.util.Arrays;

public class LaternfishSchoolSlim {
    int[] school;

    public LaternfishSchoolSlim(final int[] fishes){
        this.school = fishes;
    }

    public int addDays(final int days) {
        for(int d=0; d<days; d++) {
            System.out.println("Day " + d);
            int newFishes = 0;
            for(int i=0; i<this.school.length; i++) {
                int daysTilSpawn = --this.school[i];
                if(daysTilSpawn < 0) {
                    newFishes++;
                    this.school[i] = 6;
                }
            }

            this.addNewFishes(newFishes);
        }

        return this.school.length;
    }

    private void addNewFishes(final int numberOfNewFishes) {
        final int oldLength = this.school.length;
        if(numberOfNewFishes > 0) {
            /*
            System.arraycopy(this.school, 0, this.school, );
            this.school = Arrays.copyOf(this.school, oldLength + numberOfNewFishes);
            for (int i = 0; i < numberOfNewFishes; i++) {
                this.school[oldLength + i] = 8;
            }
            */
        }
    }
}
