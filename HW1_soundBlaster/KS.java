import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class KS {
	public static final int n = 200;
	public static final int m = 44100 * 5;
	public static final int sampleRate = 44100;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub		
		Queue<Double> Q1 = new LinkedList<Double>();
		Queue<Double> Q2 = new LinkedList<Double>();
		Random rand = new Random(42);
		double timestep = 1.0 / sampleRate;
		double time = timestep;
		for (int i = 0; i < n; i++) {
			double randomNumber = 2 * rand.nextDouble() - 1;
			Q1.add(randomNumber);
		}
		Q2.add(0.0);
		PrintWriter fileOut =
                new PrintWriter(new
				BufferedWriter(new FileWriter(args[0])));
		fileOut.println("; Sample Rate " + sampleRate);
		for (int i = 0; i < m; i++) {
			double a = Q1.remove(); 
			double b = Q2.remove(); 
			double c = 0.99 * (a + b) / 2; 
			Q1.add(c); 
			Q2.add(a); 
			fileOut.println(time + "\t" + c);
			time += timestep;
		}
		fileOut.close();
	}

}
