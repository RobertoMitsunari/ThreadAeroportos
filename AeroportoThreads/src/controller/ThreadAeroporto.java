package controller;

import java.util.concurrent.Semaphore;

public class ThreadAeroporto extends Thread{
	
	private Semaphore semaforoNorte;
	private Semaphore semaforoSul;
	private String idAviao;
	private int direcao;
	
	
	
	public ThreadAeroporto(Semaphore semaforoNorte,Semaphore semaforoSul, String idAviao) {
		super();
		this.semaforoNorte = semaforoNorte;
		this.semaforoSul = semaforoSul;
		this.idAviao = idAviao;
		this.direcao = (int) ((Math.random() * 2) + 1);
		
		
	}
	
	private void pistaNorte() {
		decolagem("Norte");
	}
	
	private void pistaSul() {
		decolagem("Sul");
	}
	
	private void decolagem(String pista) {
		int manobra = (int) ((Math.random() * 5) + 3) * 1000;
		int taxiar = (int) ((Math.random() * 6) + 5) * 1000;
		int decolagem = (int) ((Math.random() * 4) + 1) * 1000;
		int afastamento = (int) ((Math.random() * 6) + 3) * 1000;
		
		try {
			System.out.println("Avião: " + idAviao + " está manobrando na pista " + pista);
			sleep(manobra);
			System.out.println("Avião: " + idAviao + " está taxiando na pista " + pista);
			sleep(taxiar);
			System.out.println("Avião: " + idAviao + " está decolando na pista "+ pista);
			sleep(decolagem);
			System.out.println("Avião: " + idAviao + " está se afastando da pista "+ pista);
			sleep(afastamento);
			System.out.println("Avião: " + idAviao + " decolou e se afastou da pista "+ pista);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}



	@Override
	public void run() {
		if(direcao == 1) {
			try {
				semaforoNorte.acquire();
				decolagem("Norte");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				semaforoNorte.release();
			}
		}else if (direcao == 2) {
			try {
				semaforoSul.acquire();
				decolagem("Sul");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				semaforoSul.release();
			}
		}
		
		super.run();
	}

}
