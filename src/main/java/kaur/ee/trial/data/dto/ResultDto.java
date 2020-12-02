package kaur.ee.trial.data.dto;

public class ResultDto {
	private ResponseCode responseCode;
	private Object result;
	
	public ResultDto(ResponseCode responseCode, Object result) {
		super();
		this.responseCode = responseCode;
		this.result = result;
	}
	public ResponseCode getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(ResponseCode responseCode) {
		this.responseCode = responseCode;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
}
