package com.example.stickhero;

import java.util.Random;

public class Random_generator {
	
	private final Random random = new Random();
	
	public double getRandomWidth() {
        // Adjust the range of width values as needed
        return random.nextDouble() * 50 + 50; // Random width between 50 and 200
    }
	
	public double getRandomSpacing() {
        // Adjust the range of spacing values as needed
        return random.nextDouble() * 50 + 10; // Random spacing between 10 and 60
    }

}
