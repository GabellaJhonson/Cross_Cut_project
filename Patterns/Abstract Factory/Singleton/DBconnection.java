public class DBConnection {
    public static volatile DBConnection primary_key;
    private static final Object mutex = new Object();


    private DBConnection() {
    }

    public static DBConnection Initialise_PK() {
        DBConnection to_return = primary_key;

        if (to_return == null) {
            synchronized (mutex) {
                to_return = primary_key;
                if (to_return == null) {
                    primary_key = to_return = new DBConnection();
                }
            }

        }
        return to_return;
    }

}
