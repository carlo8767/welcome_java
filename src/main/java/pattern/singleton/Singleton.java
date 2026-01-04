package pattern.singleton;

public final class Singleton {



    private static Singleton SINGLETON;

        private Singleton(){

        }

        public static synchronized Singleton  createSingleton (){

            if (SINGLETON == null) {
                SINGLETON = new Singleton();
            }

            return  SINGLETON;
        }

    public static synchronized void reset() {
        SINGLETON = null;
    }




        static void main (String [] args) {
            Singleton s  = Singleton.createSingleton();
            Singleton d  = Singleton.createSingleton();
            Singleton.reset();
            Singleton a  = Singleton.createSingleton();

        }
}
