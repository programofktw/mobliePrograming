package com.example.finalproject;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class BlockButton extends Button {
    private int buttonX;
    private int buttonY;
    private boolean mine ;
    private boolean flag ;
    private int neighborMines;
    static int flags = 10;

    static int blocks;
    public BlockButton(Context context, int x , int y) {
        super(context);
        this.buttonX = x;
        this.buttonY = y;
        this.mine = false;
        this.flag = false;
    }


    public int getButtonX() {
        return this.buttonX;
    }

    public int getButtonY() {
        return buttonY;
    }

    public void setButtonY(int buttonY) {
        this.buttonY = buttonY;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    };

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getNeighborMines() {
        return neighborMines;
    }

    public void setNeighborMines(int neighborMines) {
        this.neighborMines = neighborMines;
    }

    public static void setFlags(int flags) {
        BlockButton.flags = flags;
    }

    public static int getBlocks() {
        return blocks;
    }

    public static void setBlocks(int blocks) {
        BlockButton.blocks = blocks;
    }

    public void setButtonX(int x) {
        this.buttonX = x;
    }


    public int getFlags(){
        return 10-this.flags;
    }

    public void toggleFlag(){
        this.flag = this.flag? false : true;
        if(this.flag){
            this.flags -=1;
            super.setText("+");
        }
        else {
            this.flags += 1;
            super.setText("");
        }
    }

    public boolean breakBlock() {

        if(isFlag()){
            this.toggleFlag();
        }
        this.setClickable(false);
        this.setBackgroundColor(Color.WHITE);
        if (isMine()) {
            super.setText("B");
            return true;
        }
        else {
            if (this.neighborMines != 0)
                super.setText("" + this.neighborMines);
                BlockButton.blocks--;
                return false;
        }

    }
}
