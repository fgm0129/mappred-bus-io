package mappred.bus.io.perf;

import mappred.bus.io.core.MappedBusWriter;

import java.io.File;

/**
 * @author fgm
 * @date 2018/5/21
 * @description
 */
public class MessageWriter {

    public static void main(String[] args) {
        MessageWriter writer = new MessageWriter();
        writer.run(args[0]);
    }

    public void run(String fileName) {
        try {
            new File(fileName).delete();

            MappedBusWriter writer = new MappedBusWriter(fileName, 20000000000L, 12, false);
            writer.open();

            PriceUpdate priceUpdate = new PriceUpdate();

            for (int i = 0; i < 80000000; i++) {
                writer.write(priceUpdate);
            }

            System.out.println("Done");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
