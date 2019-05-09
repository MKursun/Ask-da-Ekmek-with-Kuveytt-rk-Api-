package com.example.askdaekmek;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TransferFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TransferFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransferFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView tvExplanation;
    TextView tvAccountNo;
    Spinner spinnerReceiverName;
    Spinner spinnerAmount;
    Button btnTransferPay;
    ArrayAdapter <String> arrayAdapter ;
    ArrayAdapter <String> amountsArrayAdapter ;
    List<String> cafes;
    List<String> amounts;
    String accountNo ;
    String amount;
    private final static String CLIENT_ID = "e630fa66c05940a7ad6d3ef50b8c5bdf";
    private final static String CLIENT_SECRET ="OmBcSei+zpQOYUZpxAlAcaZVY2H8LwNZePRyCn3jYupimwtDG/QdAA==";
    private final static String REDIRECT_URI = "com.example.askdaekmek://callback";
    private final String SCOPE = "transfers";
    private final static String RESPONSE_TYPE = "code";

    String receiverName ,  senderAcountSuffix;
    int suffixNumber = 1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TransferFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransferFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransferFragment newInstance(String param1, String param2) {
        TransferFragment fragment = new TransferFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_transfer, container, false);

        tvExplanation =view.findViewById(R.id.tvTransferExplanation);
        tvAccountNo =view.findViewById(R.id.tvTransferAccountNo);
        spinnerReceiverName =view.findViewById(R.id.spinnerReceiverName);
        btnTransferPay = view.findViewById(R.id.btnTransferTransfer);
        btnTransferPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transferIntent =new Intent(getContext(),TransferActivity.class);
                startActivity(transferIntent);
            }
        });

        tvAccountNo.setText("");
        // Spinner Drop down elements
        cafes = new ArrayList<String>();
        cafes.add("Sultan Ekmek Fırını");
        cafes.add("Kapadokya Pastanesi");

        // Creating adapter for spinner
        arrayAdapter=  new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, cafes);
        // Drop down layout style - list view with radio button
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerReceiverName.setAdapter(arrayAdapter);
        spinnerReceiverName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                switch (item) {
                    case "Sultan Ekmek Fırını":
                        accountNo="154873249";
                        break;
                    case "Kapadokya Pastanesi":
                        accountNo="245612784";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tvAccountNo.setText("");
        spinnerAmount = view.findViewById(R.id.spinnerAmount);
        amounts = new ArrayList<>();
        amounts.add("1");
        amounts.add("2");
        amounts.add("3");
        amountsArrayAdapter=  new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, amounts);
        amountsArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAmount.setAdapter(amountsArrayAdapter);
        spinnerAmount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                switch (item) {
                    case "1":
                        amount="1";
                        break;
                    case "2":
                        amount="2";
                        break;
                    case "3":
                        amount="3";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

      // btnTransferPay.setOnClickListener(new View.OnClickListener() {
        //    @Override
          //  public void onClick(View v) {
               // Intent transferIntent = new Intent(getActivity(), TransferActivity.class);
              //  getActivity().startActivity(transferIntent);
                /*Intent transferIntent = new Intent(,TransferActivity.class);
                Bundle accountNoBundle =new Bundle();
                accountNoBundle.putString("bundledAccountNo" , accountNo);
                accountNoBundle.putString("bundledAmount", amount);
                transferIntent.putExtras(accountNoBundle);*/
                //Toast.makeText(getActivity(),"PAra transfer sayfasına yönlendiriliyorsunuz lütfen bekleyin",Toast.LENGTH_SHORT).show();

         //   }
       // });

       /* btnTransfer.setOnClickListener(new View.OnClickListener() {




   //**********************************************************************
   //*****************************************************************


            }
            private void generateSuffixNumber(){
                senderAcountSuffix = String.valueOf(suffixNumber);
                Integer.valueOf(suffixNumber);
                suffixNumber++;
            }
        });*/


        return view;
    }

   /* public void onItemSelected(AdapterView<?> adapter, View view, int position){
        String item = adapter.getItemAtPosition(position).toString();
        switch (item) {
            case "Sultan Ekmek Fırını":
                tvAccountNo.setText("154873249721");
                break;
            case "Kapadokya Pastanesi":
                tvAccountNo.setText("245612784563");
                break;
        }
    }*/
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
