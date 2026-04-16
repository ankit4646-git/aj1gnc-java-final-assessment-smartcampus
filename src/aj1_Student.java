import java.io.Serializable;

class aj1_Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private int aj1_id;
    private String aj1_fullName;
    private String aj1_contactEmail;

    public aj1_Student(int aj1_id, String aj1_fullName, String aj1_contactEmail) {
        this.aj1_id = aj1_id;
        this.aj1_fullName = aj1_fullName;
        this.aj1_contactEmail = aj1_contactEmail;
    }

    public int getAj1_id() { 
        return aj1_id; 
    }
    public String getAj1_fullName() { 
        return aj1_fullName; 
    }

    @Override
    public String toString() {
        return "ID: " + aj1_id + " | Name: " + aj1_fullName + " | Email: " + aj1_contactEmail;
    }
}

class aj1_Course implements Serializable {
    private static final long serialVersionUID = 1L;
    private int aj1_code;
    private String aj1_title;
    private double aj1_price;

    public aj1_Course(int aj1_code, String aj1_title, double aj1_price) {
        this.aj1_code = aj1_code;
        this.aj1_title = aj1_title;
        this.aj1_price = aj1_price;
    }

    public int getAj1_code() { 
        return aj1_code; 
    }
    public String getAj1_title() { 
        return aj1_title; 
    }

    @Override
    public String toString() {
        return "Code: " + aj1_code + " | Title: " + aj1_title + " | Fee: Rs." + aj1_price;
    }
}