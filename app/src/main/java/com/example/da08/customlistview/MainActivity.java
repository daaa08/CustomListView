package com.example.da08.customlistview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1 data
        ArrayList<Data> datas = Loader.getData();

        // 2 adapter
        CustomAdapter adapter = new CustomAdapter(datas,this);

        // 3 연결
        ((ListView)findViewById(R.id.listview)).setAdapter(adapter);
    }
}


class CustomAdapter extends BaseAdapter{  // 2번의 adapter를 생성해주기위해서 만들어진 class

    // 1 data
    ArrayList<Data> datas;
    // 2 inflater
    LayoutInflater inflater;

    public CustomAdapter(ArrayList<Data> datas, Context context){
        this.datas = datas;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(id);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1 컨버투뷰를 체크해서 널 일경우 아이템 레이아웃을 생성해준다
        Holder holder;  // 겹치므로 밖에다가 한번 선언해준것임
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.item_list, null);
            holder = new Holder();
            holder.no = (TextView) convertView.findViewById(R.id.txtno);
            holder.title = (TextView) convertView.findViewById(R.id.txttitle);

            convertView.setTag(holder);
        }else{
            holder =  (Holder) convertView.getTag();  // 셋태그로하면 오브젝트로 들어감
        }
        // 2 내 아이쳄에 해당하는 데이터를 세팅
        // 뿌려주는건 if문 밖에 작성해줘야 함
        Data data = datas.get(position);

//            ((TextView) convertView.findViewById(R.id.txtno)).setText(data.no + "");   // 홀더를 생서했기때문에 위에 처럼 표시하면 됨
//            ((TextView) convertView.findViewById(R.id.txttitle)).setText(data.title);
        holder.no.setText(data.no+"");
        holder.title.setText(data.title);
        return convertView;
    }

  // convertView가 null이냐 아니냐에 따라서 한번만 체크 됨
    class Holder {
        TextView no;
        TextView title;
    }
}
class Loader{
    public static ArrayList<Data> getData(){   // 데이터를 가져옴
        ArrayList<Data> result = new ArrayList<>();
        for(int i=0; i<100; i++){
            Data data = new Data();
            data.no=i+1;
            data.title = "데이터"+i;
            result.add(data);
        }
        return result;
    }
}

class Data{
    public int no;
    public String title;
}