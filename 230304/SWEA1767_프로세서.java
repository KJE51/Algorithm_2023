import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA1767 {
	static int[][] arr;
	static int n;
	static int coreNum;
	static int maxCoreNum;
	static int lineLen;
	// 코어 위치 저장할 리스트
	static ArrayList<Integer> coreI;
	static ArrayList<Integer> coreK;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int testCase = Integer.parseInt(str[0]) + 1;
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc < testCase; tc++) {
			sb.append("#").append(tc).append(" ");
			str = br.readLine().split(" ");
			n = Integer.parseInt(str[0]);
			// 맵
			arr = new int[n][n];
			// 충전 필요한 코어 좌표 저장, 벽에 붙어있는 건 저장하지 않음
			coreI = new ArrayList<>();
			coreK = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				str = br.readLine().split(" ");
				for (int k = 0; k < n; k++) {
					arr[i][k] = Integer.parseInt(str[k]);
					if (i > 0 && i < n && k < n && k > 0 && arr[i][k] == 1) {
						coreI.add(i);
						coreK.add(k);
					}
				}
			}

			// 연결해야할 코어의 수, 아웃풋으로 쓸 것들
			coreNum = coreI.size();
			maxCoreNum = 0;
			lineLen = 0;

			// 0번째 코어부터 탐색
			dfs(0, 0);
			sb.append(lineLen).append('\n');
		}
		System.out.println(sb);
	}

	// idx : 몇번째 코어인지, cnt: 지금까지 연결된 코어 수
	static void dfs(int idx, int cnt) {

		// 끝까지 다 탐색했을 때
		if (idx == coreNum) {
			// 코어 수랑 전선 수 갱신
			if (cnt < maxCoreNum) {
				return;
			}

			// 전선 수 세기
			int tmp = 0;
			for (int i = 0; i < n; i++) {
				for (int k = 0; k < n; k++) {
					if (arr[i][k] == 2)
						tmp++;
				}
			}

			// 현재의 코어 수가 많을때, 둘 다 갱신
			if (maxCoreNum < cnt) {
				maxCoreNum = cnt;
				lineLen = tmp;
				return;
			}

			// 같을때 - 전선 길이만 갱신
			lineLen = Math.min(lineLen, tmp);
			return;
		}

		// 백트래킹 : max랑 coreNum - idx, cnt 비교해서 최댓값 불가능할 시 return
		int re = coreNum - idx;
		if (re + cnt < maxCoreNum) {
			return;
		}

		// 사방탐색 - 중간에 연결되지 않아도 다음으로 보내긴 해야 함
		// 좌, 상, 우, 하 순서
		int[] di = { 0, -1, 0, +1 };
		int[] dk = { -1, 0, +1, 0 };
		boolean flag;
		int tmpI, tmpK, i = 0;
		int nowI = coreI.get(idx);
		int nowK = coreK.get(idx);
		// 새로 연결한 전선 위치 저장할 큐 2개
		Queue<Integer> tmpIs = new LinkedList<Integer>();
		Queue<Integer> tmpKs = new LinkedList<Integer>();

		for (int d = 0; d < 4; d++) {
			flag = true;
			i = 1;
			// flag 통해서 사방탐색 방향으로 쭉 가기... 범위까지 갈 수 있는지 확인(채우면서)
			while (flag) {
				tmpI = nowI + di[d] * i;
				tmpK = nowK + dk[d] * i;
				// 끝까지 가면 break;
				if (tmpI == -1 || tmpI == n || tmpK == -1 || tmpK == n) {
					flag = false;
					break;
				}

				// 빈칸일 때
				if (arr[tmpI][tmpK] == 0) {
					arr[tmpI][tmpK] = 2;
					tmpIs.add(tmpI);
					tmpKs.add(tmpK);
				}
				// 빈칸이 아닐 때 - 가지 못함, break
				else {
					break;
				}
				i++;
			}

			// 전선을 연결할 수 있었을 때
			if (!flag) {
				dfs(idx + 1, cnt + 1);
			}

			// 되돌리기
			while (!tmpIs.isEmpty()) {
				arr[tmpIs.poll()][tmpKs.poll()] = 0;
			}

			// 연결 안했다고 가정하고
			dfs(idx + 1, cnt);
		}
	}

	// 디버깅용 출력
	private static void print(String str) {
		System.out.println(str);
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				System.out.print(arr[i][k] + " ");
			}
			System.out.println();
		}
	}
}
