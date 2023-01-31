import java.util.Scanner;
//2231 분해합
public class AddSum {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String str = Integer.toString(n);
		
		int sum, tmp;
		
		//len : 자릿수
		//자릿수 *10
		int len = str.length();
		int start = n - len * 9;
		if(start<1)
			start = 1;
		
		int result = 0;
		for(int i = start; i<n;i++) {
			sum = i;
			tmp = i;
			while(tmp>0) {
				sum = sum + (tmp%10);
				tmp = tmp / 10;
			}
			if(sum == n) {
				result = i;
				break;
			}
		}
		System.out.println(result);
		
	}

}
