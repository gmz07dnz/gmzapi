package pojos;

public class TodosPojo {

    /*
    REQUEST BODY
      {"userId": 21,
        "id":201,
         "title": "Tidy your room",
         "completed": true}
     */


    // 1) Java dataya bakılarak her bir key icin bir private degisken olusturulması

    private int userId;
    private int id;
    private String title;
    private boolean completed;

    // 2) Private degiskenlere erisebilmek için getter ve setter'ları olusturmak
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // 3) Default parametresiz Constructor olusturmak
    public TodosPojo(){

    }


    // 4) Parametreli constructor olusturmak -- nesne olusturma da kullanacagız

    public TodosPojo(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }


    // 5) toString() methodu kullanacagız actual veya expected data'yı gorebilmek için


    @Override
    public String toString() {
        return "TodosPojo{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
