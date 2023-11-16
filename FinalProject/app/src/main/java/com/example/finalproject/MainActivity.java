package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {


    TableLayout tableLayout;
    ToggleButton toggleButton;

    BlockButton[][] buttons;

    int minenum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton2);
        toggleButton.setText("Flag");
        toggleButton.setTextOn("Break");
        toggleButton.setTextOff("Flag");

        buttons = initBlock(tableLayout);

        buttons = makeMine(buttons);
    }

    public BlockButton[][] initBlock(TableLayout tableLayout){
        minenum = 10;
        BlockButton.flags = minenum;
        TextView mineNum = (TextView) findViewById(R.id.mineNumText);
        BlockButton[][] buttons = new BlockButton[9][9];
        TextView text = (TextView) findViewById(R.id.mineNumText);
        text.setText("10");
        for(int i =0; i < 9; i++){
            TableRow tableRow = new TableRow(this);

            for(int j = 0 ; j<9;j++){
                buttons[i][j] = new BlockButton(this,j,i);
                TableRow.LayoutParams layoutParams =
                        new TableRow.LayoutParams(
                                TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT,
                                1.0f);
                buttons[i][j].setLayoutParams(layoutParams);
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(toggleButton.getText().equals("Flag")){
                            toggleFlag(view);
                        }

                        else{
                            if(!((BlockButton) view).isFlag())
                               breakBlock(view);
                        }

                    }
                });
                tableRow.addView(buttons[i][j]);
            }
            tableLayout.addView(tableRow);
        }
        BlockButton.blocks = 71;

        return buttons;
    }

    public BlockButton[][] makeMine(BlockButton[][] buttons){
        int count = 0;
        int x;
        int y;
        while(count<minenum){
            x = (int)(Math.random()*9);
            y = (int)(Math.random()*9);
            if(buttons[x][y].isMine()) {
                continue;
            }

            buttons[x][y].setMine(true);
            count++;
        }

        for(int i = 0; i<buttons.length;i++){
            for(int j = 0 ; j<buttons[i].length;j++){
                if(buttons[i][j].isMine()){
                    buttons[i][j].setBackgroundColor(Color.RED);
                    if(i-1>=0){
                        buttons[i-1][j] = addNeighborMines(buttons[i-1][j]);
                        if(j-1>=0)
                            buttons[i-1][j-1] = addNeighborMines(buttons[i-1][j-1]);
                        if(j+1<=8)
                            buttons[i-1][j+1]= addNeighborMines(buttons[i-1][j+1]);
                    }
                    if(i+1<=8){
                        buttons[i+1][j] = addNeighborMines(buttons[i+1][j]);
                        if(j-1>=0)
                            buttons[i+1][j-1] = addNeighborMines(buttons[i+1][j-1]);
                        if(j+1<=8)
                            buttons[i+1][j+1]= addNeighborMines(buttons[i+1][j+1]);
                    }
                    if(j-1 >=0){
                        buttons[i][j-1] = addNeighborMines(buttons[i][j-1]);
                    }
                    if(j+1<=8){
                        buttons[i][j+1] = addNeighborMines(buttons[i][j+1]);
                    }
                }
            }
        }

        return buttons;
    }

    public BlockButton addNeighborMines(BlockButton button){
        button.setNeighborMines(button.getNeighborMines()+1);
        return button;
    }
    public boolean breakBlock(View view){
        BlockButton blockButton = ((BlockButton) view);
        blockButton.breakBlock();

        int i = blockButton.getButtonY();
        int j = blockButton.getButtonX();

        if(blockButton.getNeighborMines()==0 && !blockButton.isMine()){
            if(i-1 >= 0){
                chainBreak(buttons[i-1][j]);


                if(j-1>=0){
                    chainBreak(buttons[i-1][j-1]);
                }
                if(j+1<=8){
                    chainBreak(buttons[i-1][j+1]);
                }
            }
            if(i+1<=8){
//                buttons[i+1][j].breakBlock();
                chainBreak(buttons[i+1][j]);
                if(j-1>=0){
//                    buttons[i+1][j-1].breakBlock();
                    chainBreak(buttons[i+1][j-1]);
                }
                if(j+1<=8){
//                    buttons[i+1][j+1].breakBlock();

                    chainBreak(buttons[i+1][j+1]);
                }
            }
            if(j-1>=0){
//                buttons[i][j-1].breakBlock();
                chainBreak(buttons[i][j-1]);
            }
            if(j+1<=8){
//                buttons[i][j+1].breakBlock();
                chainBreak(buttons[i][j+1]);
            }
        }
        else if(blockButton.isMine()){
            blockButton.breakBlock();
            gameOver();
        }
        TextView textView = (TextView) findViewById(R.id.mineNumText);
        textView.setText(""+(BlockButton.flags));
        return true;
    }

    public boolean chainBreak(View view){
        BlockButton blockButton = ((BlockButton) view);
        if(blockButton.isClickable()){
            boolean b = (blockButton.getNeighborMines() == 0) ? breakBlock(blockButton) : blockButton.breakBlock();
        }
        return true;
    }


    public boolean toggleFlag(View view){
        ((BlockButton) view).toggleFlag();
        TextView mineNum = (TextView) findViewById(R.id.mineNumText);
        mineNum.setText(""+BlockButton.flags);
        return true;
    }

    public void gameOver(){
        for(int i = 0 ;i < 9 ; i++){
            for(int j = 0; j<9;j++){
                buttons[i][j].setClickable(false);
                if(buttons[i][j].isMine()){
                    buttons[i][j].breakBlock();
                    buttons[i][j].setTextColor(Color.WHITE);
                    buttons[i][j].setBackgroundColor(Color.GRAY);
                }
            }
        }
    }


}