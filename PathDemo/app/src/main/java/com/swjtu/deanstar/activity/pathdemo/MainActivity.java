package com.swjtu.deanstar.activity.pathdemo;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    private LolAbilityView mLolView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLolView = (LolAbilityView) findViewById(R.id.lolabilityview);
        Item[] items = new Item[]{
                new Item("击杀",40,100),
                new Item("生存",10,100),
                new Item("助攻",80,100),
                new Item("物理",40,100),
                new Item("魔法",50,100),
                new Item("防御",70,100),
                new Item("金钱",80,100),


        };
        mLolView.bindData(items);
    }
}
