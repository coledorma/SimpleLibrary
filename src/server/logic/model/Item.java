package server.logic.model;

public class Item {
	int itemid;
	String ISBN;
	
	public Item(int itemid,String ISBN){
		this.itemid=itemid;
		this.ISBN=ISBN;
	}
	
	public String toString(){
		return "["+this.itemid+","+this.ISBN+","+"]";
	}
	
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

}
