import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Response example: PROTOCOL_VERSION#STATUS#DATA
 *
 */


public class CcpResponse {

	private String protocolVersion;
	private String status;
	private List<String> data;
	
	public CcpResponse(String protocolVersion, String status, List<String> data) {
		super();
		this.protocolVersion = protocolVersion;
		this.status = status;
		this.data = data;
	}
	public String getProtocolVersion() {
		return protocolVersion;
	}
	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getData() {
		return data;
	}
	public void setData(List<String> data) {
		this.data = data;
	}
	
	public String toRawResponse() {
		String dataString = "";
		if(data != null) {
			for(String item : data) {
				dataString += "<" + item + ">";
			}
		}
		return protocolVersion + "#" + status + "#" + dataString + "\r\n"; 
	}
	
	public static CcpResponse fromRawResponse(String rawResponse) {
		String[] parts = rawResponse.split("#");
		String protocolVersion = parts[0];
		String status = parts[1];
		String dataString = parts[2];
		List<String> data = null;
		if(!dataString.isEmpty()) {
			data = new ArrayList<>();
			String[] dataParts = dataString.split("><");
			for(String itemPart : dataParts) {
				String readyItem = itemPart.replace("<", "").replace(">", "");
				data.add(readyItem);
			}
		}
		return new CcpResponse(protocolVersion, status, data);
	}
	
	public String getResult() {
		if(data == null) {
			return "There is no any result.";
		} else {
			String result = "";
			for(String item : data) {
				result += " - " + item + "\n";
			}
			return result;
		}
	}
	
}










