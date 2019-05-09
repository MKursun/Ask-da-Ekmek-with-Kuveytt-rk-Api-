package com.example.askdaekmek;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.askdaekmek.api.AccessTokenFacade;
import com.example.askdaekmek.api.AuthorizationFacade;
import com.example.askdaekmek.api.FacadeFactory;
import com.example.askdaekmek.api.FacadeFactoryInterface;
import com.example.askdaekmek.api.PostRequestFacade;
import com.example.askdaekmek.api.ResponseHandlingFacade;
import com.example.askdaekmek.api.util.AccessTokenResponseBean;
import com.example.askdaekmek.api.util.SignatureGenerationException;
import com.example.askdaekmek.api.util.SignatureGenerator;
import com.google.gson.JsonObject;

public class TransferActivity extends AppCompatActivity implements ResponseHandlingFacade {

    EditText etReceiverName;
    EditText etReceiverTelephone;
    EditText etAmount;
    Button btnTransfer;
    String receiverName, receiverPhoneNumber, amount, senderAcountSuffix;
    int suffixNumber = 1;
    private final static String CLIENT_ID = "e630fa66c05940a7ad6d3ef50b8c5bdf";
    private final static String CLIENT_SECRET ="OmBcSei+zpQOYUZpxAlAcaZVY2H8LwNZePRyCn3jYupimwtDG/QdAA==";
    private final static String REDIRECT_URI = "com.example.askdaekmek://callback";
    private final String SCOPE = "transfers";
    private final static String RESPONSE_TYPE = "code";
    private AuthorizationFacade<TransferActivity> mKTAuthHandlerFacade;
    private AccessTokenFacade<TransferActivity> mKTAccessTokenHandlerFacade;
    private PostRequestFacade<TransferActivity> mKTPostRequestHandlerFacade;

    private String mKTAuthorizationCode;
    private String mAuthorizationBearer;
    private String mAccessToken;
    private String mRefreshToken;
    private final static String PRIVATE_KEY ="OmBcSei+zpQOYUZpxAlAcaZVY2H8LwNZePRyCn3jYupimwtDG/QdAA==";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        btnTransfer = findViewById(R.id.btnTransferTransfer);
        /*btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyTransferApi();
            }
        });*/
        FacadeFactoryInterface<TransferActivity> ktFacadeFactory =
                new FacadeFactory<TransferActivity>(this);
        mKTAuthHandlerFacade = ktFacadeFactory.getAuthorizationFacade();
        mKTAccessTokenHandlerFacade = ktFacadeFactory.getAccessTokenFacade();
        mKTPostRequestHandlerFacade = ktFacadeFactory.getPostFacade();

        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mKTAuthHandlerFacade.requestAuthorization(CLIENT_ID, RESPONSE_TYPE, REDIRECT_URI, SCOPE);
            }
        });
    }

    /*private void applyTransferApi() {
        generateSuffixNumber();
        receiverName =etReceiverName.getText().toString();
        receiverPhoneNumber = etReceiverTelephone.getText().toString();
        amount = etAmount.getText().toString();*/

       /* HttpUrl authorizeUrl = HttpUrl.parse("https://apitest.kuveytturk.com.tr/prep/v1/transfers/toGSM") //
                .newBuilder() //
                .addQueryParameter("client_id", "d85b11c87490428bb74e9b112a6b75ff")
                .addQueryParameter("scope", "transfers")
                .addQueryParameter("redirect_uri", REDIRECT_URI)
                .addQueryParameter("response_type", CODE)
                .build();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(String.valueOf(authorizeUrl.url())));
        startActivity(i);

    }*/

    private void generateSuffixNumber() {
        senderAcountSuffix = String.valueOf(suffixNumber);
        Integer.valueOf(suffixNumber);
        suffixNumber++;
    }

    @Override
    public void onGetResponseReceivedFromKTAPI(String getResponseMsg) {
        System.out.println("GET RESPONSE:\n " + getResponseMsg);
    }

    @Override
    public void onPostResponseReceivedFromKTAPI(String postResponseMsg) {

        System.out.println("POST RESPONSE:\n " + postResponseMsg);
    }

    @Override
    public void onAccessTokenReceived(AccessTokenResponseBean responseBean) {
        if (responseBean.getError() == null || responseBean.getError().isEmpty()) {

            mAccessToken = responseBean.getAccessToken();
            mRefreshToken = responseBean.getRefreshToken();
            mAuthorizationBearer = "Bearer " + mAccessToken;

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("SenderAccountSuffix", 1);
            jsonObject.addProperty("ReceiverAccountNumber ", 154873249);
            jsonObject.addProperty("ReceiverBankId",10);
            jsonObject.addProperty("ReceiverBranchId",909);
            jsonObject.addProperty("ReceiverName", "Cansu");
            jsonObject.addProperty("Amount", 1);
            jsonObject.addProperty("Comment", "SDK Test");
            jsonObject.addProperty("PaymentTypeId", 99);
            String jsonBody = jsonObject.toString();

            String signatureForPostRequest = null;
            try {
                signatureForPostRequest =
                        SignatureGenerator
                                .generateSignatureForPostRequest(mAccessToken, PRIVATE_KEY, jsonBody);
            } catch (SignatureGenerationException e) {
                e.printStackTrace();
                return;
            }
            mKTPostRequestHandlerFacade.doPost(
                    "v1/transfers/toAccount",
                    jsonBody,
                    mAuthorizationBearer,
                    signatureForPostRequest);

        } else {
            System.out.println("ERROR OCCURRED WHILE RETRIEVING THE ACCESS CODE:\n ERROR: " +
                    responseBean.getError() + "\nERROR DESCRIPTION: " +
                    responseBean.getErrorDescription());
        }


    }
    @Override
    public void onAuthorizationCodeReceived(String authorizationCode, String state, String error) {
        if (error == null || error.isEmpty()) {
            mKTAuthorizationCode = authorizationCode;
            mKTAccessTokenHandlerFacade.requestAccessTokenWithCode(
                    CLIENT_ID,
                    CLIENT_SECRET,
                    mKTAuthorizationCode,
                    REDIRECT_URI);
        } else {
            System.out.println("AUTHORIZATION FAILED!\n ERROR CODE: "
                    + error + "\nSTATE: " + state);
        }
    }
}
