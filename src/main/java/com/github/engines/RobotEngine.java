package com.github.engines;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RobotEngine {

	private String[] lines;
	private int permutations = 0;
	private int counter = 0;
	private Point center = new Point(0, 0);

	public void load(String steps, String verification) throws IOException {
		InputStream inSteps = getClass().getClassLoader().getResourceAsStream(steps);
		InputStreamReader isrSteps = new InputStreamReader(inSteps);
		BufferedReader brSteps = new BufferedReader(isrSteps);

		String input = brSteps.readLine();
		if (input != null && !input.isBlank()) {
			String[] command = input.split(" ");
			int lineCount = Integer.parseInt(command[0]);
			int lengthOfLine = Integer.parseInt(command[1]);

			lines = new String[lineCount];
			for (int i = 0; i < lineCount; i++) {
				input = brSteps.readLine();
				lines[i] = input;
			}
		}

		InputStream inVerification = getClass().getClassLoader().getResourceAsStream(verification);
		InputStreamReader isrVerification = new InputStreamReader(inVerification);
		BufferedReader brVerification = new BufferedReader(isrVerification);
		permutations = Integer.parseInt(brVerification.readLine());
	}

	public void run() throws Exception {
		recurse(lines);
		System.out.println(String.format("Expected: %d, found: %d", permutations, counter));
	}

    public <T> void recurse(T[] elements) {
    	recurse(elements.length, elements);
    }

    public <T> void recurse(int n, T[] elements) {
        if (n == 1) {
        	Point point = computeEndPoint(concatenate(elements));
        	if (point.equals(center)) counter++;
        } else {
            for(int i = 0; i < n-1; i++) {
            	recurse(n - 1, elements);
                if(n % 2 == 0) {
                    swap(elements, i, n-1);
                } else {
                    swap(elements, 0, n-1);
                }
            }
            recurse(n - 1, elements);
        }
    }

    private <T> void swap(T[] elements, int a, int b) {
        T tmp = elements[a];
        elements[a] = elements[b];
        elements[b] = tmp;
    }

    private <T> String concatenate(T[] elements) {
    	String result = "";
        for(int i = 0; i < elements.length; i++) {
            result = result + elements[i];
        }
        
        return result;
    }
    
	private Point computeEndPoint(String line) {
		int x = 0;
		int y = 0;
		Direction direction = Direction.NORTH;
		for (int i = 0; i < line.length(); i++) {
			switch (line.charAt(i)) {
			case 'P':
				switch (direction) {
				case NORTH:
					y--;
					break;
				case EAST:
					x++;
					break;
				case SOUTH:
					y++;
					break;
				case WEST:
					x--;
					break;
				}
				break;
			case 'L':
				switch (direction) {
				case NORTH:
					direction = Direction.WEST;
					break;
				case EAST:
					direction = Direction.NORTH;
					break;
				case SOUTH:
					direction = Direction.EAST;
					break;
				case WEST:
					direction = Direction.SOUTH;
					break;
				}
				break;
			case 'D':
				switch (direction) {
				case NORTH:
					direction = Direction.EAST;
					break;
				case EAST:
					direction = Direction.SOUTH;
					break;
				case SOUTH:
					direction = Direction.WEST;
					break;
				case WEST:
					direction = Direction.NORTH;
					break;
				}
				break;
			}
		}
		return new Point(x, y);
	}

	enum Direction {
		NORTH, EAST, SOUTH, WEST
	}

}

class Point {

	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point(" + x + ", " + y + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		return x == other.x && y == other.y;
	}

}