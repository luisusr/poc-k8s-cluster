

public class Primos {

	 public static void main(String[] args)
	            throws java.lang.InterruptedException {

	       


	        int number = 0;
	        int more_than_one_divisor = 0;
	        String mem_consumed [] =  new String[1024*1024*10];
	        int max_mem = Integer.valueOf(args[1]).intValue();
	        number=Integer.valueOf(args[0]).intValue();
	        for (int index=1; index < number; index++)
	        {
	        	mem_consumed =  new String[1024*1024*max_mem];
	                for (int second_index=1; second_index <= index; second_index++)
	                        {
	                         if ((index % second_index) == 0) more_than_one_divisor++;
	                        }
	                if (more_than_one_divisor <= 2) System.out.print("::"+index);
	         more_than_one_divisor = 0;
	        }
	        }

}

