public class InvalidParametersEntered extends Exception {
	InvalidParametersEntered() {

	}

	InvalidParametersEntered(String error) throws Exception {
		throw new Exception(error);
	}
}
