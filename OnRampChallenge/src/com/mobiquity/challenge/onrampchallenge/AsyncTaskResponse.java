package com.mobiquity.challenge.onrampchallenge;

import java.util.ArrayList;

//Interface implemented to get result back from AsyncTask to Activity
public interface AsyncTaskResponse {
	void postResult(ArrayList<String> result);
}
