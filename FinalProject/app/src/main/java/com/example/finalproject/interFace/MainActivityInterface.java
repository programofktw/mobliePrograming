package com.example.finalproject.interFace;

import android.widget.TableLayout;

import com.example.finalproject.BlockButton;

public interface MainActivityInterface {
    public BlockButton[][] initBlock(TableLayout tableLayout);
    public BlockButton[][] makeMine(BlockButton[][] buttons);
}
