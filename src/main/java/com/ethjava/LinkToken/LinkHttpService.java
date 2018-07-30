package com.ethjava.LinkToken;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.Service;
import org.web3j.protocol.exceptions.ClientConnectionException;
import org.web3j.protocol.http.HttpService;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class LinkHttpService extends Service {

	public static final MediaType JSON_MEDIA_TYPE
			= MediaType.parse("application/json; charset=utf-8");

	public static final String DEFAULT_URL = "http://localhost:8545/";

	private static final Logger log = LoggerFactory.getLogger(HttpService.class);

	private OkHttpClient httpClient;

	private final String url;

	private final boolean includeRawResponse;

	private HashMap<String, String> headers = new HashMap<>();

	private HashMap<String, String> linkRawTransactionHeads = new HashMap<String, String>() {
		{
			put("Nc", "IN");
		}
	};


	public LinkHttpService(String url, OkHttpClient httpClient, boolean includeRawResponses) {
		super(includeRawResponses);
		this.url = url;
		this.httpClient = httpClient;
		this.includeRawResponse = includeRawResponses;
	}

	public LinkHttpService(OkHttpClient httpClient, boolean includeRawResponses) {
		this(DEFAULT_URL, httpClient, includeRawResponses);
	}

	private LinkHttpService(String url, OkHttpClient httpClient) {
		this(url, httpClient, false);
	}

	public LinkHttpService(String url) {
		this(url, createOkHttpClient());
	}

	public LinkHttpService(String url, boolean includeRawResponse) {
		this(url, createOkHttpClient(), includeRawResponse);
	}

	public LinkHttpService(OkHttpClient httpClient) {
		this(DEFAULT_URL, httpClient);
	}

	public LinkHttpService(boolean includeRawResponse) {
		this(DEFAULT_URL, includeRawResponse);
	}

	public LinkHttpService() {
		this(DEFAULT_URL);
	}

	private static OkHttpClient createOkHttpClient() {
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		configureLogging(builder);
		return builder.build();
	}

	private static void configureLogging(OkHttpClient.Builder builder) {
		if (log.isDebugEnabled()) {
			HttpLoggingInterceptor logging = new HttpLoggingInterceptor(log::debug);
			logging.setLevel(HttpLoggingInterceptor.Level.BODY);
			builder.addInterceptor(logging);
		}
	}

	@Override
	protected InputStream performIO(String request) throws IOException {
		/* headers */
		Headers headers = buildHeaders();
		/* body */
		JSONObject jsonObject = JSON.parseObject(request);
		String method = jsonObject.getString("method");
		if (method.equals("getTransactionRecords")) {
			JSONArray params = jsonObject.getJSONArray("params");
			request = params.toJSONString();
		}
		if (method.equals("eth_sendRawTransaction")) {
			jsonObject.put("Nc", "IN");
			headers = Headers.of(linkRawTransactionHeads);
			request = jsonObject.toJSONString();
		}
		RequestBody requestBody = RequestBody.create(JSON_MEDIA_TYPE, request);
		okhttp3.Request httpRequest;
		httpRequest = new okhttp3.Request.Builder()
				.url(url + method.replace("eth_", ""))
				.headers(headers)
				.post(requestBody)
				.build();
		okhttp3.Response response = httpClient.newCall(httpRequest).execute();
		if (response.isSuccessful()) {
			ResponseBody responseBody = response.body();
			if (responseBody != null) {
				return buildInputStream(responseBody);
			} else {
				return null;
			}
		} else {
			throw new ClientConnectionException(
					"Invalid response received: " + response.body());
		}
	}

	private InputStream buildInputStream(ResponseBody responseBody) throws IOException {
		InputStream inputStream = responseBody.byteStream();

		if (includeRawResponse) {
			// we have to buffer the entire input payload, so that after processing
			// it can be re-read and used to populate the rawResponse field.

			BufferedSource source = responseBody.source();
			source.request(Long.MAX_VALUE); // Buffer the entire body
			Buffer buffer = source.buffer();

			long size = buffer.size();
			if (size > Integer.MAX_VALUE) {
				throw new UnsupportedOperationException(
						"Non-integer input buffer size specified: " + size);
			}

			int bufferSize = (int) size;
			BufferedInputStream bufferedinputStream =
					new BufferedInputStream(inputStream, bufferSize);

			bufferedinputStream.mark(inputStream.available());
			return bufferedinputStream;

		} else {
			return inputStream;
		}
	}

	private Headers buildHeaders() {
		return Headers.of(headers);
	}

	public void addHeader(String key, String value) {
		headers.put(key, value);
	}

	public void addHeaders(Map<String, String> headersToAdd) {
		headers.putAll(headersToAdd);
	}

	public HashMap<String, String> getHeaders() {
		return headers;
	}
}