import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//백준 2116 주사위 쌓기
public class Dice {
	static int n;
	static int[][] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		n = Integer.parseInt(input[0]);
		arr = new int[n][6];
		for (int i = 0; i < n; i++) {
			String[] temp = br.readLine().split(" ");
			for (int k = 0; k < 6; k++) {
				arr[i][k] = Integer.parseInt(temp[k]);
			}
		}

		int max = -1;
		// 6번 다 해야함
		for (int i = 1; i < 7; i++) {
			max = Math.max(func(i), max);
		}
		System.out.println(max);
	}

	// 시작점 주기 - 값
	static int func(int bottom) {
		int result = 0;

		// 값으로 찾기
		// start : 바닥에 와야하는 값
		for (int i = 0; i < n; i++) {
			// 1~6까지 값 저장하는
			boolean[] diceValue = new boolean[7];

			// 인덱스 저장
			int idxB = 0;
			int idxT = 0;
			for (int k = 0; k < 6; k++) {
				if (arr[i][k] == bottom) {
					idxB = k;
					break;
				}
			}

			switch (idxB) {
			case 0:
				idxT = 5;
				break;
			case 5:
				idxT = 0;
				break;
			case 1:
				idxT = 3;
				break;
			case 3:
				idxT = 1;
				break;
			case 2:
				idxT = 4;
				break;
			case 4:
				idxT = 2;
				break;
			}
			// 반대쪽의 idx 찾고 값 저장
			diceValue[bottom] = true;
			bottom = arr[i][idxT];
			diceValue[bottom] = true;

			for (int k = 6; k > 0; k--) {
				if (diceValue[k] == false) {
					result += k;
					break;
				}
			}

		}
		return result;
	}

}
