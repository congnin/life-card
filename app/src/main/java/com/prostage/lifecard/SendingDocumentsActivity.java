package com.prostage.lifecard;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.prostage.lifecard.Utils.Common;

import java.util.ArrayList;
import java.util.Arrays;

import info.hoang8f.android.segmented.SegmentedGroup;

public class SendingDocumentsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        View.OnClickListener {

    private Spinner spinner;
    private ArrayList<String> arrayList;
    private ArrayList<String> arrayListDecription;
    private LinearLayout linearLayout;
    private Button nextButton;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_documents);
        linearLayout = (LinearLayout) findViewById(R.id.layoutSending);
        nextButton = (Button) findViewById(R.id.btn_choose_document);
        RadioButton btSelect1 = (RadioButton) findViewById(R.id.button21);
        RadioButton btSelect2 = (RadioButton) findViewById(R.id.button22);
        nextButton.setOnClickListener(view -> {
            finish();
            Intent intent = new Intent(SendingDocumentsActivity.this, GuideToCaptureImageActivity.class);
            startActivity(intent);
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.chooseDocumentToolbar);
        toolbar.setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
//        toolbar.setNavigationIcon(R.drawable.ic_action_back);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });

        spinner = (Spinner) findViewById(R.id.spinner_choose_document);
        spinner.setOnItemSelectedListener(this);

//        arrayList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.spinner_choose_document)));
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList) {
//
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//
//                View view = super.getView(position, convertView, parent);
//                if (position == getCount()) {
//                    ((TextView) view.findViewById(android.R.id.text1)).setText("");
//                    ((TextView) view.findViewById(android.R.id.text1)).setHint(getItem(getCount()));
//                }
//                return view;
//            }
//
//            @Override
//            public int getCount() {
//                return super.getCount() - 1;
//            }
//        };
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(arrayAdapter);
//        spinner.setSelection(arrayAdapter.getCount());

        btSelect1.setOnClickListener(this);
        btSelect2.setOnClickListener(this);
        btSelect1.setTextColor(Color.BLACK);
        btSelect2.setTextColor(Color.BLACK);
        btSelect1.performClick();
        linearLayout.setVisibility(View.GONE);
        nextButton.setVisibility(View.GONE);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        Common.ID = position;
//        if (position != 5) {
//            linearLayout.setVisibility(View.VISIBLE);
//            nextButton.setVisibility(View.VISIBLE);
//        } else {
//            linearLayout.setVisibility(View.GONE);
//            nextButton.setVisibility(View.GONE);
//        }
        linearLayout.setVisibility(View.VISIBLE);
        nextButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        finish();
//        Intent intent = new Intent(SendingDocumentsActivity.this, CustomerInputActivity.class);
//        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button21:
                onLoadingData(0);
                break;
            case R.id.button22:
                onLoadingData(1);
                break;
        }
    }

    private void onLoadingData(int index) {
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        } else {
            arrayList.clear();
        }
        if (arrayListDecription == null) {
            arrayListDecription = new ArrayList<>();
        } else {
            arrayListDecription.clear();
        }

        switch (index) {
            case 0:
                arrayList.addAll(Arrays.asList(getResources().getStringArray(R.array.spinner_choose_document1)));
                arrayListDecription.addAll(Arrays.asList(getResources().getStringArray(R.array.document_content1)));
                break;
            case 1:
                arrayList.addAll(Arrays.asList(getResources().getStringArray(R.array.spinner_choose_document2)));
                arrayListDecription.addAll(Arrays.asList(getResources().getStringArray(R.array.document_content2)));
                break;
        }


        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(-1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                TextView textView =
                        (TextView) findViewById(R.id.explain_choose_document_1);
                textView.setText(arrayListDecription.get(position));
                linearLayout.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                linearLayout.setVisibility(View.GONE);
                nextButton.setVisibility(View.GONE);
            }
        });
    }
}
