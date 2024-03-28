package view;

import java.util.concurrent.Semaphore;

import controller.threadcavaleiro;

public class cavaleiro {

	public static void main(String[] args) {
		int permissao = 1;
		Semaphore semaforo = new Semaphore(permissao);
		Semaphore porta1 = new Semaphore(4);

		for(int i = 0; i< 4 ; i++) {
			Thread thread = new threadcavaleiro(i, semaforo, porta1);
			thread.start();
		}
		
	}

}
