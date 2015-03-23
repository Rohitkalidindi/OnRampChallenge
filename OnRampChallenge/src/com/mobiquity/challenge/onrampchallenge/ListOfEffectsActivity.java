package com.mobiquity.challenge.onrampchallenge;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class ListOfEffectsActivity extends ListActivity
{

    ArrayList<String> effects= new ArrayList<String>();
    String path;
    String effect_choosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i=getIntent();
        path=i.getStringExtra("image thumbnail path");

        Log.i("path of file in list of effects......", path);
 
        effects.add("Red Filter");
        effects.add("Green Filter");
        effects.add("Blue Filter");
        effects.add("Depth");
        effects.add("Gray");
        
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_of_effects,effects));
        
        ListView listView = getListView();
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //applyEffects(path);
                effect_choosen=effects.get(position);
                Log.i("Effect choosen................",effect_choosen);
                applyEffects();
            }
        });
    }

    void applyEffects()
    {
        Intent i=new Intent(ListOfEffectsActivity.this, EffectAddedActivity.class);
        i.putExtra("path",path);
        i.putExtra("effect",effect_choosen);
        startActivity(i);
    }
}