import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2931 {
	static int sizeI;
	static int sizeK;
	static int startI, startK, endI, endK, blankI = 0, blankK = 0;
	static int[][] arr;
	static boolean[][] visited;
	static int[] di = { 0, 0, 1, 0, -1, 0, 0, -1, 0, +1, -1, +1, 0, 0 };
	static int[] dk = { 0, 0, 0, +1, 0, +1, -1, 0, -1, 0, 0, 0, -1, +1 };
	// 상하좌우
	static int[] di4 = { -1, +1, 0, 0 };
	static int[] dk4 = { 0, 0, -1, +1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		sizeI = Integer.parseInt(str[0]);
		sizeK = Integer.parseInt(str[1]);
		arr = new int[sizeI + 2][sizeK + 2];
		visited = new boolean[sizeI + 2][sizeK + 2];

		for (int i = 1; i <= sizeI; i++) {
			str[0] = br.readLine();
			for (int k = 1; k <= sizeK; k++) {
				switch (str[0].charAt(k - 1)) {
				case '1':
					arr[i][k] = 1;
					break;
				case '2':
					arr[i][k] = 2;
					break;
				case '3':
					arr[i][k] = 3;
					break;
				case '4':
					arr[i][k] = 4;
					break;
				case '|':
					arr[i][k] = 5;
					break;
				case '-':
					arr[i][k] = 6;
					break;
				case '+':
					arr[i][k] = 7;
					break;
				case 'Z':
					arr[i][k] = -1;
					endI = i;
					endK = k;
					break;
				case 'M':
					arr[i][k] = -1;
					startI = i;
					startK = k;
					break;
				}
			}
		}
		int tmpi, tmpk;
		tmpi = 0;
		tmpk = 0;
		for (int i = 0; i < 4; i++) {
			tmpi = startI + di4[i];
			tmpk = startK + dk4[i];
			if (arr[tmpi][tmpk] > 0) {
				break;
			}
		}
		visited[startI][startK] = visited[endI][endK] = visited[tmpi][tmpk] = true;
		dfs(tmpi, tmpk);
		int blankChar = foundChar(blankI, blankK);
		String chars = "01234|-+";
		System.out.println(blankI + " " + blankK + " " + chars.charAt(blankChar));
	}

	private static int foundChar(int nowI, int nowK) {
		int[] test = { 0, 0, 0, 0 };
		int cnt = 0;
		int i, k;
		//
		for (int d = 0; d < 4; d++) {
			i = arr[nowI + di4[d]][nowK + dk4[d]];
			
			if (i > 0) {
				if(d == 0) {
					if(i == 5 || i == 7 || i == 4 || i == 1) {
						test[d] = 1;
						cnt++;
					}
				}
				else if(d == 1) {
					if(i == 7 || i == 2 || i == 3 || i == 5) {
						test[d] = 1;
						cnt++;
					}
				}
				else if(d == 2) {
					if(i == 6 || i == 1 || i == 2 || i == 7) {
						test[d] = 1;
						cnt++;
					}
				}
				else if(d == 3) {
					if(i == 6 || i == 7 || i == 3 || i == 4) {
						test[d] = 1;
						cnt++;
					}
				}
				
			}
		}
		if (cnt == 4)
			return 7;

		// ㅣ, -, 상 하 조건 체크
		if (test[1] == test[0]) {
			//
			if (test[0] == 1) {
				return 5;
			}
			return 6;
		}

		// 상, 오
		if (test[0] == test[3]) {
			if (test[0] == 1)
				return 2;
			return 4;
		}

		if (test[0] == 1)
			return 3;
		return 1;

	}

	private static void dfs(int nowI, int nowK) {
		if (blankI != 0)
			return;

		int newI, newK;

		// -1일때 그냥 넘기기
		// 0일때 -> 빈칸발견
		// 나머지 자연수일떄 -> 다음탐색
		// 8자
		if (arr[nowI][nowK] == 7) {
			// 사방탐색
			for (int d = 0; d < 4; d++) {
				newI = nowI + di4[d];
				newK = nowK + dk4[d];

				// 다음이 빈칸일 때
				if (arr[newI][newK] == 0) {
					blankI = newI;
					blankK = newK;
					return;
				}

				// 자연수일 때 : 다음꺼랑 이어지는지 탐색
				if (arr[newI][newK] > 0) {
					visited[newI][newK] = true;
					dfs(newI, newK);
					visited[newI][newK] = false;
				}
			}
			return;
		}

		if (arr[nowI][nowK] > 0) {
			for (int d = 0; d < 2; d++) {
				newI = nowI + di[arr[nowI][nowK] * 2 + d];
				newK = nowK + dk[arr[nowI][nowK] * 2 + d];

				if (visited[newI][newK])
					continue;

				// 갈 곳이 없을 때
				if (arr[newI][newK] == 0) {
					blankI = newI;
					blankK = newK;
					return;
				}

				// 갈 곳 있을 때
				visited[newI][newK] = true;
				dfs(newI, newK);
				visited[newI][newK] = false;
			}
		}

	}
}