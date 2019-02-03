package com.example.simplehangmanapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import stanford.androidlib.SimpleActivity;

public class MainActivity extends SimpleActivity {

    private String word;
    private int cnt;
    private ArrayList<String> words=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
        Init();
    }
    private void getData(){
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.words));
        while(scan.hasNextLine()){
               words.add(scan.nextLine().toLowerCase());
        }
    }

    private String randomWord(){
        Random randy = new Random();
        Random randy2 = new Random();
        word = words.get( randy.nextInt(words.size())   );
        int tmp=word.length()/2;
        String hiddenWord="";
        while(tmp>0) {
               int inx = randy2.nextInt(word.length());
               hiddenWord= word.replace(word.substring(inx,inx+1),"?");
               tmp--;
        }
        return hiddenWord;
    }

    private void Init(){
        $ET(R.id.your_text).setText("");
        $IV(R.id.picture).setImageResource(R.drawable.hangman0);
        $TV(R.id.word).setText(randomWord());
        cnt=0;
    }

    public void guess_click(View view) {
        ++cnt;
        EditText inputWord = $(R.id.your_text);
        if(   inputWord.getText().toString().equals(word) ) {
               toast("YOU WON");
               Init();
        }
        else {
                 if(cnt > 6) {
                       toast("YOU LOSE");
                       Init();
                 }
                 else {
                          int id = getResources().getIdentifier("hangman"+cnt,"drawable",getPackageName());
                          $IV(R.id.picture).setImageResource(id);
                 }
        }
    }

    public void change_click(View view) {
         Init();
    }
}
