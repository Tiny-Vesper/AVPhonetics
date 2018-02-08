package templateprj.ulip.hkbu.com.templateproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;
import android.content.pm.ActivityInfo;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class cGlossaryActivity extends AppCompatActivity {

    Activity activity;
    ListView listV;

    ArrayList glossaryList=new ArrayList();
    ArrayList<String> glossaryNameList=new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE); // Title or not.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Glossary");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#312223"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        setContentView(R.layout.activity_c_glossary);
        activity=this;
        listV=(ListView)findViewById(R.id.listV);

        try{
            String jStr=DeviceAPI.getJSONStringFromAssets("web/glossary.json");
            glossaryList=(ArrayList)DeviceAPI.getListOrMapFromJSON(new JSONArray(jStr));
        }catch(Exception e){}

        for(int i=0; i<glossaryList.size(); i++){
            glossaryNameList.add(((Map<String,String>)(glossaryList.get(i))).get("name"));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,glossaryNameList );
        listV.setAdapter(arrayAdapter);
        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //sToast("Pressed: "+position);
                toNext(position);
            }
        });
    }

    public void sToast(String str){
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home)finish();
        return true;
    }

    public void toNext(int index){
        Intent intent=new Intent();
        HashMap<String,String> map=new HashMap<String,String>();
        GlobalValue.viewingGlossary=((Map<String,String>)(glossaryList.get(index))).get("name");
        intent.setClass(activity, GlossaryDetailActivity.class);
        activity.startActivity(intent);
    }

}