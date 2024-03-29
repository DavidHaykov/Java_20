import telran.net.RequestJava;
import telran.net.ResponseJava;
import telran.net.TcpResponseCode;
import telran.net.server.ProtocolJava;

public class ProtocolTest implements ProtocolJava {

    @Override
    public ResponseJava getResponse(RequestJava request) {
        String type = request.requestType;
        String data = (String) request.requestData;
        switch (type) {
            case "length":
                return getLength(data);
            case "reverse":
                return getReverse(data);
            default:
                return new ResponseJava(TcpResponseCode.UNKNOWN, null);
        }
    }

    private ResponseJava getReverse(String data) {
        try {
            String res = new StringBuilder(data).reverse().toString();
            return new ResponseJava(TcpResponseCode.OK, res);
        } catch (Exception e) {
            return new ResponseJava(TcpResponseCode.WRONG_REQUEST, null);
        }
    }

    private ResponseJava getLength(String data) {
        try {
            int length = data.length();
            return new ResponseJava(TcpResponseCode.OK, length);
        } catch (Exception e) {
            return new ResponseJava(TcpResponseCode.WRONG_REQUEST, null);
        }
    }

}
