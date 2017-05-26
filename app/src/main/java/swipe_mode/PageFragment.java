package swipe_mode;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import nm.na3r.imageeditor.MainActivity;
import nm.na3r.imageeditor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment implements View.OnClickListener {
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,btn15,btn16;
            int position;

    ArrayList<Integer> imgSourc= new ArrayList<>();
    public PageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view =inflater.inflate(R.layout.page_fragment, container, false);
        btn1=(Button)view.findViewById(R.id.btn1);

        btn2=(Button)view.findViewById(R.id.btn2);
        btn3=(Button)view.findViewById(R.id.btn3);
        btn4=(Button)view.findViewById(R.id.btn4);
        btn5=(Button)view.findViewById(R.id.btn5);
        btn6=(Button)view.findViewById(R.id.btn6);
        btn7=(Button)view.findViewById(R.id.btn7);
        btn8=(Button)view.findViewById(R.id.btn8);
        btn9=(Button)view.findViewById(R.id.btn9);
        btn10=(Button)view.findViewById(R.id.btn10);
        btn11=(Button)view.findViewById(R.id.btn11);
        btn12=(Button)view.findViewById(R.id.btn12);
        btn13=(Button)view.findViewById(R.id.btn13);
        btn14=(Button)view.findViewById(R.id.btn14);
        btn15=(Button)view.findViewById(R.id.btn15);
        btn16=(Button)view.findViewById(R.id.btn16);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btn12.setOnClickListener(this);
        btn13.setOnClickListener(this);
        btn14.setOnClickListener(this);
        btn15.setOnClickListener(this);
        btn16.setOnClickListener(this);
        Bundle bundle =getArguments();
          position = bundle.getInt("count",2);
        btn1.setText(" page is "+ position);
        addimg();



        return  view;
    }






    public void addimg(){
        imgSourc.add(R.mipmap.a1);
        imgSourc.add(R.mipmap.a2);
        imgSourc.add(R.mipmap.a3);



    }

    @Override
    public void onClick(View v) {
        Intent intent =new Intent(v.getContext(), MainActivity.class);

      if (position==0){
            switch (v.getId()) {

                case R.id.btn1:
                    intent.putExtra("pagenumber",1);
                    break;

                case R.id.btn2:
                    intent.putExtra("pagenumber",2);
                    break;

                case R.id.btn3:
                    intent.putExtra("pagenumber",3);
                    break;
                case R.id.btn4:
                    intent.putExtra("pagenumber",4);
                    break;
                case R.id.btn5:
                    intent.putExtra("pagenumber",5);
                    break;
                case R.id.btn6:
                    intent.putExtra("pagenumber",6);
                    break;
                case R.id.btn7:
                    intent.putExtra("pagenumber",7);
                    break;
                case R.id.btn8:
                    intent.putExtra("pagenumber",8);
                    break;
                case R.id.btn9:
                    intent.putExtra("pagenumber",9);
                    break;
                case R.id.btn10:
                    intent.putExtra("pagenumber",10);
                    break;
                case R.id.btn11:
                    intent.putExtra("pagenumber",11);
                    break;
                case R.id.btn12:
                    intent.putExtra("pagenumber",12);
                    break;
                case R.id.btn13:
                    intent.putExtra("pagenumber",13);
                    break;
                case R.id.btn14:
                    intent.putExtra("pagenumber",14);
                    break;
                case R.id.btn15:
                    intent.putExtra("pagenumber",15);
                    break;
                case R.id.btn16:
                    intent.putExtra("pagenumber",16);
                    break;




                default:
                    break;
            }
        }


        if (position==1){
            switch (v.getId()) {

                case R.id.btn1:
                    intent.putExtra("pagenumber",17);
                    break;

                case R.id.btn2:
                    intent.putExtra("pagenumber",18);
                    break;

                case R.id.btn3:
                    intent.putExtra("pagenumber",19);
                    break;
                case R.id.btn4:
                    intent.putExtra("pagenumber",20);
                    break;
                case R.id.btn5:
                    intent.putExtra("pagenumber",21);
                    break;
                case R.id.btn6:
                    intent.putExtra("pagenumber",22);
                    break;
                case R.id.btn7:
                    intent.putExtra("pagenumber",23);
                    break;
                case R.id.btn8:
                    intent.putExtra("pagenumber",24);
                    break;
                case R.id.btn9:
                    intent.putExtra("pagenumber",25);
                    break;
                case R.id.btn10:
                    intent.putExtra("pagenumber",26);
                    break;
                case R.id.btn11:
                    intent.putExtra("pagenumber",27);
                    break;
                case R.id.btn12:
                    intent.putExtra("pagenumber",28);
                    break;
                case R.id.btn13:
                    intent.putExtra("pagenumber",29);
                    break;
                case R.id.btn14:
                    intent.putExtra("pagenumber",30);
                    break;
                case R.id.btn15:
                    intent.putExtra("pagenumber",31);
                    break;
                case R.id.btn16:
                    intent.putExtra("pagenumber",32);
                    break;




                default:
                    break;
            }
        }
        startActivity(intent);
    }
}
