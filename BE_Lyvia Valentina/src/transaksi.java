public class transaksi {
    private int transaksi_id;
    private int item_id;
    private int user_id;
    private String tanggal_transaksi;
    private int total_price;

    transaksi(int transaksi_id, int item_id, int user_id, String tanggal_transaksi, int total_price) {
        this.transaksi_id = transaksi_id;
        this.item_id = item_id;
        this.user_id = user_id;
        this.tanggal_transaksi = tanggal_transaksi;
        this.total_price = total_price;
    }

    public int getTransaksi_id() {
        return transaksi_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public int getUser_id() {
        return user_id;
    }
    
    public String getTanggal_transaksi() {
        return tanggal_transaksi;
    }
    
    public int getTotal_price() {
        return total_price;
    }
}
