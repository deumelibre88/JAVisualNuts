/**
 * 
 */
/**
 * @author joao_
 *
 */
public class Exercise1 {
	
	public static String visualnuts = "Visual Nuts";
	public static String visual = "Visual";
	public static String nuts = "Nuts";
	
	public static void main(String[] args) 
    {
        int nMax = 100;
        if(args[0]!= null) {
        	try {
        		nMax = Integer.parseInt(args[1]);
        	}
        	catch (NumberFormatException e) {
        		//do nothing, keep 100 as value and moveOn;
        		System.out.println(e);
        	}
        }
        System.out.println(nMax);
        
        for(int x = 1; x <= nMax; x++){
        	if(x %  3 == 0 && x %  5 == 0) {
        		System.out.println(visualnuts);
        	}
        	else if(x %  3 == 0) {
        		System.out.println(visual);
        	}else if(x %  5 == 0) {
        		System.out.println(nuts);
        	}else {
        		System.out.println(x);
        	}
        }
        
    } 
}