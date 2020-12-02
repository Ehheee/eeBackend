package kaur.ee.trial.service.exceptions;

public class ShopException extends Exception {

	private String messageKey;
	public ShopException(String messageKey) {
		this.messageKey = messageKey;
	}
	public String getMessageKey() {
		return messageKey;
	}
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
}
