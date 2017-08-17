package in.artist.util;

public class ConnectionRetryManager {

	public static final int DEFAULT_RETRIES = 1;
	public static final long DEFAULT_WAIT_TIME_IN_MILLI = 2000;

	private int numberOfRetries;
	private int numberOfTriesLeft;
	private long timeToWait;

	public ConnectionRetryManager() {
		this(DEFAULT_RETRIES, DEFAULT_WAIT_TIME_IN_MILLI);
	}

	public ConnectionRetryManager(int type) {
		this.numberOfRetries = 1;
		this.numberOfTriesLeft = 1;
	}

	public ConnectionRetryManager(int numberOfRetries, long timeToWait) {
		this.numberOfRetries = numberOfRetries;
		numberOfTriesLeft = numberOfRetries;
		this.timeToWait = timeToWait;
	}

	/**
	 * @return true if there are tries left
	 */
	public boolean shouldRetry() {
		return numberOfTriesLeft > 0;
	}

	public void errorOccured() {
		numberOfTriesLeft--;
		waitUntilNextTry();
	}

	public long getTimeToWait() {
		return timeToWait;
	}

	private void waitUntilNextTry() {
		try {
			Thread.sleep(getTimeToWait());
		} catch (InterruptedException ignored) {
		}
	}
}