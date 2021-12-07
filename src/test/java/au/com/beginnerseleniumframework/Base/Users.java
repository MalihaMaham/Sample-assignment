package au.com.beginnerseleniumframework.Base;

public class Users {
    private String[] customer = new String[2];
    private String[] agent = new String[2];
    private String[] admin = new String[2];
    private String[] supplier = new String[2];

    // TODO: make this get all four instead of an array


    public void setCustomer(String email, String password) {
        String[] customer = { email, password };
        this.customer = customer;
    }

    public void setAgent(String email, String password) {
        String[] agent = { email, password };
        this.agent = agent;
    }

    public void setAdmin(String email, String password) {
        String[] admin = { email, password };
        this.admin = admin;
    }

    public void setSupplier(String email, String password) {
        String[] supplier = { email, password };
        this.supplier = supplier;
    }

    public String[] getCustomer() {
        return customer;
    }

    public String[] getAgent() {
        return agent;
    }

    public String[] getAdmin() {
        return admin;
    }

    public String[] getSupplier() {
        return supplier;
    }
}
