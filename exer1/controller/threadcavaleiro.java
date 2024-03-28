package controller;

import java.util.concurrent.Semaphore;

public class threadcavaleiro extends Thread {
			private int id;
			private Semaphore tocha;
			private static int n;
			private static int n1;
			private Semaphore portas;
			private static int portacerta = (int)((Math.random()*4)+1);

			public threadcavaleiro(int id, Semaphore tocha, Semaphore portas) {
				this.id = id;
				this.tocha = tocha;
				this.portas = portas;
			}

			@Override
			public void run() {
				andando();
				terminou();
				super.run();
			}

			private void andando() {
				int tempo = 50;
				int andar = 0;
				int dispercorrida = 0;
				int boost = 0;
				int teste = 0;
				while (dispercorrida < 2000) {
					andar = (int) ((Math.random() * 3) + 2);
					try {
						sleep(tempo);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					dispercorrida += andar + boost;
					teste = andar + boost;

					if (dispercorrida >= 500 && n < 1) {
						n++;
						try {
							tocha.acquire();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						boost= boost + tocha();
						System.err.println("Thread[#" + id + "] pegou a tocha");
					}
					if (dispercorrida >= 1500 && n1 < 1) {
						n1++;
						boost = boost + tocha();
						System.err.println("Thread[#" + id + "] pegou a pedra estranha");
					}
					System.out.println("Thread[#" + id + "] andou" + dispercorrida + "ms." + "     " + andar + "      " + boost
							+ "         " + teste);
				}
				tocha.release();
			}
			private int tocha() {
				int v = 0;
				v = v + 2;
				return v;
			}
			
			private void terminou() {
				int portaescolhida;
				try {
					sleep(1000 * id);
					portaescolhida = (int) ((Math.random()*4)+1);
					
					portas.acquire();

					
					if(portaescolhida == portacerta) {
						System.err.println("O cavaleiro " + (id+1) + " escolheu a porta"+ portaescolhida + " conseguiu escapar.");
					}else {
						System.err.println("O cavaleiro " + (id+1) + " escolheu a porta " + portaescolhida + " foi devorado por um monstro.");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();	
				} finally {
					
					
				}
			System.out.println(portacerta);

			}
}
