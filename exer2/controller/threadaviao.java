package controller;

import java.util.concurrent.Semaphore;

public class threadaviao extends Thread {
	private Semaphore sul;
	private int aviao;
	private int pista;
	private Semaphore norte;
	private String nome;
	private Semaphore total;

	public threadaviao(int pista, Semaphore semaforo, Semaphore total, int aviao) {
		this.pista = pista;
		this.aviao = aviao;
		this.norte = semaforo;
		this.sul = semaforo;
		this.total = total;
	}

	@Override
	public void run() {
		try {
			total.acquire();
			if (pista == 1) {
				nome = "Norte";
				norte.acquire();
				manobrar();
				taxiar();
				decocalr();
				afastamento();
			} else {
				nome = "Sul";
				sul.acquire();
				manobrar();
				taxiar();
				decocalr();
				afastamento();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			norte.release();
			sul.release();
			total.release();
		}

		super.run();
	}

	private void manobrar() {
		int tempo = (int) (Math.random() * 401) + 300;
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("O aviao de numero " + aviao + " esta manobrando na pista " + nome);
	}

	private void taxiar() {
		int tempo = (int) (Math.random() * 501) + 500;
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("O aviao de numero " + aviao + " esta taxiando na pista " + nome);

	}

	private void decocalr() {
		int tempo = (int) (Math.random() * 201) + 600;
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("O aviao de numero " + aviao + " esta decolando na pista " + nome);
	}

	private void afastamento() {
		int tempo = (int) (Math.random() * 501) + 300;
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("O aviao de numero " + aviao + " esta afastando da pista " + nome);
	}
}
