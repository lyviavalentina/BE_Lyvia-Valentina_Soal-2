public class items{
    private int item_id;
    private String name;
    private String description;
    private int price;
    private String status;
    private int user_id;
    
    items(int item_id, String name, String description, int price, String status, int user_id){
        this.item_id = item_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.user_id= user_id;
    }

    items(int aInt, String string, String string0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public int getItem_id(){
        return item_id;
    }
    
    public String getName(){
        return name;
    }
    
    public String getDescription(){
        return description;
    }
    
    public int getPrice(){
        return price;
    }
    
    public String getStatus(){
        return status;
    }
    
    public int getUser_id(){
        return user_id;
    }
}