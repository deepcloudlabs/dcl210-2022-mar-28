package com.example.javase7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class NewFeatures {

	public static void main(String[] args) {
		int x = 1_000_000;
		double y = 1_234_567.123_456;
		int z = 123; // decimal
		z = 01234_3456; // octal
		z = 0xa1c8_2bd3; // hexadecimal
		z = 0b1110_0110; // New: binary
        List<List<Integer>> lotteryNumbers = new ArrayList<>(); // Diamond Operator 
        lotteryNumbers.add(List.of(1,2,3,4,5,6));
        lotteryNumbers.add(List.of(4,8,15,16,23,42));
        System.out.println(lotteryNumbers);
        // Before Java SE 7
        /*
        {
        	PreciousResource res1 = new PreciousResource(1);
        	PreciousResource res2 = new PreciousResource(2);
        	PreciousResource res3 = new PreciousResource(3);
        	try {
        		// work with the resources
        	} catch (Exception e) {
				System.err.println("An error has occured: "+e.getMessage());
			} finally {
				try {
					res1.close();
				} catch (IOException e) {
					System.err.println("An error has occured: "+e.getMessage());
				} finally {
					try {
						res2.close();
					} catch (IOException e) {
						System.err.println("An error has occured: "+e.getMessage());
					} finally {
						try {
							res3.close();
						} catch (IOException e) {
							System.err.println("An error has occured: "+e.getMessage());
						}											
					}
				}
			}
        }
        */
        // After Java SE 7: try-with-resources
        {
        	
        	try(PreciousResource res4 = new PreciousResource(1)){
        		try(
        				PreciousResource res1 = new PreciousResource(1);
        				PreciousResource res2 = new PreciousResource(2);
        				PreciousResource res3 = new PreciousResource(3);
        				) {
        			// work with the resources
        		} catch (Exception e) {
        			System.err.println("An error has occured: "+e.getMessage());
        			for (var otherException : e.getSuppressed()) {
        				System.err.println("An error has occured: "+otherException.getMessage());
        			}
        		} finally {
				   System.out.println(res4.getId());
				} 
        	    // res1, res2, res3 are not available
        		// res4 is available  
        	} catch (Exception e) {
			}
        }
        // switch
        Integer m = 42; // auto-boxing
        Integer n = 108; // auto-boxing
        Integer u = Integer.valueOf(m.intValue() + n.intValue());
        switch(m.intValue()) { // auto-unboxing
	        case 42:
	        	
	        }
	    }
	    

}

class PreciousResource implements AutoCloseable {

	private final int id;

	public PreciousResource(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public void close() throws IOException {
		System.err.println("Closing the precious resource %d...".formatted(id));
		throw new IllegalStateException("Cannot close the resource %d!".formatted(id));
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}
	
}
