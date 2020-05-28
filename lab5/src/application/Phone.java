package application;

public class Phone {
	   
	    String brand; 
	    Integer vendor_code;
	    Integer price;
	 
	    boolean sensor;
	  
	    
	    public Phone() {}

	    public Phone( Integer vendor_code,String brand, Integer price) {
	        this.brand = brand;
	        this.vendor_code = vendor_code;
	        this.price = price;
	        
	    }

	    public Phone(String brand) {
	        this.brand = brand;
	    }

	    public String get_brand() {
	        return brand;
	    }

	    public void set_brand(String brand) {
	        this.brand = brand;
	    }

	    public Integer get_vendor_code() {
	        return vendor_code;
	    }

	    public void set_vendor_code(Integer vendor_code) {
	        this.vendor_code = vendor_code;
	    }

	    public Integer get_price() {
	        return price;
	    }

	    public void set_price(Integer price) {
	        this.price = price;
	    }

	   

}
