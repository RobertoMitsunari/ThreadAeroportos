package view;

import java.util.concurrent.Semaphore;

import controller.ThreadAeroporto;

public class Principal {

	public static void main(String[] args) {
		Semaphore semaforo1 = new Semaphore(1);
		Semaphore semaforo2 = new Semaphore(1);
		String[] siglas = { "PP-PQP", "PR-PAB", "PP-APB", "PS-ABD", "PP-ABC", "PP-PAN", "PS-MAB", "PR-EAG", "PP-PPB",
				"OS-OBL", "PS,PTL", "PR-JCB" };

		Thread av;
		for (int i = 0; i < 12; i++) {
			av = new ThreadAeroporto(semaforo1, semaforo2, siglas[i]);
			av.start();
		}

	}

}
