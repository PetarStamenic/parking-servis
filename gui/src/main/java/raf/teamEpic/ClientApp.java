package raf.teamEpic;

public class ClientApp {
    private String token;

    private ClientApp()throws IllegalAccessException, NoSuchMethodException{}

    private static class InstanceHolder {
        private static ClientApp instance;

        static {
            try {
                instance = new ClientApp();
            } catch (IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static ClientApp getInstance() {
        return InstanceHolder.instance;
    }
}
