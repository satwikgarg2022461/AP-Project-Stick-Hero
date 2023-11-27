package com.example.stickhero;

import java.util.Random;

public class Random_generator {
	
	private final Random random = new Random();
	
	public double getRandomWidth() {
        // Adjust the range of width values as needed
        return random.nextDouble() * 30 + 80;
    }
	
	public double getRandomSpacing() {
        // Adjust the range of spacing values as needed
        return random.nextDouble() * 20 + 50;
    }

}
