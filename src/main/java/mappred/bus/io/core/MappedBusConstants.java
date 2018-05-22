package mappred.bus.io.core;

/**
 * @author fgm
 * @date 2018/5/21
 * @description
 */
public class MappedBusConstants {

   public  static class Structure {

        public static final int Limit = 0;

        public static final int Data = Length.Limit;

    }

    public static class Length {

        public static final int Limit = 8;

        public static final int Commit = 1;

        public static final int Rollback = 1;

        public static final int Metadata = 4;

        public static final int StatusFlags = Commit + Rollback;

        public static final int RecordHeader = Commit + Rollback + Metadata;

    }

    public static class Commit {

        public static final byte NotSet = 0;

        public static final byte Set = 1;

    }

    public static class Rollback {

        public static final byte NotSet = 0;

        public static final byte Set = 1;

    }
}
