package bzb.se.geo.pusher;

public class GEOBridge {

	public static void main(String[] args) {
		if (args.length > 0) {
			int hubId = Integer.parseInt(args[0]);
			while (true) {
				new Thread(new Pusher(hubId)).start();
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("No args");
		}
	}

}
