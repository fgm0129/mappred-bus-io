package mappred.bus.io.sample.bytearray;

import mappred.bus.io.core.MappedBusReader;

import java.util.Arrays;

public class ByteArrayReader {

	public static void main(String[] args) {
		ByteArrayReader reader = new ByteArrayReader();
		reader.run();	
	}

	public void run() {
		try {
			MappedBusReader reader = new MappedBusReader("/tmp/test-bytearray", 2000000L, 10);
			reader.open();

			byte[] buffer = new byte[10];

			int count=0;
			while (true) {
				if(count>3){
					System.out.println("没有数据,休眠3秒");
					Thread.sleep(3000);
				}
				count++;
				if (reader.next()) {
					count=0;
					int length = reader.readBuffer(buffer, 0);
					System.out.println("Read: length = " + length + ", data= "+ Arrays.toString(buffer));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}