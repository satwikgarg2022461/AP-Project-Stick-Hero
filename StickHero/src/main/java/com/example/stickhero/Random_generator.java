package com.example.stickhero;

import java.util.Random;

public class Random_generator {
	
	private final Random random = new Random();
	
	public double getRandomWidth() {
        // Adjust the range of width values as needed
        if(random.nextInt()%2 ==0)
        {
            double baseWidth = random.nextDouble() * 38 + 80;
            double randomOffset = random.nextDouble() * 10;
            return baseWidth + randomOffset;
        }
        double baseWidth = random.nextDouble() * 38 + 40;
        double randomOffset = random.nextDouble() * 10;
        return baseWidth + randomOffset;
    }
	
	public double getRandomSpacing() {
        // Adjust the range of spacing values as needed
        return random.nextDouble() * 100 + 50;
    }

    public double generateRandomDouble(double minValue, double maxValue) {
        if (minValue >= maxValue) {
            throw new IllegalArgumentException("minValue must be less than maxValue");
        }

        Random random = new Random();
        return minValue + (random.nextDouble() * (maxValue - minValue));
    }

    public int randomInt()
    {
        return random.nextInt(10);
    }

}
