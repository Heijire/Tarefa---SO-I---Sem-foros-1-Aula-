package view;

import java.util.concurrent.Semaphore;

import controller.threadaviao;

public class avioes {

	public static void main(String[] args) {
		int permissao = 1;
		Semaphore semaforo = new Semaphore(permissao);
		Semaphore total = new Semaphore(2);

		for( int i = 0; i  < 12; i++) {
			int x = (int)((Math.random()*2)+1);
				Thread aviao = new threadaviao(x, semaforo, total, i+1);
				aviao.start();
		}
	}

}
