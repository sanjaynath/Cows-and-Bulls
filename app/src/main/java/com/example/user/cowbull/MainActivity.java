package com.example.user.cowbull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static TextView textView;
    static int a[] = {1,1,1};
    static int comp[] = new int[4];
    static int count_cow = 0;
    static int count_bull =0;
    static int attemp=0;
    ListView listView;
    ArrayList<String> al;

    public void generateRandom( View view)
    {
        Random rand = new Random();
        int distinct = 1;
        int p=0;
       // count_bull=0;


       while(distinct==1) {
         int j = 0;
            int compnum = rand.nextInt(900) + 100;
         //   textView.setText(Integer.toString(compnum));
           p = compnum;
            while(compnum!=0)
            {
                int temp = compnum%10;
                comp[j] = temp;
                compnum/=10;
                j++;

            }
            distinct=valid(comp);
        }

        String c2 = Integer.toString(comp[2]);
        String c1 = Integer.toString(comp[1]);
        String c0 = Integer.toString(comp[0]);





    }
    public static int valid(int arr[])
    {
        for(int i=1;i<3;i++)
        {

            if(arr[0]==arr[i])
            {
                return 1;
            }

        }
        if(arr[1]==arr[2])
            return 1;
        for(int j=0;j<3;j++)
        {
            if(arr[j]==0)
                return 1;
        }

        return 2;

    }

    public void check(View view)
    {
        attemp++;
        textView.setText(Integer.toString(attemp));
        Boolean flag=true;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(i!=j)
                {
                    if(a[i]==a[j])
                    {
                        Toast.makeText(MainActivity.this,"Two digits cannot be same ",Toast.LENGTH_SHORT).show();
                        flag=false;
                        break;

                    }
                }
            }

        }
        if(flag) {
            count_cow = 0;
            count_bull = 0;
            for (int i = 0; i < 3; i++) {
                if (a[i] == comp[i]) {
                    count_bull++;
                }
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i != j) {
                        if (a[i] == comp[j])
                            count_cow++;
                    }
                }
            }

            String c2 = Integer.toString(a[2]);
            String c1 = Integer.toString(a[1]);
            String c0 = Integer.toString(a[0]);
            if(a[0]==comp[0] && a[1]==comp[1] && a[2]==comp[2])
            {
                al.add(0,c2 + c1 + c0 + " Bingo");
            }
            else if (count_cow == 0 && count_bull == 0)
                al.add(0, c2 + c1 + c0 + " JOKER");
            else
                al.add(0, c2 + c1 + c0 + " Bull " + Integer.toString(count_bull) + " Cow " + Integer.toString(count_cow));

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al);
            listView.setAdapter(arrayAdapter);
        }


       // if(a[0]==comp[0] && a[1]==comp[1] && a[2]==comp[2])
           // textView.setText("Bingo");

            //textView.setText("Oops");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView);
        al = new ArrayList<String>();
        NumberPicker picker2 = (NumberPicker)findViewById(R.id.numberPicker4);
        picker2.setMinValue(1);
        picker2.setMaxValue(9);
        picker2.setWrapSelectorWheel(true);
        picker2.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        picker2.setValue(1);

        NumberPicker picker1 = (NumberPicker)findViewById(R.id.numberPicker5);
        picker1.setMinValue(1);
        picker1.setMaxValue(9);
        picker1.setWrapSelectorWheel(true);
        picker1.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        picker1.setValue(1);

        NumberPicker picker0 = (NumberPicker)findViewById(R.id.numberPicker6);
        picker0.setMinValue(1);
        picker0.setMaxValue(9);
        picker0.setWrapSelectorWheel(true);
        picker0.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        picker0.setValue(1);

        picker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                a[2] = i1;


            }
        });
        picker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                a[1] = i1;

            }
        });
        picker0.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                a[0] = i1;

            }
        });
        textView = (TextView)findViewById(R.id.textView3);
        generateRandom(findViewById(R.id.textView3));

    }
}
