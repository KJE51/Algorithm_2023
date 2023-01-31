import java.util.Scanner;

//14888 연산자 끼워넣기

public class OperatorAdd {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		//수 저장
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		//연산자 저장	+ - * /
		int[] operators = new int[4];
		for(int i = 0; i < 4; i++) {
			operators[i] = sc.nextInt();
		}
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int tmp = 0;
		
		
	}

}
