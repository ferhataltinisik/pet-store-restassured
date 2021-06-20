package model;

public class Order {
	
	private Integer id;
	private Integer petId;
	private Integer quantity;
	private String status;
	private boolean complete;
	private String shipDate;

	
	public Order() {
		setId(3);
		setPetId(7);
		setQuantity(1);
		setComplete(true);
		setStatus("placed");
		setShipDate("2021-06-18T22:02:14.222Z");
	}

	public Order(Integer id, Integer petId, Integer quantity, String status, boolean complete, String shipDate) {
		this.id = id;
		this.petId = petId;
		this.quantity = quantity;
		this.status = status;
		this.complete = complete;
		this.shipDate = shipDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPetId() {
		return petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public String getShipDate() {
		return shipDate;
	}

	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}
}
