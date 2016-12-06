package com.porohnenko.pv115po.ibmt.bsu.lectionphotolist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


class Person {
    public String surname;
    public String name;
    public int img;

    public Person(String s, String n, int img) {
        this.surname = s;
        this.name = n;
        this.img = img;
    }

    @Override
    public String toString() {
        return this.surname + " " + this.name;
    }
}

class PersonAdapter extends ArrayAdapter<Person> {

    ArrayList<Person> array;
    Context context;




    public PersonAdapter(Context context, ArrayList<Person> array) {
        super(context, android.R.layout.simple_list_item_1);
        this.context = context;
        this.array = array;
    }


    @Override

    //give number of elements of Array
    public int getCount() {
        return array.size();
    }

    @NonNull
    @Override
//    getView -когда не трогаем спиннер
//    getDropDonwView , когда трогаем.
    public View getView(int position, View convertView, ViewGroup parent) {
        //put our layout and make "inflate"

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.layout_item, parent, false);

        ImageView iw = (ImageView) layout.findViewById(R.id.imageView);
        TextView name = (TextView) layout.findViewById(R.id.textName);
        TextView surname = (TextView) layout.findViewById(R.id.textSurname);

        Person p = array.get(position);
        name.setText(p.name);
        surname.setText(p.surname);
        iw.setImageResource(p.img);

        return layout;
    }
      @NonNull
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

          LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.layout_item, parent, false);

          ImageView iw = (ImageView) layout.findViewById(R.id.imageView);
          TextView name = (TextView) layout.findViewById(R.id.textName);
          TextView surname = (TextView) layout.findViewById(R.id.textSurname);

          Person p = array.get(position);
          name.setText(p.name);
          surname.setText(p.surname);
          iw.setImageResource(p.img);

          return layout;
    }
}

public class MainActivity extends AppCompatActivity {

    ArrayList<Person> array = new ArrayList<Person>();
    ListView lw;
    PersonAdapter adapter;
    TextView tv;
    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        array.add(new Person("Porohnenko", "Oleg", R.drawable.p1));
        array.add(new Person("Petrov", "Ivan", R.drawable.p2));
        array.add(new Person("Sidorov", "Boris", R.drawable.p3));
        array.add(new Person("Volkov", "Alexander", R.drawable.p4));
        tv = (TextView)findViewById(R.id.textView);
        sp=(Spinner)findViewById(R.id.spinner);

//        lw = (ListView) findViewById(R.id.list_view);
        adapter = new PersonAdapter(this, array);

        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv.setText(array.get(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
