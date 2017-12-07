package com.apps.elikem.midtronics.webservices;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by elikem on 12/6/17.
 */

public class WebServices {

    public static  class UrlStrings {

        public static String getCountryDetail(String countryName) {

            return "https://restcountries.eu/rest/v1/name/" + countryName;
        }

    }

    public static String getCountryDetailWebServiceCall(String countryName) throws IOException {

        OkHttpClient mClient = new OkHttpClient();
        String url = UrlStrings.getCountryDetail(countryName);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = mClient.newCall(request).execute();
        return response.body().string();
    }
}
