// Import the necessary Java synchronization and scheduling classes.

import java.util.concurrent.CountDownLatch;

/**
 * @class PingPongRight
 * 
 * @brief This class implements a Java program that creates two instances of the
 *        PlayPingPongThread and start these thread instances to correctly
 *        alternate printing "Ping" and "Pong", respectively, on the console
 *        display.
 */
public class PingPongRight {
	/**
	 * Number of iterations to run the test program.
	 */
	public static int mMaxIterations = 10;

	/**
	 * Latch that will be decremented each time a thread exits.
	 */
	public static CountDownLatch latch = null; // TODO - You fill in here

	/**
	 * @class PlayPingPongThread
	 * 
	 * @brief This class implements the ping/pong processing algorithm using the
	 *        SimpleSemaphore to alternate printing "ping" and "pong" to the
	 *        console display.
	 */
	public static class PlayPingPongThread extends Thread {
		String printName;
		SimpleSemaphore s1;
		SimpleSemaphore s2;

		/**
		 * Constructor initializes the data member.
		 */
		public PlayPingPongThread(SimpleSemaphore ps1, SimpleSemaphore ps2,
				String name) {
			// TODO - You fill in here.
			printName = name;
			s1 = ps1;
			s2 = ps2;
		}

		/**
		 * Main event loop that runs in a separate thread of control and
		 * performs the ping/pong algorithm using the SimpleSemaphores.
		 */
		public void run() {
			// TODO - You fill in here.

			for (int i = 0; i <= mMaxIterations; i++) {
				try {
					s1.acquire();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("****** " + "Ping");
				try {
					s2.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("****** " + "Pong");
				s2.release();
				s1.release();

				latch.countDown();
			}

		}

		/**
		 * String to print (either "ping!" or "pong"!) for each iteration.
		 */
		// TODO - You fill in here.

		/**
		 * The two SimpleSemaphores use to alternate pings and pongs.
		 */

		// TODO - You fill in here.
	}

	/**
	 * The main() entry point method into PingPongRight program.
	 */
	public static void main(String[] args) {
		try {
			// Create the ping and pong SimpleSemaphores that control
			// alternation between threads.

			SimpleSemaphore s1 = new SimpleSemaphore(1, true);
			SimpleSemaphore s2 = new SimpleSemaphore(1, true);
			latch = new CountDownLatch(mMaxIterations);

			// TODO - You fill in here.

			System.out.println("Ready...Set...Go!");

			// Create the ping and pong threads, passing in the string
			// to print and the appropriate SimpleSemaphores.
			PlayPingPongThread ping = new PlayPingPongThread(s1, s2, "Ping");
			PlayPingPongThread pong = new PlayPingPongThread(s1, s2, "Pong");

			// Initiate the ping and pong threads, which will call the
			// run() hook method.
			ping.start();
			pong.start();

			// Use barrier synchronization to wait for both threads to
			// finish.

			// TODO - replace replace the following line with a
			// CountDownLatch barrier synchronizer call that waits for
			// both threads to finish.
			latch.await();
			throw new java.lang.InterruptedException();
		} catch (java.lang.InterruptedException e) {
			System.out.println("e" + e);
		}

		System.out.println("Done!");
	}
}
